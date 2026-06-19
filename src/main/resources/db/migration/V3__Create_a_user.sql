-- 1. Primeiro, garanta que a role exista (Ajuste o nome da coluna conforme sua tabela _user_type)
INSERT INTO `_user_type` (id, type)
VALUES (3, 'administrator');

INSERT INTO `_user` (username, email,password_hash,user_type,registration)
VALUES (
      'monitor',
      'gabrielrosaobr@gmail.com',
      '{bcrypt}$2a$10$z.FERwVSZwOWZhzT1KFN1u9nttctSahE8fr6L1UJ40TltdqMLWbPC',
      3,
        '000000'
        );