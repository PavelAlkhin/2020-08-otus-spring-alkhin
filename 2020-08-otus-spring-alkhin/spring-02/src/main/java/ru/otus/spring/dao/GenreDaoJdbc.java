package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository(value = "GenreDao")
public class GenreDaoJdbc implements GenreDao{

    private final NamedParameterJdbcOperations JdbcOperations;

    public int countByName(String name) {
        return JdbcOperations.queryForObject("select count(*) from genres where name = :name",
                Map.of("name", name), Integer.class);
    }

    @Override
    public int count() {
        return JdbcOperations.getJdbcOperations().queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public Genre save(Genre genre) {

        int cnt = countByName(genre.getName());

        if (cnt > 0){
            genre.setId(getIdByName(genre.getName()));
        }

        JdbcOperations.update("insert into genres (name) values (:name)", Map.of("name", genre.getName()));

        genre.setId(getIdByName(genre.getName()));

        return genre;
    }

    @Override
    public Genre getById(long id) {

        return JdbcOperations.queryForObject(
                "select * from genres where id = :id",
                Map.of("id",id), new GenreDaoJdbc.GenreMapper()
        );
    }

    @Override
    public int getIdByName(String name) {

        if (countByName(name) == 0){
            return 0;
        }

        return JdbcOperations.queryForObject(
                "select id from genres where name = :name",
                Map.of("name", name), Integer.class
        );
    }

    @Override
    public void update(Genre Genre) {

        JdbcOperations.update(
                "delete from genres where id = :id",
                Map.of("name", Genre.getId())
        );

        JdbcOperations.update(
                "insert into genres (id, 'name') values (:id, :name)",
                Map.of("name", Genre.getName(),"id", Genre.getId())
        );
    }

    @Override
    public List<Genre> getAll() {
        return JdbcOperations.getJdbcOperations().query("select * from genres", new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        JdbcOperations.update(
                "delete from genres where id = :id",
                Map.of("id", id)
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Genre genre = new Genre(name);
            genre.setId(id);
            return genre;
        }
    }
}
