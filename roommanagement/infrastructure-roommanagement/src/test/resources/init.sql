CREATE SCHEMA IF NOT EXISTS roommanagement;

CREATE TABLE IF NOT EXISTS roommanagement.room_type (
                                                        id UUID PRIMARY KEY,
                                                        title VARCHAR(255) NOT NULL,
                                                        description TEXT,
                                                        price_for_night DECIMAL(10, 2) NOT NULL,
                                                        number_of_rooms INTEGER NOT NULL,
                                                        adult_capacity INTEGER NOT NULL,
                                                        child_capacity INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS roommanagement.room (
                                                   id UUID PRIMARY KEY,
                                                   room_type_id UUID NOT NULL,
                                                   room_number VARCHAR(50) UNIQUE NOT NULL,
                                                   CONSTRAINT fk_room_type
                                                       FOREIGN KEY (room_type_id)
                                                           REFERENCES roommanagement.room_type (id)
);

INSERT INTO roommanagement.room_type (
    id, title, description, price_for_night, number_of_rooms, adult_capacity, child_capacity
) VALUES (
             '11111111-1111-1111-1111-111111111111',
             'testTitle',
             'testDescription',
             10.00,
             1,
             2,
             1
         );

INSERT INTO roommanagement.room (
    id, room_type_id, room_number
) VALUES (
             '11111111-1111-1111-1111-111111111111',
             '11111111-1111-1111-1111-111111111111',
             '101'
         );
