insert into genres (id, name)
values (1,'fantastic'),(2, 'detective'),(3, 'adventure');

insert into authors (name, id)
values ('Pushkin',1),('Lermontov',2),('Tutchev',3);

insert into comments (text,id)
values ('Don`t read',1),('so so',2),('good',3),
('bad reading',4),('good reading',5),('the best',6);

insert into books (title, id, description)
values ('Book1', 1, 'description1'),('Book2', 2, 'description2'),('Book3', 3, 'description3'),
('Book4', 4, 'description'),('Book5', 5, 'description'),('Book6', 6, 'description'),
('Book7', 7, 'description'),('Book8', 8, 'description'),('Book9', 9, 'description'),
('Book10', 10, 'description'),('Book11', 11, 'description'),('Book12', 12, 'description');

insert into books_authors (book_id, author_id)
values (1, 1),(1, 3),(2, 2),(2, 1),(3, 3),(3, 2)
,(4, 1),(4, 3),(5, 2),(5, 1),
(6, 3),(6, 2),(7, 1),(7, 3),
(8, 2),(8, 1),(9, 3),(9, 2),
(10, 1),(10, 3),(11, 2),(11, 1),
(12, 3),(12, 2);

insert into books_genres (book_id, genre_id)
values (1, 1),(2, 1),(3, 1)
,(4, 2),(5, 1),(6, 1),(7, 1),(8, 3),(9, 1),(10, 2),(11, 3),(12, 2);

insert into books_comments (book_id, comment_id)
values (1, 1),(2, 2),(3, 3),(4, 2)
,(5, 1),(6, 1),(7, 1),(8, 1),(9, 1),(10, 1),(11, 1),(12, 1);

-- REPLACE INTO `roles` VALUES (1,'ADMIN');

-- нужно для работы acl ---------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------


INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'admin'),
(2, 1, 'user'),
(3, 0, 'ROLE_EDITOR');

INSERT INTO acl_class (id, class) VALUES
(1, 'ru.otus.spring.models.Book');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 3, 0),
(2, 1, 2, NULL, 3, 0),
(3, 1, 3, NULL, 3, 0),
(4, 1, 4, NULL, 3, 0),
(5, 1, 5, NULL, 3, 0),
(6, 1, 6, NULL, 3, 0),
(7, 1, 7, NULL, 3, 0),
(8, 1, 8, NULL, 3, 0),
(9, 1, 9, NULL, 3, 0),
(10, 1, 10, NULL, 3, 0),
(11, 1, 11, NULL, 3, 0),
(12, 1, 12, NULL, 3, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 1, 2, 1, 2, 1, 1, 1),
(3, 1, 3, 3, 1, 1, 1, 1),
(4, 2, 1, 1, 1, 1, 1, 1),
(5, 2, 2, 2, 1, 1, 1, 1),
(6, 3, 1, 3, 1, 1, 1, 1),

(7, 4, 1, 1, 2, 1, 1, 1),
(8, 5, 1, 2, 2, 1, 1, 1),
(9, 6, 1, 3, 2, 1, 1, 1),
(10, 7, 1, 1, 2, 1, 1, 1),
(11, 8, 1, 2, 2, 1, 1, 1),
(12, 9, 1, 3, 2, 1, 1, 1),
(13, 10, 1, 1, 2, 1, 1, 1),
(14, 11, 1, 2, 2, 1, 1, 1),
(15, 12, 1, 3, 2, 1, 1, 1);