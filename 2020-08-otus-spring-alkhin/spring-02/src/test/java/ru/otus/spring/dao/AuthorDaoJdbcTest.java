package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDaoJdbc.class)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AuthorDaoJdbcTest {
    private static final int EXPECTED_AUTHOR_COUNT = 2;
    private static final long NECKTOFOR_ID = 1L;
    private static final String NECKTOFOR_NAME = "Neckto For";

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество авторов в БД")
    @Test
    void shouldReturnExpectedGenreCount() {
        int count = authorDaoJdbc.count();
        assertThat(count).isEqualTo(EXPECTED_AUTHOR_COUNT);
    }


    @DisplayName("добавлять автора в БД")
    @Test
    void shouldsave() {
        Author expAuthor = new Author( "new1");
        expAuthor = authorDaoJdbc.save(expAuthor);
        Author actualAuthor = authorDaoJdbc.getById(expAuthor.getId());
        assertThat(actualAuthor).isEqualTo(expAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    void shouldgetById() {
        Author actualAuthor = authorDaoJdbc.getById(NECKTOFOR_ID);
        assertThat(actualAuthor)
                .hasFieldOrPropertyWithValue("id", NECKTOFOR_ID)
                .hasFieldOrPropertyWithValue("name", NECKTOFOR_NAME);
    }

    @DisplayName("возвращать ожидаемого автора по имени")
    @Test
    void shouldgetIdByName() {
        int expId = authorDaoJdbc.getIdByName(NECKTOFOR_NAME);
        assertThat(expId).isEqualTo(NECKTOFOR_ID);
    }

    @DisplayName("обновляет наименование автора")
    @Test
    void shouldupdate() {
        Author newAuthor = new Author("Neckto Forvrgvwe");
        newAuthor.setId(NECKTOFOR_ID);
        authorDaoJdbc.update(newAuthor);
        Author expAuthor = authorDaoJdbc.getById(NECKTOFOR_ID);
        assertThat(expAuthor.getName()).isEqualTo("Neckto Forvrgvwe");
    }

    @DisplayName("получает всех авторов")
    @Test
    void shouldgetAll() {
        List<Author> author = authorDaoJdbc.getAll();
        assertThat(author.size()).isEqualTo(EXPECTED_AUTHOR_COUNT);
    }

    @DisplayName("удаляет автора по Id")
    @Test
    void shoulddeleteById() {
        authorDaoJdbc.deleteById(NECKTOFOR_ID);
        int count = authorDaoJdbc.count();
        assertThat(count).isEqualTo(EXPECTED_AUTHOR_COUNT-1);
    }

}