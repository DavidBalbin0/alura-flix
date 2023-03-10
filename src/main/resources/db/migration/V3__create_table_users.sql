CREATE TABLE users (
                       id INT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(20) NOT NULL
);