CREATE TABLE localities (
    id SERIAL PRIMARY KEY,
    postal_code VARCHAR(6) NOT NULL,
    locality VARCHAR(30) NOT NULL
);