CREATE SCHEMA IF NOT EXISTS notification;

CREATE SCHEMA IF NOT EXISTS notification;

CREATE TABLE notification.notification (
                                           id UUID PRIMARY KEY,
                                           reservation_id UUID NOT NULL,
                                           content TEXT NOT NULL,
                                           created_at TIMESTAMP NOT NULL,
                                           type VARCHAR(50) NOT NULL
);
