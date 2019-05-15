INSERT INTO roles (id,name) VALUES (1, 'ROLE_MAIN_ADMIN');
INSERT INTO roles (id,name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO roles (id,name) VALUES (3, 'ROLE_REG');

-- glavniadmin
INSERT INTO users (username,enabled, is_verified, password, first_name, last_name, email,company_id, numF) VALUES ('admin',1,1,
  '1000:5b4240333736646261:d1a3c576fa4b36b417878e6f2dc819192e024c8bfce6b4b5d3526039d755994a017c26656d903dc7beba73b1ce857698265c8421020442799c72995b728043be',
                                                                                                  'Admin', 'Admin', 'mailad@mail.com',1,0);
INSERT INTO roles_users (roles_id, users_id) VALUES (1, 1);

INSERT INTO company(id,name,file_path) values (1,'root','keystores/root.p12');

INSERT INTO certificate(id,alias,end_date,signed_by_alias,signed_by_serial_number,serial_number,start_date,is_leaf,company_id,revoked) values (1,'root','2019-08-31 02:00:00','root','1','1','2019-03-22 01:00:00',0,1,0);

