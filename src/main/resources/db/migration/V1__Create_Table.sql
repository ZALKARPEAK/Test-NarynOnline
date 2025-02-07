-- Flyway Migration Script: V1__Create_Categories_Users_And_Houses.sql

-- Drop types if they exist (using IF EXISTS for safety)
DROP TYPE IF EXISTS role CASCADE;

-- Drop sequences if they exist (using IF EXISTS for safety)
DROP SEQUENCE IF EXISTS users_id_seq CASCADE;
DROP SEQUENCE IF EXISTS tours_id_seq CASCADE;

-- Drop tables if they exist (using IF EXISTS for safety)
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS tours CASCADE;
DROP TABLE IF EXISTS houses CASCADE;
DROP TABLE IF EXISTS categories CASCADE;

create type role as enum ('ADMIN', 'USER');

-- Create tables
CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO categories (name) VALUES
                                  ('Авто'),
                                  ('Работа'),
                                  ('Недвижимость'),
                                  ('Услуги')
    ON CONFLICT (name) DO NOTHING;

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       latitude DOUBLE PRECISION,
                       longitude DOUBLE PRECISION,
                       role role NOT NULL
);

CREATE TABLE houses (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        price DECIMAL(15, 2) NOT NULL,
                        location VARCHAR(255),
                        category_id INTEGER REFERENCES categories(id),
                        user_id INTEGER REFERENCES users(id),
                        latitude DOUBLE PRECISION,
                        longitude DOUBLE PRECISION,
                        imagePath TEXT,
                        created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT (NOW() AT TIME ZONE 'utc'),
                        enabled BOOLEAN NOT NULL DEFAULT TRUE
);

-- Add foreign key constraints
ALTER TABLE houses
    ADD CONSTRAINT fk_houses_categories FOREIGN KEY (category_id) REFERENCES categories(id);

ALTER TABLE houses
    ADD CONSTRAINT fk_houses_users FOREIGN KEY (user_id) REFERENCES users(id);