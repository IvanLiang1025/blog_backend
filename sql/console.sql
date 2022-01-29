
/*
 create database sunnyblog with owner db_user
 */
-- drop database if exists sunnyblog;
-- CREATE database sunnyblog with owner = db_user encoding 'UTF-8' TABLESPACE = pg_default;


Create sequence blog_id_seq start 10001;
create table article(
    article_id BIGINT DEFAULT nextval('blog_id_seq'::regclass) NOT NULL,

    status SMALLINT DEFAULT 1,
    title varchar(100) NOT NULL,
--     description varchar(2000)
    content text NOT NULL,
    first_picture varchar(255) DEFAULT NULL,
    view_count INT DEFAULT 0,
    user_id BIGINT DEFAULT NULL,
    category_id BIGINT DEFAULT NULL,

    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (article_id)
);

insert into article (article_id, title, content,  view_count, user_id, category_id, create_date, update_date, status)
values (10001, 'This is a test', 'the content of this article', 0, 1,1,'2021-07-20', '2021-07-20',1);


create sequence user_id_seq start 100001;
create table blog_user (
    user_id BIGINT DEFAULT nextval('user_id_seq'::regclass) NOT NULL,
    username varchar(100) NOT NULL,
    password varchar (100) NOT NULL,
    nickname varchar (100) NOT NULL,
    email varchar (100) NOT NULL,
    status SMALLINT default 0,
    role SMALLINT default 0,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id)
);

insert into blog_user (user_id, username, password, email, role, nickname, create_date, update_date)
values (100001, 'ivan', '123', 'ivanliang25@gmail.com', 0, 'ivan', '2021-07-21', '2021-07-21');

-- table for category
create sequence category_id_seq start 1;
create table category (
    category_id BIGINT DEFAULT nextval('category_id_seq'::regclass) NOT NULL,
    name varchar (255) NOT NULL,
    PRIMARY KEY(category_id)
);


ALTER table article add column description varchar(2000) NULL;
ALTER table article ALTER column status SET DEFAULT 0;

ALTER table blog_user add column avatar varchar(400) NULL;

create sequence comment_id_seq start 1;
create table comment (
    comment_id BIGINT DEFAULT nextval('comment_id_seq'::regclass) NOT NULL,
    article_id BIGINT DEFAULT NULL,
    nickname varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    content varchar(1000) NOT NULL,
    avatar varchar(100) DEFAULT NULL,
    status SMALLINT DEFAULT 0,
    parent_comment_id BIGINT DEFAULT NULL,
    parent_comment_nickname varchar(100) NULL,
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    PRIMARY KEY (comment_id)
);
-- add a type for article, representing original,
ALTER TABLE article ADD COLUMN type SMALLINT DEFAULT 1;

ALTER TABLE comment ADD COLUMN poster_role SMALLINT DEFAULT 0;

