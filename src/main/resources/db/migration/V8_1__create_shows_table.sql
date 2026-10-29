CREATE TABLE shows (
    id SERIAL PRIMARY KEY,
    slug VARCHAR(60) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    poster_url VARCHAR(255) DEFAULT NULL,
    bookable BOOLEAN NOT NULL,
    price DECIMAL(10,2) DEFAULT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    updated_at TIMESTAMP(6) DEFAULT NULL,
    location_id INT DEFAULT NULL,

-- Contraintes pour la table "shows"
    CONSTRAINT fk_shows_location
        FOREIGN KEY (location_id)
        REFERENCES locations(id)
);

-- Index pour la table "shows"
CREATE INDEX idx_shows_location_id ON shows(location_id);