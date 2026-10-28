INSERT INTO localities (id, postal_code, locality) VALUES
    (1, 1000, 'Bruxelles'),
    (2, 1020, 'Laeken'),
    (3, 1030, 'Schaerbeek'),
    (4, 1170, 'Watermael-Boistfort');

SELECT setval('localities_id_seq', (SELECT MAX(id) FROM localities));
