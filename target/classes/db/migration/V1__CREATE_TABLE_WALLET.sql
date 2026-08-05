CREATE TABLE IF NOT EXISTS wallets (
    id UUID PRIMARY KEY,
    balance DECIMAL (15,2),
    holder UUID UNIQUE
);