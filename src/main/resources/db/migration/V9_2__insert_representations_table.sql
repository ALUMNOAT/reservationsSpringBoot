INSERT INTO representations (id, show_id, location_id, "when") VALUES
(1, 1, 1, '2012-10-12 13:30:00'),
(2, 1, 2, '2012-10-12 20:30:00'),
(3, 2, NULL, '2012-10-02 20:30:00'),
(4, 3, NULL, '2012-10-16 20:30:00');


SELECT setval('representations_id_seq', (SELECT MAX(id) FROM representations));