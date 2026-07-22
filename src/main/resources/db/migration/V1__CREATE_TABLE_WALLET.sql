CREATE TABLE wallets (
    id UUID PRIMARY KEY,
    balance DECIMAL (15,2),
    user_id varchar(255) NOT NULL
)