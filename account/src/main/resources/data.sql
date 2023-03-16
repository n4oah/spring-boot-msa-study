INSERT INTO account_auth_role (name) VALUES ('User');
INSERT INTO account_auth_role (name) VALUES ('Admin');

INSERT INTO account (
    id, created_date, updated_at, name, password, email
) VALUES (
  1, '2023-03-02 15:29:43.990386 +00:00', '2023-03-02 15:29:43.990386 +00:00', '김호진', '$2a$10$qXg02iKGG3PHQwWExlEfOe3NbhJ92GZkdh6PlyR2loOgpys.5wLQ6', 'n4oahdev@gmail.com'
);
INSERT INTO account_auth (
    account_id,
    role_name
) VALUES (
    1,
    'User'
);