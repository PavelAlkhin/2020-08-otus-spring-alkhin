//package ru.otus.spring.repositories;
//
//import lombok.val;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.context.annotation.Import;
//import ru.otus.spring.models.Author;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DisplayName("Репозиторий на основе Jpa для работы с авторами ")
//@DataJpaTest
//@Import(AuthorRepositoryJpaImlp.class)
//class AuthorRepositoryJpaImlpTest {
//
//    @Autowired
//    private AuthorRepositoryJpaImlp authorRepository;
//
//    @Autowired
//    private TestEntityManager em;
//
//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void findByName() {
//        val firstAuthor = em.find(Author.class, 1L);
//        List<Author> authorList = authorRepository.findByName("Pushkin");
//        System.out.println(authorList);
//        assertThat(authorList).containsOnlyOnce(firstAuthor);
//    }
//
//    @Test
//    void findByAuthorName() {
//    }
//}