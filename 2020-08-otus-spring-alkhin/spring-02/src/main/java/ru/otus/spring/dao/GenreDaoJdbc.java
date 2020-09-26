package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
        return JdbcOperations.queryForObject("select count(1) from genres where name = :name",
                Map.of("name", name), Integer.class);
    }

    @Override
    public int count() {
        return JdbcOperations.getJdbcOperations().queryForObject("select count(1) from genres", Integer.class);
    }

    @Override
    public Genre save(Genre genre) {

        int cnt = countByName(genre.getName());

        if (cnt > 0){
            genre.setId(getIdByName(genre.getName()));
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());
        KeyHolder kh = new GeneratedKeyHolder();
        JdbcOperations.update("insert into genres (name) values (:name)", params, kh);
        genre.setId(kh.getKey().longValue());

        return genre;
    }

    @Override
    public Genre getById(long id) {

        return JdbcOperations.queryForObject(
                "select id, name from genres where id = :id",
                Map.of("id",id), new GenreDaoJdbc.GenreMapper()
        );
    }

    @Override
    public int getIdByName(String name) {

        try {
            return JdbcOperations.queryForObject(
                    "select id from genres where name = :name",
                    Map.of("name", name), Integer.class
            );
        }catch (DataAccessException e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public void update(Genre Genre) {
        try {
            JdbcOperations.update(
                    "update genres set name = :name where id = :id",
                    Map.of("name", Genre.getName(), "id", Genre.getId())
            );

        } catch (DataAccessException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Genre> getAll() {
        return JdbcOperations.getJdbcOperations().query("select id, name from genres", new GenreDaoJdbc.GenreMapper());
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
