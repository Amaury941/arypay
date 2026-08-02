CREATE TABLE IF NOT EXISTS transactions (
    id UUID PRIMARY KEY,
    sender UUID NOT NULL,
    receiver UUID NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL default now()
);