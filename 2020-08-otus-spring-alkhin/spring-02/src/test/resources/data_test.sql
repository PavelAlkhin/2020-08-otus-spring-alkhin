insert into genres (id, name)
values (1,'fantastic'),(2, 'detective'),(3, 'adventure');

insert into authors (name, id)
values ('Pushkin',1),('Lermontov',2),('Tutchev',3),('Mayakovskiy',4);

insert into books (title, id)
values ('Book1', 1),('Book2', 3),('Book3', 2),
('Book4', 4),('Book4', 5),('Book4', 6),
('Book7', 7),('Book8', 8),('Book9', 9),
('Book10', 10),('Book11', 11),('Book12', 12);

insert into books_authors (book_id, author_id)
values (1, 1),(1, 3),(2, 2),(2, 1),(3, 3),(3, 2),
(4, 1),(4, 3),(5, 2),(5, 1),
(6, 3),(6, 2),(7, 1),(7, 3),
(8, 2),(8, 1),(9, 3),(9, 2),
(10, 1),(10, 3),(11, 2),(11, 1),
(12, 3),(12, 4);

insert into books_genres (book_id, genre_id)
values (1, 1),(2, 1),(3, 1),(4, 2)
,(5, 1),(6, 1),(7, 1),(8, 1),(9, 1),(10, 1),(11, 1),(12, 1);