package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GenreDaoJdbcTest {

    private static final int EXPECTED_GENRES_COUNT = 2;
    private static final long FANTSTIC_ID = 1L;
    private static final String GENRE_NAME = "fantastic";

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedGenreCount() {
        int count = genreDaoJdbc.count();
        assertThat(count).isEqualTo(EXPECTED_GENRES_COUNT);
    }


    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldsave() {
        Genre expectedGenre = new Genre( "new1");
        expectedGenre = genreDaoJdbc.save(expectedGenre);
        Genre actualGenre = genreDaoJdbc.getById(expectedGenre.getId());
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый жанр")
    @Test
    void shouldgetById() {
        Genre actualGenre = genreDaoJdbc.getById(FANTSTIC_ID);
        assertThat(actualGenre)
                .hasFieldOrPropertyWithValue("id", FANTSTIC_ID)
                .hasFieldOrPropertyWithValue("name", GENRE_NAME);
    }

    @DisplayName("возвращать ожидаемый жанр по Id")
    @Test
    void shouldgetIdByName() {
        int expId = genreDaoJdbc.getIdByName(GENRE_NAME);
        assertThat(expId).isEqualTo(FANTSTIC_ID);
    }

    @DisplayName("обновляет наименование жанра")
    @Test
    void shouldupdate() {
        Genre newGenre = new Genre("new Genre");
        newGenre.setId(FANTSTIC_ID);
        genreDaoJdbc.update(newGenre);
        Genre expGenre = genreDaoJdbc.getById(FANTSTIC_ID);
        assertThat(expGenre.getName()).isEqualTo("new Genre");
    }

    @DisplayName("получает все жанры")
    @Test
    void shouldgetAll() {
        List<Genre> genres = genreDaoJdbc.getAll();
        assertThat(genres.size()).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("удаляет жанр по Id")
    @Test
    void shoulddeleteById() {
        genreDaoJdbc.deleteById(FANTSTIC_ID);
        int count = genreDaoJdbc.count();
        assertThat(count).isEqualTo(EXPECTED_GENRES_COUNT-1);
    }
}