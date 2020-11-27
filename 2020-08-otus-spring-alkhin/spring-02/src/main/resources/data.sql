insert into genres (id, name)
values (1,'fantastic'),(2, 'detective'),(3, 'adventure');

insert into authors (name, id)
values ('Pushkin',1),('Lermontov',2),('Tutchev',3);

-- insert into comments (text)
-- values ('Don`t read'),('so so'),('good');
-- ('bad reading',1),('good reading',2),('the best',3);

insert into books (title, id, description)
values ('Book1', 1, 'description'),('Book2', 3, 'description'),('Book3', 2, 'description'),
('Book4', 4, 'description'),('Book4', 5, 'description'),('Book4', 6, 'description'),
('Book7', 7, 'description'),('Book8', 8, 'description'),('Book9', 9, 'description'),
('Book10', 10, 'description'),('Book11', 11, 'description'),('Book12', 12, 'description');

insert into books_authors (book_id, author_id)
values (1, 1),(1, 3),(2, 2),(2, 1),(3, 3),(3, 2),
(4, 1),(4, 3),(5, 2),(5, 1),
(6, 3),(6, 2),(7, 1),(7, 3),
(8, 2),(8, 1),(9, 3),(9, 2),
(10, 1),(10, 3),(11, 2),(11, 1),
(12, 3),(12, 2);

insert into books_genres (book_id, genre_id)
values (1, 1),(2, 1),(3, 1),(4, 2)
,(5, 1),(6, 1),(7, 1),(8, 1),(9, 1),(10, 1),(11, 1),(12, 1);

-- insert into books_comments (book_id, comment_id)
-- values (1, 1),(2, 1),(3, 1),(4, 2)
-- ,(5, 1),(6, 1),(7, 1),(8, 1),(9, 1),(10, 1),(11, 1),(12, 1);

-- нужно для работы acl ---------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------


INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'admin'),
(2, 1, 'user'),
(3, 0, 'ROLE_EDITOR');

INSERT INTO acl_class (id, class) VALUES
(1, 'ru.otus.spring.models.Book');

INSERT INTO system_message(id,content) VALUES
(1,'First Level Message'),
(2,'Second Level Message'),
(3,'Third Level Message');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 3, 0),
(2, 1, 2, NULL, 3, 0),
(3, 1, 3, NULL, 3, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 1, 2, 1, 2, 1, 1, 1),
(3, 1, 3, 3, 1, 1, 1, 1),
(4, 2, 1, 2, 1, 1, 1, 1),
(5, 2, 2, 3, 1, 1, 1, 1),
(6, 3, 1, 3, 1, 1, 1, 1),
(7, 3, 2, 3, 2, 1, 1, 1);