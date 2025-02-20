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
    admin_index INT AUTO_INCREMENT,    hotels
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

CREATE TABLE IF NOT EXISTS hotel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    division VARCHAR(50) NOT NULL,
    price_range ENUM('low', 'mid', 'high') NOT NULL,
    available_rooms INT NOT NULL,
    amenities TEXT,
    nightly_rate DECIMAL(10,2) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

INSERT INTO hotel (name, division, price_range, available_rooms, amenities, nightly_rate, email, phone_number)
VALUES 
('Grand Palace', 'Dhaka', 'mid', 10, 'Free WiFi, Pool, Gym', 5000.00, 'contact@grandpalace.com', '+880123456789'),
('City Inn', 'Chattogram', 'mid', 20, 'Breakfast, WiFi', 6000.00, 'info@cityinn.com', '+880987654321'),
('Budget Stay', 'Sylhet', 'low', 30, 'WiFi, Parking', 4000.00, 'support@budgetstay.com', '+880192345678');
INSERT INTO hotel (name, division, price_range, available_rooms, amenities, nightly_rate, email, phone_number)
VALUES 
("HotelInn","Dhaka","mid",11,"Free WiFi",6000.00,"contact@hotelInn.com","+88096464232");
INSERT INTO hotel (name, division, price_range, available_rooms, amenities, nightly_rate, email, phone_number) 
VALUES 
-- Dhaka Division
('Pan Pacific Sonargaon', 'Dhaka', 'high', 15, 'Pool, Free WiFi, Gym, Spa', 12000.00, 'contact@sonargaon.com', '+88028129004'),
('The Westin Dhaka', 'Dhaka', 'high', 12, '5-Star, Rooftop Pool, Spa, Gym', 18000.00, 'contact@westin.com', '+88029891000'),
('InterContinental Dhaka', 'Dhaka', 'high', 10, 'Luxury Rooms, Fine Dining, Spa', 16000.00, 'contact@intercontinental.com', '+8802222260000'),
('Hotel 71', 'Dhaka', 'mid', 25, 'Free WiFi, Restaurant', 6000.00, 'info@hotel71.com', '+8801713333392'),
('Golden Tulip - The Grandmark Dhaka', 'Dhaka', 'mid', 18, 'Business Hotel, Gym, Free WiFi', 7500.00, 'info@goldentulip.com', '+880255065025'),
('Asia Hotel & Resort', 'Dhaka', 'low', 30, 'WiFi, Breakfast', 3500.00, 'support@asiahotel.com', '+8801556734897'),
('Hotel Ornate', 'Dhaka', 'low', 28, 'Budget Hotel, Free Breakfast, WiFi', 3200.00, 'contact@hotelornate.com', '+88029558245'),

-- Chattogram Division
('Radisson Blu Chattogram Bay View', 'Chattogram', 'high', 12, 'Infinity Pool, Spa, WiFi', 15000.00, 'contact@radissonblu.com', '+880312558555'),
('The Peninsula Chittagong', 'Chattogram', 'high', 10, 'Luxury Hotel, Pool, Gym', 13500.00, 'info@peninsula.com', '+8801766660011'),
('Hotel Agrabad', 'Chattogram', 'mid', 22, 'WiFi, Pool, Restaurant', 7000.00, 'info@hotelagrabad.com', '+88031713891'),
('Well Park Residence', 'Chattogram', 'mid', 20, 'WiFi, Free Breakfast, Free Parking', 5000.00, 'support@wellpark.com', '+880312850282'),
('Hotel Saint Martin Ltd', 'Chattogram', 'low', 30, 'WiFi, Budget Rooms, Free Breakfast', 3000.00, 'support@saintmartin.com', '+880312711135'),
('Asian SR Hotel', 'Chattogram', 'low', 26, 'WiFi, Restaurant, Airport Shuttle', 2800.00, 'info@asiansr.com', '+880312873445'),

-- Sylhet Division
('Grand Sylhet Hotel & Resort', 'Sylhet', 'high', 10, 'Golf, Spa, Free WiFi', 14000.00, 'contact@grandsylhet.com', '+880821728468'),
('Rose View Hotel', 'Sylhet', 'mid', 18, 'WiFi, Restaurant, Rooftop Pool', 6000.00, 'info@roseview.com', '+880821721439'),
('Hotel Noorjahan Grand', 'Sylhet', 'low', 22, 'WiFi, Free Breakfast', 3500.00, 'support@noorjahan.com', '+8801777760323'),
('The Grand Sultan Tea Resort & Golf', 'Sylhet', 'high', 8, 'Luxury Resort, Spa, Golf', 20000.00, 'info@grandsultan.com', '+8801712214028'),

