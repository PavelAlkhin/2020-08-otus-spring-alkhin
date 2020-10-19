package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "alkhin", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }


    @ChangeSet(order = "002", id = "insertLermontov", author = "alkhin")
    public void insertLermontov(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        var doc = new Document().append("name", "Lermontov");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertPushkin", author = "alkhin")
    public void insertPushkin(AuthorRepository repository) {
        repository.save(new Author("Pushkin"));
    }

    @ChangeSet(order = "004", id = "insertTutchev", author = "alkhin")
    public void insertTutchev(AuthorRepository repository) {
        repository.save(new Author("Tutchev"));
    }

    @ChangeSet(order = "005", id = "insertFantastic", author = "alkhin")
    public void insertFantastic(GenreRepository repository) {
        repository.save(new Genre("Fantastic"));
    }

    @ChangeSet(order = "006", id = "insertDetective", author = "alkhin")
    public void insertDetective(GenreRepository repository) {
        repository.save(new Genre("Detective"));
    }

    @ChangeSet(order = "007", id = "insertFantasy", author = "alkhin")
    public void insertFantasy(GenreRepository repository) {
        repository.save(new Genre("Fantasy"));
    }

    @ChangeSet(order = "008", id = "insertBooks", author = "alkhin")
    public void insertBooks(BookRepository repBook, AuthorRepository repAuthor, GenreRepository repGenre) {
        List<Author> authorList = new ArrayList<>();
        authorList.add(repAuthor.findByName("Pushkin"));
        List<Genre> genreList = new ArrayList<>();
        genreList.add(repGenre.findByName("Fantasy"));
        repBook.save(new Book("Book1", "Desc1", authorList, genreList));
    }

    @ChangeSet(order = "009", id = "insertComment", author = "alkhin")
    public void insertComment(CommentRepository repComment, BookRepository repBook) {
        repComment.save(new Comment("Fantasy", repBook.findByTitle("Book1").get(0)));
    }
}
