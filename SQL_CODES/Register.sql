-- Create the database
CREATE DATABASE IF NOT EXISTS register;

-- Use the database
USE register;

-- Create the user table
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for the user
    first_name VARCHAR(50) NOT NULL,   -- First name of the user
    last_name VARCHAR(50) NOT NULL,    -- Last name of the user
    username VARCHAR(50) NOT NULL UNIQUE, -- Username (must be unique)
    email VARCHAR(100) NOT NULL UNIQUE, -- Email (must be unique)
    password VARCHAR(255) NOT NULL,    -- Hashed password
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Creation timestamp
);

-- Example of inserting sample data (optional)
INSERT INTO user (first_name, last_name, username, email, password)
VALUES 
('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123'),
('Jane', 'Smith', 'janesmith', 'janesmith@example.com', 'password456');
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