INSERT INTO artists (id, firstname, lastname) VALUES
    (1, 'Daniel', 'Marcelin'),
    (2, 'Philippe', 'Laurent'),
    (3, 'Marius', 'Von Mayenburg'),
    (4, 'Olivier', 'Boudon'),
    (5, 'Anne Marie', 'Loop'),
    (6, 'Pietro', 'Varasso'),
    (7, 'Laurent', 'Caron'),
    (8, 'Élena', 'Perez'),
    (9, 'Guillaume', 'Alexandre'),
    (10, 'Claude', 'Semal'),
    (11, 'Laurence', 'Warin'),
    (12, 'Pierre', 'Wayburn'),
    (13, 'Gwendoline', 'Gauthier');

-- Pour fetcher le dernier id, sinon db non synchro et le add fail
SELECT setval('artists_id_seq', (SELECT MAX(id) FROM artists));