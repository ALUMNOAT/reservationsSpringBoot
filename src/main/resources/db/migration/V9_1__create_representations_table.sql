CREATE TABLE representations (
    id SERIAL PRIMARY KEY,
    show_id INT NOT NULL,
    location_id INT DEFAULT NULL,
    "when" TIMESTAMP(6) NOT NULL,

    -- Contraintes de clés étrangères
    CONSTRAINT representations_location_id_fk
        FOREIGN KEY (location_id)
        REFERENCES locations (id)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    CONSTRAINT representations_show_id_fk
        FOREIGN KEY (show_id)
        REFERENCES shows (id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Index pour optimiser les recherches sur les clés étrangères
CREATE INDEX idx_representations_location_id ON representations(location_id);
CREATE INDEX idx_representations_show_id ON representations(show_id);