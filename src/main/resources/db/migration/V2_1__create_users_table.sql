-- 1. Création du type ENUM
CREATE TYPE user_role AS ENUM ('ADMIN', 'MEMBER', 'AFFILIATE', 'PRESS', 'PRODUCER', '');

-- 2. Création de la table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(60) NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(60) NOT NULL,
    lastname VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL,
    langue VARCHAR(2) NOT NULL,
    role user_role NOT NULL,
    created_at TIMESTAMP NOT NULL
);