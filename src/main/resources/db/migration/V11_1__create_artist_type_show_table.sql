CREATE TABLE artist_type_show (
    id SERIAL PRIMARY KEY,
    artist_type_id INT NOT NULL,
    show_id INT NOT NULL,

    -- Contraintes de clés étrangères
    CONSTRAINT fk_artist_type_show_artist_type
        FOREIGN KEY (artist_type_id)
        REFERENCES artist_type (id)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    CONSTRAINT fk_artist_type_show_show
        FOREIGN KEY (show_id)
        REFERENCES shows (id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Index pour optimiser les recherches sur les clés étrangères
CREATE INDEX idx_artist_type_show_artist_type_id ON artist_type_show(artist_type_id);
CREATE INDEX idx_artist_type_show_show_id ON artist_type_show(show_id);