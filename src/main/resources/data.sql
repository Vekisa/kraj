INSERT INTO roles (id,name) VALUES (1, 'ROLE_MAIN_ADMIN');
INSERT INTO roles (id,name) VALUES (2, 'ROLE_ADMIN');

-- glavniadmin
INSERT INTO users (username,enabled, password, first_name, last_name, email) VALUES ('mainmain',1, '$2a$10$eukKXJfuGd8.ab3sZgRO9eWfWhwVP3CGTZ3U.WRjb9FXLTgQW7o/W', 'Admin', 'Admin', 'mailad@mail.com');
INSERT INTO roles_users (roles_id, users_id) VALUES (1, 1);