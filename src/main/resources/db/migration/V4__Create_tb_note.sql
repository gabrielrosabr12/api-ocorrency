CREATE TABLE note (
                      id BIGINT not null auto_increment primary key ,
                      title varchar(255) not null,
                      body text,
                      created_by int,
                      created_at date,

                      CONSTRAINT fk_user FOREIGN KEY (created_by) REFERENCES `_user`(id)
);