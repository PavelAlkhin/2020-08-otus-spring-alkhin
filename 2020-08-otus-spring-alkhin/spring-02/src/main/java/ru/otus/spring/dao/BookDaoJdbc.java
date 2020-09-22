package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository(value = "BookDao")
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations JdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public int countByTitleAuthor(String title, long author_id) {
        return JdbcOperations.queryForObject("select count(*) from books " +
                        "where title = :title and author_id = :author_id",
                Map.of("title", title,"author_id",author_id), Integer.class);
    }

    @Override
    public int count() {
        return JdbcOperations.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    public int getIdByTitleAuthor(String title, long author_id) {

        if (countByTitleAuthor(title, author_id) == 0){
            return 0;
        }

        return JdbcOperations.queryForObject(
                "select id from books where title = :title and author_id = :author_id",
                Map.of("title", title,"author_id",author_id), Integer.class
        );
    }


    @Override
    public Book save(Book book) throws SQLException {

        Author author = authorDao.save(book.getAuthor());
        Genre genre = genreDao.save(book.getGenre());

        int cnt = countByTitleAuthor(book.getTitle(), author.getId());

        if (cnt > 0){
            book.setId(getIdByTitleAuthor(book.getTitle(), author.getId()));
            return book;
        }

        JdbcOperations.update(
                "insert into books (title, author_id, genre_id) values (:title, :author_id, :genre_id)",
                Map.of("title", book.getTitle(),
                        "author_id", author.getId(),
                        "genre_id", genre.getId()
                )
        );

        book.setId(getIdByTitleAuthor(book.getTitle(), author.getId()));

        return book;

    }

    @Override
    public Book getBookById(long id) {

        return JdbcOperations.queryForObject(
                "select * from books where id = :id", Map.of("id", id), new BookMapper()
        );

    }

    @Override
    public void update(Book book) {

        Author author = authorDao.save(book.getAuthor());
        Genre genre = genreDao.save(book.getGenre());

        try {
            JdbcOperations.update(
                    "delete from books where title = :title and author_id = :author_id and genre_id = :genre_id",
                    Map.of("title", book.getTitle(),
                            "author_id", author.getId(),
                            "genre_id", genre.getId()
                    )
            );
        }catch (DataAccessException e){

        }

        JdbcOperations.update(
                "insert into books (title, author_id, genre_id) values (:title, :author_id, :genre_id)",
                Map.of("title", book.getTitle(),
                        "author_id", author.getId(),
                        "genre_id", genre.getId()
                )
        );
    }

    @Override
    public List<Book> getAll() {
        return JdbcOperations.query("select * from books", new BookMapper());
    }

    @Override
    public void deleteById(long id) {

        try {
            JdbcOperations.update(
                    "delete from books where id = :id", Map.of("id", id)
            );
        }catch (DataAccessException e){

        }

    }

    private static class BookMapper implements RowMapper<Book> {

        private AuthorDao authorDao;
        private GenreDao genreDao;

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            long author_id = resultSet.getLong("author_id");
            Author author = authorDao.getById(author_id);
            long genre_id = resultSet.getLong("genre_id");
            Genre genre = genreDao.getById(genre_id);
            Book book = new Book(title, author, genre);
            book.setId(id);
            return book;
        }
    }
}
