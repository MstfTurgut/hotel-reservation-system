CREATE SCHEMA IF NOT EXISTS reservation;

CREATE TABLE IF NOT EXISTS reservation.reservation (
                 id UUID PRIMARY KEY,
                 user_id UUID NOT NULL,
                 room_id UUID NOT NULL,
                 confirmation_code VARCHAR(255) UNIQUE NOT NULL,
                 reservation_code VARCHAR(255) UNIQUE NOT NULL,
                 adult_guest_count INTEGER NOT NULL,
                 child_guest_count INTEGER NOT NULL,
                 status VARCHAR(50) NOT NULL,
                 check_in_date DATE NOT NULL,
                 check_out_date DATE NOT NULL,
                 full_name VARCHAR(255) NOT NULL,
                 phone_number VARCHAR(50) NOT NULL,
                 email_address VARCHAR(255) NOT NULL,
                 created_at TIMESTAMP NOT NULL
);

INSERT INTO reservation.reservation (
    id,
    user_id,
    room_id,
    confirmation_code,
    reservation_code,
    adult_guest_count,
    child_guest_count,
    status,
    check_in_date,
    check_out_date,
    full_name,
    phone_number,
    email_address,
    created_at
) VALUES (
             '11111111-1111-1111-1111-111111111111', -- id
             '11111111-1111-1111-1111-111111111111', -- user_id
             '11111111-1111-1111-1111-111111111111', -- room_id
             '12345678',                             -- confirmation_code
             '12345678',                             -- reservation_code
             2,                                      -- adult_guest_count
             1,                                      -- child_guest_count
             'CONFIRMED',                            -- status
             '2030-01-01',                           -- check_in_date
             '2030-01-05',                           -- check_out_date
             'John Doe',                             -- full_name
             '1234567890',                          -- phone_number
             'johndoe@example.com',                 -- email_address
             NOW()                                   -- created_at
         );