insert into genres (id, name)
values (1,'fantastic'),(2, 'detective'),(3, 'adventure');

insert into authors (name, id)
values ('Pushkin',1),('Lermontov',2),('Tutchev',3);

insert into books (title, id)
values ('Book1', 1),('Book2', 3),('Book3', 2);

insert into books_authors (book_id, author_id)
values (1, 1),(1, 3),(2, 2);

insert into books_genres (book_id, genre_id)
values (1, 1),(2, 1),(3, 1);