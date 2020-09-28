DROP TABLE IF EXISTS GENRES;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS AUTHORS;

CREATE TABLE AUTHORS (
    id bigint AUTO_INCREMENT,
    name VARCHAR(255),
    primary key (id)
);

CREATE TABLE GENRES (
    id bigint AUTO_INCREMENT,
    name VARCHAR(255),
    primary key (id)
);

CREATE TABLE BOOKS (
    id bigint AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(255),
    primary key (id)
);

CREATE TABLE BOOKS_AUTHORS(
    book_id bigint references books(id) on delete cascade,
    author_id bigint references authors(id) on delete cascade
--     primary key (book_id, author_id)
);

CREATE TABLE BOOKS_GENRES(
    book_id bigint references books(id) on delete cascade,
    genre_id bigint references genres(id) on delete cascade
--     primary key (book_id, genre_id)
);