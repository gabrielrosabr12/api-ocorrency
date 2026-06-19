CREATE TABLE attachments (
                             id BIGINT not null auto_increment primary key,
                             file_path varchar(300),
                             created_at date
);