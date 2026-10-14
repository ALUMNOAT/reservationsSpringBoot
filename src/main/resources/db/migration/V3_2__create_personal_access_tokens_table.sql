-- Structure de la table personal_access_tokens
CREATE TABLE personal_access_tokens (
    id SERIAL PRIMARY KEY,
    token UUID NOT NULL,
    user_id INTEGER NOT NULL,
    expires_at TIMESTAMP NOT NULL
);

-- Index pour la table personal_access_tokens
ALTER TABLE personal_access_tokens ADD CONSTRAINT personal_access_tokens_token_key UNIQUE (token);
CREATE INDEX idx_personal_access_tokens_user_id ON personal_access_tokens(user_id);