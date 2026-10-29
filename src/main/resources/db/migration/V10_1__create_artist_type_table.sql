CREATE TABLE artist_type (
    id SERIAL PRIMARY KEY,
    artist_id INT NOT NULL,
    type_id INT NOT NULL,

    -- Contraintes de clés étrangères
    CONSTRAINT artist_type_artist_id_fk
        FOREIGN KEY (artist_id)
        REFERENCES artists (id)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    CONSTRAINT artist_type_type_id_fk
        FOREIGN KEY (type_id)
        REFERENCES types (id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Index pour optimiser les recherches sur les clés étrangères
CREATE INDEX idx_artist_type_artist_id ON artist_type(artist_id);
CREATE INDEX idx_artist_type_type_id ON artist_type(type_id);