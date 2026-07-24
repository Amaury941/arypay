CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    email varchar(255) NOT NULL UNIQUE,
    password_hash varchar(255) NOT NULL,
    role varchar(8) NOT NULL,
    created_at TIMESTAMP NOT NULL default now()
);