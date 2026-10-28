INSERT INTO roles (id, role) VALUES
    (1, 'admin'),
    (2, 'member'),
    (3, 'affiliate');

SELECT setval('roles_id_seq', (SELECT MAX(id) FROM roles));
