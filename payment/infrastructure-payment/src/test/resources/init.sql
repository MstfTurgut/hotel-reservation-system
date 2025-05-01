CREATE SCHEMA IF NOT EXISTS payment;

CREATE TABLE IF NOT EXISTS payment.payment (
                       id UUID PRIMARY KEY,
                       transaction_id UUID UNIQUE,
                       reservation_id UUID NOT NULL,
                       amount DECIMAL(10, 2) NOT NULL,
                       create_date TIMESTAMP NOT NULL,
                       status VARCHAR(50) NOT NULL
);

INSERT INTO payment.payment (
    id,
    transaction_id,
    reservation_id,
    amount,
    create_date,
    status
) VALUES (
             '11111111-1111-1111-1111-111111111111',
             '11111111-1111-1111-1111-111111111111',
             '11111111-1111-1111-1111-111111111111',
             10.00,
             NOW(),
             'COMPLETED'
         );