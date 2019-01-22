insert into roles (id, name) values (1, 'ADMIN');
insert into roles (id, name) values (2, 'USER');

insert into users (active, birth_date, first_name, last_name, password, username, role_id) values (true, '1995-01-28', 'Tomek', 'Laz', '$2a$10$mn4d0KoLAS1SzBs2U/sU4eUJV4lV2t201OkKRrU07M0NFGiXYPvz2', 'tomeklazXD', 2);
insert into users (active, birth_date, first_name, last_name, password, username, role_id) values (true, '1995-02-28', 'Mariusz', 'Basista', '$2a$10$hhNmpfEbwjtKynOAC.GiDuqvrZD4ikz3kcgb8WvFCdjUxrNHJh1EW', 'XDDD', 1);
