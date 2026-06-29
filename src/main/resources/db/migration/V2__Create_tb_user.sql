CREATE TABLE `_user` (
                         id int primary key auto_increment,
                         username VARCHAR(64) unique not null,
                         email varchar(120) unique not null,
                         password_hash varchar(255) not null,
                         user_type BIGINT,
                         registration varchar(6) not null unique,
                         is_enabled boolean default true,

                         CONSTRAINT fk_user_role FOREIGN KEY (user_type) REFERENCES `_user_type` (id)
);