INSERT INTO users (id, login, password, firstname, lastname, email, langue, role, created_at) VALUES
    (1, 'bob', '12345678', 'Bob', 'Sull', 'bob@sull.com','fr','ADMIN', NOW()),
    (2, 'anna', '12345678', 'Anna', 'Lyse', 'anna.lyse@sull.com','en','MEMBER', NOW());

SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
