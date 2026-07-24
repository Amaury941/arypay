CREATE TABLE IF NOT EXISTS transactions (
    id UUID PRIMARY KEY,
    sender varchar(255) NOT NULL,
    receiver varchar(255) NOT NULL,
    amount varchar(255) NOT NULL,
    created_at TIMESTAMP NOT NULL default now()
);