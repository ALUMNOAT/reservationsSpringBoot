CREATE TABLE locations (
    id SERIAL PRIMARY KEY,
    slug VARCHAR(60) NOT NULL UNIQUE,
    designation VARCHAR(60) NOT NULL,
    address VARCHAR(255) NOT NULL,
    locality_id INT NOT NULL,
    website VARCHAR(255) NULL,
    phone VARCHAR(30) NULL,

    -- Contraintes pour la table "locations"
    CONSTRAINT fk_locations_locality
        FOREIGN KEY (locality_id)
        REFERENCES localities(id)
);

-- Index pour la table "locations"
CREATE INDEX idx_locations_locality_id ON locations(locality_id)