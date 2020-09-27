
CREATE TABLE AUTHORS (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE GENRES (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE BOOKS (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    author_id BIGINT,
    genre_id BIGINT,
    FOREIGN KEY (genre_id) REFERENCES GENRES(id)
);

CREATE TABLE BOOKS_AUTHORS(
    book_id bigint references books(id) on delete cascade,
    author_id bigint references authors(id),
    primary key (book_id, author_id)
);