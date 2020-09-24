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
        return JdbcOperations.queryForObject("select count(1) from books " +
                        "where title = :title and author_id = :author_id",
                Map.of("title", title,"author_id",author_id), Integer.class);
    }

    @Override
    public int count() {
        return JdbcOperations.getJdbcOperations().queryForObject("select count(1) from books", Integer.class);
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

        String query = "select " +
                "books.id, books.title, books.author_id, books.genre_id, " +
                "authors.name as authorname, " +
                "genres.name as genrename " +
                "from books left join authors on books.author_id = authors.id" +
                " left join genres on books.genre_id = genres.id where books.id = :id";

        return JdbcOperations.queryForObject( query, Map.of("id", id), new BookMapper() );

    }

    @Override
    public void update(Book book) {

        Author author = authorDao.save(book.getAuthor());
        Genre genre = genreDao.save(book.getGenre());

        try {
            JdbcOperations.update(
                    "delete from books where id = :id",
                    Map.of("id", book.getId())
            );
        }catch (DataAccessException e){
            System.out.println(e);
        }

        JdbcOperations.update(
                "insert into books (id, title, author_id, genre_id) values (:id, :title, :author_id, :genre_id)",
                Map.of("id", book.getId(),
                        "title", book.getTitle(),
                        "author_id", author.getId(),
                        "genre_id", genre.getId()
                )
        );
    }

    @Override
    public List<Book> getAll() {
        String query = "select " +
                "books.id, books.title, books.author_id, books.genre_id, " +
                "authors.name as authorname, " +
                "genres.name as genrename " +
                "from books left join authors on books.author_id = authors.id" +
                " left join genres on books.genre_id = genres.id";
        return JdbcOperations.query(query, new BookMapper());
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

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {

            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            long author_id = resultSet.getLong("author_id");
            long genre_id = resultSet.getLong("genre_id");
            String authorName = resultSet.getString("authorname");
            String genreName = resultSet.getString("genrename");

            Author author = new Author(authorName);
            author.setId(author_id);

            Genre genre = new Genre(genreName);
            genre.setId(genre_id);

            Book book = new Book(title, author, genre);
            book.setId(id);

            return book;
        }
    }
}
