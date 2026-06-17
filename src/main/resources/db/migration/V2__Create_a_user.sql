-- 1. Primeiro, garanta que a role exista (Ajuste o nome da coluna conforme sua tabela _user_type)
INSERT INTO `_user_type` (id, type)
VALUES (1, 'user');

INSERT INTO `_user` (id,username, email,password_hash,user_role)
VALUES (1,
      'monitor',
      'gabrielrosaobr@gmail.com',
      '{bcrypt}$2a$10$e0FG3DFxZ/txdF8R9R5Hme9zm6awv5p3cipwtMJVHvWtJka.0om5S',
      1
        );