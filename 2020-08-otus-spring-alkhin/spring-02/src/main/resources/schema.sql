DROP TABLE IF EXISTS GENRES;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS COMMENTS;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ROLES;

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

CREATE TABLE COMMENTS (
    id bigint AUTO_INCREMENT,
    text VARCHAR(255),
    primary key (id)
);



CREATE TABLE BOOKS_AUTHORS(
    book_id bigint references BOOKS(id) on delete cascade,
    author_id bigint references AUTHORS(id) on delete cascade
);

CREATE TABLE BOOKS_GENRES(
    book_id bigint references BOOKS(id) on delete cascade,
    genre_id bigint references GENRES(id) on delete cascade
);

CREATE TABLE BOOKS_COMMENTS(
    book_id bigint references BOOKS(id) on delete cascade,
    comment_id bigint references COMMENTS(id) on delete cascade
);

CREATE TABLE USERS (
    user_id bigint AUTO_INCREMENT,
    active tinyint(1) NOT NULL,
    username VARCHAR(255),
    email  VARCHAR(255),
    password  VARCHAR(255),
    name  VARCHAR(255),
    lastname  VARCHAR(255),
    primary key (user_id)
);

CREATE TABLE ROLES (
    role_id bigint AUTO_INCREMENT,
    role VARCHAR(255),
    primary key (role_id)
);

CREATE TABLE USER_ROLE(
    user_id bigint references USERS(user_id) on delete cascade,
    role_id bigint references ROLES(role_id) on delete cascade
);


-- нужно для работы acl ---------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------
create table IF NOT EXISTS system_message (id integer not null, content varchar(255), primary key (id));

CREATE TABLE IF NOT EXISTS acl_sid (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  principal tinyint(1) NOT NULL,
  sid varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_1 (sid,principal)
);

CREATE TABLE IF NOT EXISTS acl_class (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  class varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_2 (class)
);

CREATE TABLE IF NOT EXISTS acl_entry (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  acl_object_identity bigint(20) NOT NULL,
  ace_order int(11) NOT NULL,
  sid bigint(20) NOT NULL,
  mask int(11) NOT NULL,
  granting tinyint(1) NOT NULL,
  audit_success tinyint(1) NOT NULL,
  audit_failure tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_4 (acl_object_identity,ace_order)
);

CREATE TABLE IF NOT EXISTS acl_object_identity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  object_id_class bigint(20) NOT NULL,
  object_id_identity bigint(20) NOT NULL,
  parent_object bigint(20) DEFAULT NULL,
  owner_sid bigint(20) DEFAULT NULL,
  entries_inheriting tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_3 (object_id_class,object_id_identity)
);

ALTER TABLE acl_entry
ADD FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity(id);

ALTER TABLE acl_entry
ADD FOREIGN KEY (sid) REFERENCES acl_sid(id);

--
-- Constraints for table acl_object_identity
--
ALTER TABLE acl_object_identity
ADD FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (object_id_class) REFERENCES acl_class (id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (owner_sid) REFERENCES acl_sid (id);
