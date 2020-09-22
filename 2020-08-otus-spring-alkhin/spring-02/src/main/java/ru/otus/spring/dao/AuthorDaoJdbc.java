package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository(value = "AuthorDao")
public class AuthorDaoJdbc implements AuthorDao{

    private final NamedParameterJdbcOperations JdbcOperations;

    public int countByName(String name) {
        return JdbcOperations.queryForObject("select count(*) from authors where name = :name",
                Map.of("name", name), Integer.class);
    }

    @Override
    public int count() {
        return JdbcOperations.getJdbcOperations().queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public Author save(Author author) {

        int cnt = countByName(author.getName());

        if (cnt > 0){
            author.setId(getIdByName(author.getName()));
        }

        JdbcOperations.update("insert into authors (name) values (:name)", Map.of("name", author.getName()));

        author.setId(getIdByName(author.getName()));

        return author;
    }

    @Override
    public Author getById(long id) {

        return JdbcOperations.queryForObject(
                "select * from authors where id = :id",
                Map.of("id",id), new AuthorMapper()
        );
    }

    @Override
    public int getIdByName(String name) {

        if (countByName(name) == 0){
            return 0;
        }

        return JdbcOperations.queryForObject(
                "select id from authors where name = :name",
                Map.of("name", name), Integer.class
        );
    }

    @Override
    public void update(Author author) {

        JdbcOperations.update(
                "delete from authors where id = :id",
                Map.of("name", author.getId())
        );

        JdbcOperations.update(
                "insert into authors (id, 'name') values (:id, :name)",
                Map.of("name", author.getName(),"id", author.getId())
        );
    }

    @Override
    public List<Author> getAll() {
        return JdbcOperations.getJdbcOperations().query("select * from authors", new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        JdbcOperations.update(
                "delete from authors where id = :id",
                Map.of("id", id)
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Author author = new Author(name);
            author.setId(id);
            return author;
        }
    }
}