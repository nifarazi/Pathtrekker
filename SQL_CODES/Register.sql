CREATE DATABASE IF NOT EXISTS register;


USE register;


CREATE TABLE IF NOT EXISTS user (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    first_name VARCHAR(50) NOT NULL,
                                    last_name VARCHAR(50) NOT NULL,
                                    username VARCHAR(50) NOT NULL UNIQUE,
                                    email VARCHAR(100) NOT NULL UNIQUE,
                                    password VARCHAR(255) NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO user (first_name, last_name, username, email, password)
VALUES
    ('Ashfia', 'Tabassum', 'ashfia', 'ashfia@example.com', 'password123'),
    ('Nanjiba', 'Farazi', 'nanjiba', 'nanjiba@example.com', 'password456');
SELECT* FROM user;


CREATE TABLE admin_schema (
                              admin_index INT AUTO_INCREMENT,
                              admin_id VARCHAR(50) NOT NULL,
                              password VARCHAR(100) NOT NULL,
                              PRIMARY KEY (admin_id),
                              UNIQUE (admin_index)
);

INSERT INTO admin_schema (admin_id, password) VALUES
                                                  ('admin1', 'password123'),
                                                  ('admin2', 'password456'),
                                                  ('admin3', 'password789');

SELECT * FROM admin_schema;