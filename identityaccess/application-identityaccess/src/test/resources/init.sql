create schema if not exists identityaccess;

create table if not exists identityaccess.users (
                                      id UUID PRIMARY KEY,
                                      email VARCHAR(255) UNIQUE NOT NULL,
                                      password VARCHAR(255) NOT NULL,
                                      role VARCHAR(255) NOT NULL
);