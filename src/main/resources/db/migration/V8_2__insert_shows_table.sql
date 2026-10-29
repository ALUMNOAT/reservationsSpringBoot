INSERT INTO shows (id, slug, title, description, poster_url, bookable, price, created_at, updated_at, location_id) VALUES
(1, 'ayiti', 'Ayiti', 'Un homme est bloqué à l’aéroport.' || chr(10) || ' Questionné par les douaniers, il doit alors justifier son identité, et surtout prouver qu''il est haïtien – qu''est-ce qu''être haïtien ?', 'ayiti.jpg', TRUE, 9.00, '2020-04-21 19:08:44.583000', NULL, 1),
(2, 'cible-mouvante', 'Cible mouvante', 'Dans ce « thriller d’anticipation », des adultes semblent alimenter et véhiculer une crainte féroce envers les enfants âgés entre 10 et 12 ans.', 'cible.jpg', TRUE, 9.00, '2020-04-21 19:08:53.156000', NULL, 2),
(3, 'ceci-nest-pas-un-chanteur-belge', 'Ceci n''est pas un chanteur belge', 'Non peut-être ?!' || chr(10) || 'Entre Magritte (pour le surréalisme comique) et Maigret (pour le réalisme mélancolique), ce dixième opus semalien propose quatorze nouvelles chansons mêlées à de petits textes humoristiques et à quelques fortes images poétiques.', 'claudebelgesaison220.jpg', TRUE, 5.50, '2020-04-21 19:08:53.189000', '2020-04-21 19:08:53.189000', NULL),
(4, 'manneke', 'Manneke… !', 'A tour de rôle, Pierre se joue de ses oncles, tantes, grands-parents et surtout de sa mère.', 'wayburn.jpg', TRUE, 10.50, '2020-04-21 19:09:02.426000', '2020-04-21 19:09:02.426000', 3);


SELECT setval('shows_id_seq', (SELECT MAX(id) FROM shows));