SELECT 'CREATE DATABASE userdb' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'userdb');
SELECT 'CREATE DATABASE tripdb' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'tripdb');
SELECT 'CREATE DATABASE notificationdb' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'notificationdb');
GRANT ALL PRIVILEGES ON DATABASE userdb TO admin;
GRANT ALL PRIVILEGES ON DATABASE tripdb TO admin;
GRANT ALL PRIVILEGES ON DATABASE notificationdb TO admin;
\c userdb;
CREATE TABLE IF NOT EXISTS passengers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT now()
);
CREATE TABLE IF NOT EXISTS drivers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    license_number VARCHAR(50),
    status VARCHAR(20) DEFAULT 'FREE',
    created_at TIMESTAMP DEFAULT now()
);