-- Rajshahi Division
('Hotel X Rajshahi', 'Rajshahi', 'high', 8, 'Luxury Rooms, WiFi, Free Breakfast', 11000.00, 'contact@hotelx.com', '+880721774555'),
('Sky View Hotel', 'Rajshahi', 'mid', 15, 'WiFi, Restaurant', 5000.00, 'info@skyview.com', '+8801715442165'),
('Hotel Star International', 'Rajshahi', 'low', 20, 'WiFi, Free Parking', 3000.00, 'support@hotelstar.com', '+8801764485442'),

-- Khulna Division
('Tiger Garden International', 'Khulna', 'high', 9, 'Pool, Gym, Free WiFi', 10000.00, 'contact@tigergarden.com', '+88041723500'),
('City Inn Khulna', 'Khulna', 'mid', 20, 'WiFi, Restaurant, Parking', 5500.00, 'info@cityinnkhulna.com', '+88041813837'),
('Hotel Castle Salam', 'Khulna', 'low', 25, 'WiFi, Free Breakfast', 3200.00, 'support@castlesalam.com', '+88041722199'),

-- Barishal Division
('Grand Park Hotel', 'Barishal', 'high', 10, 'WiFi, Free Breakfast, Parking', 9500.00, 'contact@grandparkbarishal.com', '+8801717755088'),
('Sikder Resort & Villas', 'Barishal', 'mid', 14, 'WiFi, Pool, Restaurant', 5800.00, 'info@sikderresort.com', '+8801717500470'),
('Hotel The Atlantic', 'Barishal', 'low', 18, 'WiFi, Free Parking', 2800.00, 'support@atlantic.com', '+8801716012345'),

-- Rangpur Division
('Rangpur Hotel & Resort', 'Rangpur', 'high', 10, 'Luxury Suites, Pool, Free WiFi', 9000.00, 'contact@rangpurresort.com', '+88052155000'),
('Parjatan Motel', 'Rangpur', 'mid', 16, 'WiFi, Free Parking', 4800.00, 'info@parjatanmotel.com', '+8801715098789'),
('Hotel Golden Tower', 'Rangpur', 'low', 22, 'WiFi, Restaurant', 2700.00, 'support@goldentower.com', '+8801714356784'),

-- Mymensingh Division
('Hotel Amir International', 'Mymensingh', 'high', 8, 'WiFi, Restaurant, Rooftop View', 8000.00, 'contact@hotelamir.com', '+88092161400'),
('Mymensingh Hotel & Resort', 'Mymensingh', 'mid', 13, 'WiFi, Gym, Pool', 5200.00, 'info@mymensinghresort.com', '+88092151160'),
('Hotel Rivera', 'Mymensingh', 'low', 19, 'WiFi, Free Breakfast', 2900.00, 'support@rivera.com', '+88092156321');

SELECT * FROM hotel;
CREATE TABLE IF NOT EXISTS transport (
    id INT AUTO_INCREMENT PRIMARY KEY,
    from_division VARCHAR(50) NOT NULL,
    to_division VARCHAR(50) NOT NULL,
    transport_type ENUM('Bus', 'Train', 'Flight') NOT NULL,
    departure_date DATE NOT NULL,
    return_date DATE,
    fare DECIMAL(10,2) NOT NULL
);
INSERT INTO transport (from_division, to_division, transport_type, departure_date, return_date, fare)
VALUES 
('Dhaka', 'Chattogram', 'Bus', '2025-03-10', '2025-03-15', 25.00),
('Dhaka', 'Sylhet', 'Train', '2025-03-12', '2025-03-16', 15.00),
('Dhaka', 'Cox’s Bazar', 'Flight', '2025-03-08', '2025-03-12', 120.00);
SELECT * FROM transport 
WHERE from_division = 'Dhaka' 
AND to_division = 'Chattogram' 
AND departure_date = '2025-03-10';

CREATE TABLE bucket_list (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    place VARCHAR(255) NOT NULL,
    visited BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (username) REFERENCES user(username)
);