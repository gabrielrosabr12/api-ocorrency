CREATE TABLE `_user_type` (
    id int primary key auto_increment,
    type VARCHAR(255) unique not null
);


CREATE TABLE `_user` (
    id int primary key,
    username VARCHAR(64) unique not null,
    email varchar(120) unique not null,
    password_hash varchar(255) not null,
    user_role BIGINT,

    CONSTRAINT fk_user_role FOREIGN KEY (user_role) REFERENCES `_user_type` (id)
);