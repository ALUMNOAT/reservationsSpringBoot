INSERT INTO types (id, type) VALUES
    (1, 'auteur'),
    (2, 'scenographe'),
    (3, 'comedien');

SELECT setval('types_id_seq', (SELECT MAX(id) FROM types));
