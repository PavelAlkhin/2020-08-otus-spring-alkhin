//package ru.otus.spring.mongock.changelog;
//
//import com.github.cloudyrock.mongock.ChangeLog;
//import com.github.cloudyrock.mongock.ChangeSet;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import lombok.val;
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import ru.otus.spring.models.*;
//import ru.otus.spring.repositories.*;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@ChangeLog
//public class DatabaseChangelog {
//
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @ChangeSet(order = "001", id = "dropDb", author = "alkhin", runAlways = true)
//    public void dropDb(MongoDatabase db) {
//        db.drop();
//    }
//
//    @ChangeSet(order = "002", id = "insertLermontov", author = "alkhin")
//    public void insertLermontov(MongoDatabase db) {
//        MongoCollection<Document> myCollection = db.getCollection("authors");
//        var doc = new Document().append("name", "Lermontov");
//        myCollection.insertOne(doc);
//    }
//
//    @ChangeSet(order = "003", id = "insertPushkin", author = "alkhin")
//    public void insertPushkin(AuthorRepository repository) {
//        repository.save(new Author("Pushkin"));
//    }
//
//    @ChangeSet(order = "004", id = "insertTutchev", author = "alkhin")
//    public void insertTutchev(AuthorRepository repository) {
//        repository.save(new Author("Tutchev"));
//    }
//
//    @ChangeSet(order = "005", id = "insertFantastic", author = "alkhin")
//    public void insertFantastic(GenreRepository repository) {
//        repository.save(new Genre("Fantastic"));
//    }
//
//    @ChangeSet(order = "006", id = "insertDetective", author = "alkhin")
//    public void insertDetective(GenreRepository repository) {
//        repository.save(new Genre("Detective"));
//    }
//
//    @ChangeSet(order = "007", id = "insertFantasy", author = "alkhin")
//    public void insertFantasy(GenreRepository repository) {
//        repository.save(new Genre("Fantasy"));
//    }
//
//    @ChangeSet(order = "008", id = "insertBooks", author = "alkhin")
//    public void insertBooks(BookRepository repBook, AuthorRepository repAuthor, GenreRepository repGenre) {
//        List<Author> authorList = new ArrayList<>();
//        authorList.add(repAuthor.findByName("Pushkin"));
//        authorList.add(repAuthor.findByName("Tutchev"));
//        List<Genre> genreList = new ArrayList<>();
//        genreList.add(repGenre.findByName("Fantasy"));
//        genreList.add(repGenre.findByName("Detective"));
//        repBook.save(new Book("Book1", "Desc1", authorList, genreList));
//    }
//
//    @ChangeSet(order = "009", id = "insertBooks2", author = "alkhin")
//    public void insertBooks2(BookRepository repBook, AuthorRepository repAuthor, GenreRepository repGenre) {
//        List<Author> authorList = new ArrayList<>();
//        authorList.add(repAuthor.findByName("Lermontov"));
//        authorList.add(repAuthor.findByName("Tutchev"));
//        List<Genre> genreList = new ArrayList<>();
//        genreList.add(repGenre.findByName("Detective"));
//        genreList.add(repGenre.findByName("Fantastic"));
//        repBook.save(new Book("Book2", "Desc2 for book2", authorList, genreList));
//    }
//
//    @ChangeSet(order = "010", id = "insertComment", author = "alkhin")
//    public void insertComment(BookRepository repBook) {
//        Book book = repBook.findByTitle("Book1").get(0);
//        book.setComments(Collections.singletonList(new Comment("It is very interesting book")));
//        repBook.save(book);
//    }
//
//    @ChangeSet(order = "011", id = "insertComment2", author = "alkhin")
//    public void insertComment2(BookRepository repBook) {
//        Book book = repBook.findByTitle("Book1").get(0);
//        book.addComment("I like it");
//        repBook.save(book);
//    }
//
//    @ChangeSet(order = "012", id = "insertComment3", author = "alkhin")
//    public void insertComment3(BookRepository repBook) {
//        Book book = repBook.findByTitle("Book1").get(0);
//        book.addComment("I like it too");
//        repBook.save(book);
//    }
//
//    @ChangeSet(order = "013", id = "insertComment4", author = "alkhin")
//    public void insertComment4(BookRepository repBook) {
//        Book book = repBook.findByTitle("Book2").get(0);
//        book.addComment("Very bad");
//        repBook.save(book);
//    }
//
//    @ChangeSet(order = "013", id = "insertComment5", author = "alkhin")
//    public void insertComment5(BookRepository repBook) {
//        Book book = repBook.findByTitle("Book2").get(0);
//        book.addComment("Nerver buy it, please.");
//        repBook.save(book);
//    }
//
//    @ChangeSet(order = "014", id = "insertUser", author = "alkhin")
//    public void insertUser(UserRepository userRep, RoleRepository roleRep) {
//
//        val userUser = new User("user");
//        userUser.setPassword("111");
//        userUser.setActive(true);
//        userUser.setName("userovich");
//        userUser.setEmail("aaa@qqq");
//        userUser.setLastName("userov");
//
//        User userFromDB = userRep.findByUserName(userUser.getUserName());
//
//        if (userFromDB != null) {
//            return;
//        }
//
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//        Role roleUser = roleRep.findByRole("ROLE_USER");
//        if(roleUser == null){
//            roleUser = roleRep.save(new Role("ROLE_USER"));
//        }
//
//        userUser.setRoles(Collections.singleton(roleUser));
//        userUser.setPassword(bCryptPasswordEncoder.encode(userUser.getPassword()));
//        userRep.save(userUser);
//
//
//        val userAdmin = new User("admin");
//        userAdmin.setPassword("111");
//        User userFromDB2 = userRep.findByUserName(userAdmin.getUserName());
//
//        if (userFromDB2 != null) {
//            return;
//        }
//
//        Role roleAdmin = roleRep.findByRole("ROLE_ADMIN");
//        if(roleAdmin == null){
//            roleAdmin = roleRep.save(new Role("ROLE_ADMIN"));
//        }
//        userAdmin.setActive(true);
//        userAdmin.setName("adminych");
//        userAdmin.setEmail("adm@qqq");
//        userAdmin.setLastName("adminov");
//
//        userAdmin.setRoles(Collections.singleton(roleAdmin));
//        userAdmin.setPassword(bCryptPasswordEncoder.encode(userAdmin.getPassword()));
//        userRep.save(userAdmin);
//
//    }
//
//}
