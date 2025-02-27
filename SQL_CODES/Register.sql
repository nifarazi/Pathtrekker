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

CREATE TABLE transport_system (
    id INT AUTO_INCREMENT PRIMARY KEY,
    from_division VARCHAR(50),
    to_division VARCHAR(50),
    transport_type VARCHAR(20),
    departure_date DATE,
    return_date DATE,
    fare DECIMAL(10,2),
    service_name VARCHAR(100),
    email VARCHAR(100),
    phone_number VARCHAR(20),
    service_location VARCHAR(150)
);

INSERT INTO transport_system (from_division, to_division, transport_type, departure_date, return_date, fare, service_name, email, phone_number, service_location)
VALUES 
    -- Dhaka to Chattogram
    ('Dhaka', 'Chattogram', 'Bus', '2025-03-01', '2025-03-05', 1200.00, 'Green Line Paribahan', 'info@greenline.com', '+8801712345678', 'Dhaka Bus Terminal'),
    ('Dhaka', 'Chattogram', 'Train', '2025-03-02', '2025-03-06', 1000.00, 'Bangladesh Railway', 'info@railway.com', '+8801712345679', 'Dhaka Railway Station'),
    ('Dhaka', 'Chattogram', 'Plane', '2025-03-03', '2025-03-07', 4500.00, 'Biman Bangladesh', 'info@biman.com', '+8801712345680', 'Dhaka Airport'),
    ('Dhaka', 'Chattogram', 'Ferry', '2025-03-04', '2025-03-08', 1800.00, 'Sundarban Navigation', 'info@sundarban.com', '+8801712345681', 'Sadarghat Terminal'),

    -- Dhaka to Rajshahi
    ('Dhaka', 'Rajshahi', 'Bus', '2025-03-01', '2025-03-05', 900.00, 'Hanif Paribahan', 'info@hanif.com', '+8801712345682', 'Dhaka Bus Terminal'),
    ('Dhaka', 'Rajshahi', 'Train', '2025-03-02', '2025-03-06', 800.00, 'Bangladesh Railway', 'info@railway.com', '+8801712345683', 'Dhaka Railway Station'),
    ('Dhaka', 'Rajshahi', 'Plane', '2025-03-03', '2025-03-07', 5000.00, 'US-Bangla Airlines', 'info@usbangla.com', '+8801712345684', 'Dhaka Airport'),

    -- Dhaka to Khulna
    ('Dhaka', 'Khulna', 'Bus', '2025-03-01', '2025-03-05', 1000.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801712345685', 'Dhaka Bus Terminal'),
    ('Dhaka', 'Khulna', 'Train', '2025-03-02', '2025-03-06', 950.00, 'Bangladesh Railway', 'info@railway.com', '+8801712345686', 'Dhaka Railway Station'),
    ('Dhaka', 'Khulna', 'Ferry', '2025-03-03', '2025-03-07', 2000.00, 'Rocket Steamer', 'info@rocketsteamer.com', '+8801712345687', 'Sadarghat Terminal'),
    ('Dhaka', 'Khulna', 'Plane', '2025-03-04', '2025-03-08', 5500.00, 'NovoAir', 'info@novoair.com', '+8801712345688', 'Dhaka Airport'),

    -- Dhaka to Barishal
    ('Dhaka', 'Barishal', 'Bus', '2025-03-01', '2025-03-05', 850.00, 'Hanif Paribahan', 'info@hanif.com', '+8801712345689', 'Dhaka Bus Terminal'),
    ('Dhaka', 'Barishal', 'Ferry', '2025-03-02', '2025-03-06', 1000.00, 'Sundarban Navigation', 'info@sundarban.com', '+8801712345690', 'Sadarghat Terminal'),
    ('Dhaka', 'Barishal', 'Plane', '2025-03-03', '2025-03-07', 4800.00, 'Biman Bangladesh', 'info@biman.com', '+8801712345691', 'Dhaka Airport'),

    -- Dhaka to Sylhet
    ('Dhaka', 'Sylhet', 'Bus', '2025-03-01', '2025-03-05', 1300.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801712345692', 'Dhaka Bus Terminal'),
    ('Dhaka', 'Sylhet', 'Train', '2025-03-02', '2025-03-06', 1200.00, 'Bangladesh Railway', 'info@railway.com', '+8801712345693', 'Dhaka Railway Station'),
    ('Dhaka', 'Sylhet', 'Plane', '2025-03-03', '2025-03-07', 4600.00, 'US-Bangla Airlines', 'info@usbangla.com', '+8801712345694', 'Dhaka Airport'),

    -- Dhaka to Rangpur
    ('Dhaka', 'Rangpur', 'Bus', '2025-03-01', '2025-03-05', 1100.00, 'Hanif Paribahan', 'info@hanif.com', '+8801712345695', 'Dhaka Bus Terminal'),
    ('Dhaka', 'Rangpur', 'Train', '2025-03-02', '2025-03-06', 900.00, 'Bangladesh Railway', 'info@railway.com', '+8801712345696', 'Dhaka Railway Station'),

    -- Dhaka to Mymensingh
    ('Dhaka', 'Mymensingh', 'Bus', '2025-03-01', '2025-03-05', 600.00, 'Ena Transport', 'info@ena.com', '+8801712345697', 'Dhaka Bus Terminal'),
    ('Dhaka', 'Mymensingh', 'Train', '2025-03-02', '2025-03-06', 500.00, 'Bangladesh Railway', 'info@railway.com', '+8801712345698', 'Dhaka Railway Station');


INSERT INTO transport_system (from_division, to_division, transport_type, departure_date, return_date, fare, service_name, email, phone_number, service_location)
VALUES 
    -- Chattogram to Dhaka
    ('Chattogram', 'Dhaka', 'Bus', '2025-03-01', '2025-03-05', 1200.00, 'Green Line Paribahan', 'info@greenline.com', '+8801812345678', 'Chattogram Bus Terminal'),
    ('Chattogram', 'Dhaka', 'Train', '2025-03-02', '2025-03-06', 1000.00, 'Bangladesh Railway', 'info@railway.com', '+8801812345679', 'Chattogram Railway Station'),
    ('Chattogram', 'Dhaka', 'Plane', '2025-03-03', '2025-03-07', 4500.00, 'Biman Bangladesh', 'info@biman.com', '+8801812345680', 'Shah Amanat Intl. Airport'),
    ('Chattogram', 'Dhaka', 'Ferry', '2025-03-04', '2025-03-08', 1800.00, 'Sundarban Navigation', 'info@sundarban.com', '+8801812345681', 'Chattogram Terminal'),

    -- Chattogram to Rajshahi
    ('Chattogram', 'Rajshahi', 'Bus', '2025-03-01', '2025-03-05', 1400.00, 'Hanif Paribahan', 'info@hanif.com', '+8801812345682', 'Chattogram Bus Terminal'),
    ('Chattogram', 'Rajshahi', 'Train', '2025-03-02', '2025-03-06', 1300.00, 'Bangladesh Railway', 'info@railway.com', '+8801812345683', 'Chattogram Railway Station'),
    ('Chattogram', 'Rajshahi', 'Plane', '2025-03-03', '2025-03-07', 5200.00, 'NovoAir', 'info@novoair.com', '+8801812345684', 'Shah Amanat Intl. Airport'),

    -- Chattogram to Khulna
    ('Chattogram', 'Khulna', 'Bus', '2025-03-01', '2025-03-05', 1600.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801812345685', 'Chattogram Bus Terminal'),
    ('Chattogram', 'Khulna', 'Train', '2025-03-02', '2025-03-06', 1500.00, 'Bangladesh Railway', 'info@railway.com', '+8801812345686', 'Chattogram Railway Station'),
    ('Chattogram', 'Khulna', 'Ferry', '2025-03-03', '2025-03-07', 2200.00, 'Rocket Steamer', 'info@rocketsteamer.com', '+8801812345687', 'Chattogram Terminal'),
    ('Chattogram', 'Khulna', 'Plane', '2025-03-04', '2025-03-08', 5700.00, 'Biman Bangladesh', 'info@biman.com', '+8801812345688', 'Shah Amanat Intl. Airport'),

    -- Chattogram to Barishal
    ('Chattogram', 'Barishal', 'Bus', '2025-03-01', '2025-03-05', 1400.00, 'Ena Transport', 'info@ena.com', '+8801812345689', 'Chattogram Bus Terminal'),
    ('Chattogram', 'Barishal', 'Ferry', '2025-03-02', '2025-03-06', 1900.00, 'Sundarban Navigation', 'info@sundarban.com', '+8801812345690', 'Chattogram Terminal'),
    ('Chattogram', 'Barishal', 'Plane', '2025-03-03', '2025-03-07', 5000.00, 'NovoAir', 'info@novoair.com', '+8801812345691', 'Shah Amanat Intl. Airport'),

    -- Chattogram to Sylhet
    ('Chattogram', 'Sylhet', 'Bus', '2025-03-01', '2025-03-05', 1500.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801812345692', 'Chattogram Bus Terminal'),
    ('Chattogram', 'Sylhet', 'Train', '2025-03-02', '2025-03-06', 1400.00, 'Bangladesh Railway', 'info@railway.com', '+8801812345693', 'Chattogram Railway Station'),
    ('Chattogram', 'Sylhet', 'Plane', '2025-03-03', '2025-03-07', 5300.00, 'US-Bangla Airlines', 'info@usbangla.com', '+8801812345694', 'Shah Amanat Intl. Airport'),

    -- Chattogram to Rangpur
    ('Chattogram', 'Rangpur', 'Bus', '2025-03-01', '2025-03-05', 1600.00, 'Hanif Paribahan', 'info@hanif.com', '+8801812345695', 'Chattogram Bus Terminal'),
    ('Chattogram', 'Rangpur', 'Train', '2025-03-02', '2025-03-06', 1450.00, 'Bangladesh Railway', 'info@railway.com', '+8801812345696', 'Chattogram Railway Station'),

    -- Chattogram to Mymensingh
    ('Chattogram', 'Mymensingh', 'Bus', '2025-03-01', '2025-03-05', 1300.00, 'Ena Transport', 'info@ena.com', '+8801812345697', 'Chattogram Bus Terminal'),
    ('Chattogram', 'Mymensingh', 'Train', '2025-03-02', '2025-03-06', 1100.00, 'Bangladesh Railway', 'info@railway.com', '+8801812345698', 'Chattogram Railway Station');
INSERT INTO transport_system (from_division, to_division, transport_type, departure_date, return_date, fare, service_name, email, phone_number, service_location)
VALUES 
    -- Rajshahi to Dhaka
    ('Rajshahi', 'Dhaka', 'Bus', '2025-03-01', '2025-03-05', 900.00, 'Hanif Paribahan', 'info@hanif.com', '+8801912345678', 'Rajshahi Bus Terminal'),
    ('Rajshahi', 'Dhaka', 'Train', '2025-03-02', '2025-03-06', 800.00, 'Bangladesh Railway', 'info@railway.com', '+8801912345679', 'Rajshahi Railway Station'),
    ('Rajshahi', 'Dhaka', 'Plane', '2025-03-03', '2025-03-07', 5000.00, 'US-Bangla Airlines', 'info@usbangla.com', '+8801912345680', 'Rajshahi Airport'),

    -- Rajshahi to Chattogram
    ('Rajshahi', 'Chattogram', 'Bus', '2025-03-01', '2025-03-05', 1400.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801912345681', 'Rajshahi Bus Terminal'),
    ('Rajshahi', 'Chattogram', 'Train', '2025-03-02', '2025-03-06', 1300.00, 'Bangladesh Railway', 'info@railway.com', '+8801912345682', 'Rajshahi Railway Station'),
    ('Rajshahi', 'Chattogram', 'Plane', '2025-03-03', '2025-03-07', 5200.00, 'NovoAir', 'info@novoair.com', '+8801912345683', 'Rajshahi Airport'),

    -- Rajshahi to Khulna
    ('Rajshahi', 'Khulna', 'Bus', '2025-03-01', '2025-03-05', 1100.00, 'Hanif Paribahan', 'info@hanif.com', '+8801912345684', 'Rajshahi Bus Terminal'),
    ('Rajshahi', 'Khulna', 'Train', '2025-03-02', '2025-03-06', 1050.00, 'Bangladesh Railway', 'info@railway.com', '+8801912345685', 'Rajshahi Railway Station'),

    -- Rajshahi to Barishal
    ('Rajshahi', 'Barishal', 'Bus', '2025-03-01', '2025-03-05', 1600.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801912345686', 'Rajshahi Bus Terminal'),
    ('Rajshahi', 'Barishal', 'Plane', '2025-03-02', '2025-03-06', 5300.00, 'Biman Bangladesh', 'info@biman.com', '+8801912345687', 'Rajshahi Airport'),

    -- Rajshahi to Sylhet
    ('Rajshahi', 'Sylhet', 'Bus', '2025-03-01', '2025-03-05', 1500.00, 'Ena Transport', 'info@ena.com', '+8801912345688', 'Rajshahi Bus Terminal'),
    ('Rajshahi', 'Sylhet', 'Train', '2025-03-02', '2025-03-06', 1400.00, 'Bangladesh Railway', 'info@railway.com', '+8801912345689', 'Rajshahi Railway Station'),
    ('Rajshahi', 'Sylhet', 'Plane', '2025-03-03', '2025-03-07', 5500.00, 'NovoAir', 'info@novoair.com', '+8801912345690', 'Rajshahi Airport'),

    -- Rajshahi to Rangpur
    ('Rajshahi', 'Rangpur', 'Bus', '2025-03-01', '2025-03-05', 700.00, 'Hanif Paribahan', 'info@hanif.com', '+8801912345691', 'Rajshahi Bus Terminal'),
    ('Rajshahi', 'Rangpur', 'Train', '2025-03-02', '2025-03-06', 650.00, 'Bangladesh Railway', 'info@railway.com', '+8801912345692', 'Rajshahi Railway Station'),

    -- Rajshahi to Mymensingh
    ('Rajshahi', 'Mymensingh', 'Bus', '2025-03-01', '2025-03-05', 1200.00, 'Ena Transport', 'info@ena.com', '+8801912345693', 'Rajshahi Bus Terminal'),
    ('Rajshahi', 'Mymensingh', 'Train', '2025-03-02', '2025-03-06', 1000.00, 'Bangladesh Railway', 'info@railway.com', '+8801912345694', 'Rajshahi Railway Station');
INSERT INTO transport_system (from_division, to_division, transport_type, departure_date, return_date, fare, service_name, email, phone_number, service_location)
VALUES 
    -- Khulna to Dhaka
    ('Khulna', 'Dhaka', 'Bus', '2025-03-01', '2025-03-05', 1000.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801711111111', 'Khulna Bus Terminal'),
    ('Khulna', 'Dhaka', 'Train', '2025-03-02', '2025-03-06', 950.00, 'Bangladesh Railway', 'info@railway.com', '+8801711111112', 'Khulna Railway Station'),
    ('Khulna', 'Dhaka', 'Ferry', '2025-03-03', '2025-03-07', 2000.00, 'Rocket Steamer', 'info@rocketsteamer.com', '+8801711111113', 'Khulna River Terminal'),
    ('Khulna', 'Dhaka', 'Plane', '2025-03-04', '2025-03-08', 5500.00, 'NovoAir', 'info@novoair.com', '+8801711111114', 'Jessore Airport'),

    -- Khulna to Chattogram
    ('Khulna', 'Chattogram', 'Bus', '2025-03-01', '2025-03-05', 1600.00, 'Hanif Paribahan', 'info@hanif.com', '+8801711111115', 'Khulna Bus Terminal'),
    ('Khulna', 'Chattogram', 'Train', '2025-03-02', '2025-03-06', 1500.00, 'Bangladesh Railway', 'info@railway.com', '+8801711111116', 'Khulna Railway Station'),
    ('Khulna', 'Chattogram', 'Plane', '2025-03-03', '2025-03-07', 5700.00, 'Biman Bangladesh', 'info@biman.com', '+8801711111117', 'Jessore Airport'),

    -- Khulna to Rajshahi
    ('Khulna', 'Rajshahi', 'Bus', '2025-03-01', '2025-03-05', 1100.00, 'Ena Transport', 'info@ena.com', '+8801711111118', 'Khulna Bus Terminal'),
    ('Khulna', 'Rajshahi', 'Train', '2025-03-02', '2025-03-06', 1050.00, 'Bangladesh Railway', 'info@railway.com', '+8801711111119', 'Khulna Railway Station'),

    -- Khulna to Barishal
    ('Khulna', 'Barishal', 'Bus', '2025-03-01', '2025-03-05', 1000.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801711111120', 'Khulna Bus Terminal'),
    ('Khulna', 'Barishal', 'Ferry', '2025-03-02', '2025-03-06', 1500.00, 'Sundarban Navigation', 'info@sundarban.com', '+8801711111121', 'Khulna River Terminal'),

    -- Khulna to Sylhet
    ('Khulna', 'Sylhet', 'Bus', '2025-03-01', '2025-03-05', 1700.00, 'Hanif Paribahan', 'info@hanif.com', '+8801711111122', 'Khulna Bus Terminal'),
    ('Khulna', 'Sylhet', 'Train', '2025-03-02', '2025-03-06', 1600.00, 'Bangladesh Railway', 'info@railway.com', '+8801711111123', 'Khulna Railway Station'),
    ('Khulna', 'Sylhet', 'Plane', '2025-03-03', '2025-03-07', 6000.00, 'NovoAir', 'info@novoair.com', '+8801711111124', 'Jessore Airport'),

    -- Khulna to Rangpur
    ('Khulna', 'Rangpur', 'Bus', '2025-03-01', '2025-03-05', 1800.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801711111125', 'Khulna Bus Terminal'),
    ('Khulna', 'Rangpur', 'Train', '2025-03-02', '2025-03-06', 1700.00, 'Bangladesh Railway', 'info@railway.com', '+8801711111126', 'Khulna Railway Station'),

    -- Khulna to Mymensingh
    ('Khulna', 'Mymensingh', 'Bus', '2025-03-01', '2025-03-05', 1300.00, 'Ena Transport', 'info@ena.com', '+8801711111127', 'Khulna Bus Terminal'),
    ('Khulna', 'Mymensingh', 'Train', '2025-03-02', '2025-03-06', 1200.00, 'Bangladesh Railway', 'info@railway.com', '+8801711111128', 'Khulna Railway Station');
INSERT INTO transport_system (from_division, to_division, transport_type, departure_date, return_date, fare, service_name, email, phone_number, service_location)
VALUES 
    -- Barishal to Dhaka
    ('Barishal', 'Dhaka', 'Bus', '2025-03-01', '2025-03-05', 850.00, 'Hanif Paribahan', 'info@hanif.com', '+8801812222221', 'Barishal Bus Terminal'),
    ('Barishal', 'Dhaka', 'Ferry', '2025-03-02', '2025-03-06', 1000.00, 'Sundarban Navigation', 'info@sundarban.com', '+8801812222222', 'Barishal River Terminal'),
    ('Barishal', 'Dhaka', 'Plane', '2025-03-03', '2025-03-07', 4800.00, 'Biman Bangladesh', 'info@biman.com', '+8801812222223', 'Barishal Airport'),

    -- Barishal to Chattogram
    ('Barishal', 'Chattogram', 'Bus', '2025-03-01', '2025-03-05', 1400.00, 'Ena Transport', 'info@ena.com', '+8801812222224', 'Barishal Bus Terminal'),
    ('Barishal', 'Chattogram', 'Ferry', '2025-03-02', '2025-03-06', 1900.00, 'Sundarban Navigation', 'info@sundarban.com', '+8801812222225', 'Barishal River Terminal'),
    ('Barishal', 'Chattogram', 'Plane', '2025-03-03', '2025-03-07', 5000.00, 'NovoAir', 'info@novoair.com', '+8801812222226', 'Barishal Airport'),

    -- Barishal to Rajshahi
    ('Barishal', 'Rajshahi', 'Bus', '2025-03-01', '2025-03-05', 1600.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801812222227', 'Barishal Bus Terminal'),
    ('Barishal', 'Rajshahi', 'Plane', '2025-03-02', '2025-03-06', 5300.00, 'Biman Bangladesh', 'info@biman.com', '+8801812222228', 'Barishal Airport'),

    -- Barishal to Khulna
    ('Barishal', 'Khulna', 'Bus', '2025-03-01', '2025-03-05', 1000.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801812222229', 'Barishal Bus Terminal'),
    ('Barishal', 'Khulna', 'Ferry', '2025-03-02', '2025-03-06', 1500.00, 'Sundarban Navigation', 'info@sundarban.com', '+8801812222230', 'Barishal River Terminal'),

    -- Barishal to Sylhet
    ('Barishal', 'Sylhet', 'Bus', '2025-03-01', '2025-03-05', 1700.00, 'Hanif Paribahan', 'info@hanif.com', '+8801812222231', 'Barishal Bus Terminal'),
    ('Barishal', 'Sylhet', 'Plane', '2025-03-02', '2025-03-06', 5500.00, 'NovoAir', 'info@novoair.com', '+8801812222232', 'Barishal Airport'),

    -- Barishal to Rangpur
    ('Barishal', 'Rangpur', 'Bus', '2025-03-01', '2025-03-05', 1800.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801812222233', 'Barishal Bus Terminal'),

    -- Barishal to Mymensingh
    ('Barishal', 'Mymensingh', 'Bus', '2025-03-01', '2025-03-05', 1300.00, 'Ena Transport', 'info@ena.com', '+8801812222234', 'Barishal Bus Terminal');

INSERT INTO transport_system (from_division, to_division, transport_type, departure_date, return_date, fare, service_name, email, phone_number, service_location)
VALUES 
    -- Sylhet to Dhaka
    ('Sylhet', 'Dhaka', 'Bus', '2025-03-01', '2025-03-05', 1300.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801913333331', 'Sylhet Bus Terminal'),
    ('Sylhet', 'Dhaka', 'Train', '2025-03-02', '2025-03-06', 1200.00, 'Bangladesh Railway', 'info@railway.com', '+8801913333332', 'Sylhet Railway Station'),
    ('Sylhet', 'Dhaka', 'Plane', '2025-03-03', '2025-03-07', 4600.00, 'Biman Bangladesh', 'info@biman.com', '+8801913333333', 'Osmani International Airport'),

    -- Sylhet to Chattogram
    ('Sylhet', 'Chattogram', 'Bus', '2025-03-01', '2025-03-05', 1500.00, 'Ena Transport', 'info@ena.com', '+8801913333334', 'Sylhet Bus Terminal'),
    ('Sylhet', 'Chattogram', 'Train', '2025-03-02', '2025-03-06', 1400.00, 'Bangladesh Railway', 'info@railway.com', '+8801913333335', 'Sylhet Railway Station'),
    ('Sylhet', 'Chattogram', 'Plane', '2025-03-03', '2025-03-07', 5300.00, 'US-Bangla Airlines', 'info@usbangla.com', '+8801913333336', 'Osmani International Airport'),

    -- Sylhet to Rajshahi
    ('Sylhet', 'Rajshahi', 'Bus', '2025-03-01', '2025-03-05', 1500.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801913333337', 'Sylhet Bus Terminal'),
    ('Sylhet', 'Rajshahi', 'Train', '2025-03-02', '2025-03-06', 1400.00, 'Bangladesh Railway', 'info@railway.com', '+8801913333338', 'Sylhet Railway Station'),
    ('Sylhet', 'Rajshahi', 'Plane', '2025-03-03', '2025-03-07', 5500.00, 'NovoAir', 'info@novoair.com', '+8801913333339', 'Osmani International Airport'),

    -- Sylhet to Khulna
    ('Sylhet', 'Khulna', 'Bus', '2025-03-01', '2025-03-05', 1700.00, 'Hanif Paribahan', 'info@hanif.com', '+8801913333340', 'Sylhet Bus Terminal'),
    ('Sylhet', 'Khulna', 'Train', '2025-03-02', '2025-03-06', 1600.00, 'Bangladesh Railway', 'info@railway.com', '+8801913333341', 'Sylhet Railway Station'),
    ('Sylhet', 'Khulna', 'Plane', '2025-03-03', '2025-03-07', 6000.00, 'NovoAir', 'info@novoair.com', '+8801913333342', 'Osmani International Airport'),

    -- Sylhet to Barishal
    ('Sylhet', 'Barishal', 'Bus', '2025-03-01', '2025-03-05', 1700.00, 'Hanif Paribahan', 'info@hanif.com', '+8801913333343', 'Sylhet Bus Terminal'),
    ('Sylhet', 'Barishal', 'Plane', '2025-03-02', '2025-03-06', 5500.00, 'NovoAir', 'info@novoair.com', '+8801913333344', 'Osmani International Airport'),

    -- Sylhet to Rangpur
    ('Sylhet', 'Rangpur', 'Bus', '2025-03-01', '2025-03-05', 1800.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801913333345', 'Sylhet Bus Terminal'),
    ('Sylhet', 'Rangpur', 'Train', '2025-03-02', '2025-03-06', 1700.00, 'Bangladesh Railway', 'info@railway.com', '+8801913333346', 'Sylhet Railway Station'),

    -- Sylhet to Mymensingh
    ('Sylhet', 'Mymensingh', 'Bus', '2025-03-01', '2025-03-05', 1300.00, 'Ena Transport', 'info@ena.com', '+8801913333347', 'Sylhet Bus Terminal'),
    ('Sylhet', 'Mymensingh', 'Train', '2025-03-02', '2025-03-06', 1200.00, 'Bangladesh Railway', 'info@railway.com', '+8801913333348', 'Sylhet Railway Station');
INSERT INTO transport_system (from_division, to_division, transport_type, departure_date, return_date, fare, service_name, email, phone_number, service_location)
VALUES 
    -- Rangpur to Dhaka
    ('Rangpur', 'Dhaka', 'Bus', '2025-03-01', '2025-03-05', 1100.00, 'Hanif Paribahan', 'info@hanif.com', '+8801924444441', 'Rangpur Bus Terminal'),
    ('Rangpur', 'Dhaka', 'Train', '2025-03-02', '2025-03-06', 900.00, 'Bangladesh Railway', 'info@railway.com', '+8801924444442', 'Rangpur Railway Station'),

    -- Rangpur to Chattogram
    ('Rangpur', 'Chattogram', 'Bus', '2025-03-01', '2025-03-05', 1600.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801924444443', 'Rangpur Bus Terminal'),
    ('Rangpur', 'Chattogram', 'Train', '2025-03-02', '2025-03-06', 1450.00, 'Bangladesh Railway', 'info@railway.com', '+8801924444444', 'Rangpur Railway Station'),

    -- Rangpur to Rajshahi
    ('Rangpur', 'Rajshahi', 'Bus', '2025-03-01', '2025-03-05', 700.00, 'Ena Transport', 'info@ena.com', '+8801924444445', 'Rangpur Bus Terminal'),
    ('Rangpur', 'Rajshahi', 'Train', '2025-03-02', '2025-03-06', 650.00, 'Bangladesh Railway', 'info@railway.com', '+8801924444446', 'Rangpur Railway Station'),

    -- Rangpur to Khulna
    ('Rangpur', 'Khulna', 'Bus', '2025-03-01', '2025-03-05', 1800.00, 'Hanif Paribahan', 'info@hanif.com', '+8801924444447', 'Rangpur Bus Terminal'),
    ('Rangpur', 'Khulna', 'Train', '2025-03-02', '2025-03-06', 1700.00, 'Bangladesh Railway', 'info@railway.com', '+8801924444448', 'Rangpur Railway Station'),

    -- Rangpur to Barishal
    ('Rangpur', 'Barishal', 'Bus', '2025-03-01', '2025-03-05', 1800.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801924444449', 'Rangpur Bus Terminal'),

    -- Rangpur to Sylhet
    ('Rangpur', 'Sylhet', 'Bus', '2025-03-01', '2025-03-05', 1800.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801924444450', 'Rangpur Bus Terminal'),
    ('Rangpur', 'Sylhet', 'Train', '2025-03-02', '2025-03-06', 1700.00, 'Bangladesh Railway', 'info@railway.com', '+8801924444451', 'Rangpur Railway Station'),

    -- Rangpur to Mymensingh
    ('Rangpur', 'Mymensingh', 'Bus', '2025-03-01', '2025-03-05', 1400.00, 'Ena Transport', 'info@ena.com', '+8801924444452', 'Rangpur Bus Terminal'),
    ('Rangpur', 'Mymensingh', 'Train', '2025-03-02', '2025-03-06', 1300.00, 'Bangladesh Railway', 'info@railway.com', '+8801924444453', 'Rangpur Railway Station');
INSERT INTO transport_system (from_division, to_division, transport_type, departure_date, return_date, fare, service_name, email, phone_number, service_location)
VALUES 
    -- Mymensingh to Dhaka
    ('Mymensingh', 'Dhaka', 'Bus', '2025-03-01', '2025-03-05', 600.00, 'Ena Transport', 'info@ena.com', '+8801935555551', 'Mymensingh Bus Terminal'),
    ('Mymensingh', 'Dhaka', 'Train', '2025-03-02', '2025-03-06', 500.00, 'Bangladesh Railway', 'info@railway.com', '+8801935555552', 'Mymensingh Railway Station'),

    -- Mymensingh to Chattogram
    ('Mymensingh', 'Chattogram', 'Bus', '2025-03-01', '2025-03-05', 1300.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801935555553', 'Mymensingh Bus Terminal'),
    ('Mymensingh', 'Chattogram', 'Train', '2025-03-02', '2025-03-06', 1200.00, 'Bangladesh Railway', 'info@railway.com', '+8801935555554', 'Mymensingh Railway Station'),

    -- Mymensingh to Rajshahi
    ('Mymensingh', 'Rajshahi', 'Bus', '2025-03-01', '2025-03-05', 1200.00, 'Hanif Paribahan', 'info@hanif.com', '+8801935555555', 'Mymensingh Bus Terminal'),
    ('Mymensingh', 'Rajshahi', 'Train', '2025-03-02', '2025-03-06', 1000.00, 'Bangladesh Railway', 'info@railway.com', '+8801935555556', 'Mymensingh Railway Station'),

    -- Mymensingh to Khulna
    ('Mymensingh', 'Khulna', 'Bus', '2025-03-01', '2025-03-05', 1300.00, 'Ena Transport', 'info@ena.com', '+8801935555557', 'Mymensingh Bus Terminal'),
    ('Mymensingh', 'Khulna', 'Train', '2025-03-02', '2025-03-06', 1200.00, 'Bangladesh Railway', 'info@railway.com', '+8801935555558', 'Mymensingh Railway Station'),

    -- Mymensingh to Barishal
    ('Mymensingh', 'Barishal', 'Bus', '2025-03-01', '2025-03-05', 1300.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801935555559', 'Mymensingh Bus Terminal'),

    -- Mymensingh to Sylhet
    ('Mymensingh', 'Sylhet', 'Bus', '2025-03-01', '2025-03-05', 1300.00, 'Ena Transport', 'info@ena.com', '+8801935555560', 'Mymensingh Bus Terminal'),
    ('Mymensingh', 'Sylhet', 'Train', '2025-03-02', '2025-03-06', 1200.00, 'Bangladesh Railway', 'info@railway.com', '+8801935555561', 'Mymensingh Railway Station'),

    -- Mymensingh to Rangpur
    ('Mymensingh', 'Rangpur', 'Bus', '2025-03-01', '2025-03-05', 1400.00, 'Shyamoli Paribahan', 'info@shyamoli.com', '+8801935555562', 'Mymensingh Bus Terminal'),
    ('Mymensingh', 'Rangpur', 'Train', '2025-03-02', '2025-03-06', 1300.00, 'Bangladesh Railway', 'info@railway.com', '+8801935555563', 'Mymensingh Railway Station');

SELECT * FROM transport_system;
CREATE TABLE bucket_list (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    place VARCHAR(255) NOT NULL,
    visited BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (username) REFERENCES user(username)
);
SELECT * FROM bucket_list;

CREATE TABLE IF NOT EXISTS final_destinations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    division VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    top_attractions TEXT,
    weather_info TEXT,
    local_cuisine TEXT,
    transport_info TEXT,
    opening_time TEXT,
    closing_time TEXT
);

-- Dhaka Division
INSERT INTO final_destinations (division, name, opening_time, closing_time, description, top_attractions, weather_info, local_cuisine, transport_info) VALUES
('Dhaka', 'Ahsan Manzil', '09:00:00', '17:00:00', 'A beautiful historical palace located in Old Dhaka. Known for its Mughal architecture and historical significance.',
 'Ahsan Manzil Museum, Pink Palace', 'Tropical monsoon climate, hot summers, and mild winters.', 'Biryani, Bhuna Khichuri, Panta Bhat', 'Accessible by Rickshaws, taxis, and local buses.'),
('Dhaka', 'Lalbagh Fort', '09:00:00', '17:00:00', 'A Mughal-era fort in the heart of Dhaka. It has a rich history and stunning architecture.',
 'Lalbagh Fort Museum, Archaeological Remains', 'Hot and humid summers, moderate winters.', 'Kacchi Biryani, Fuchka, Bhorta', 'Easily accessible via taxis, buses, or rickshaws.'),
('Dhaka', 'National Museum', '10:00:00', '17:00:00', 'The largest museum in Bangladesh, featuring exhibitions on the country’s history, culture, and art.',
 'Historical Exhibitions, Ancient Artifacts', 'Warm and humid with rainy seasons.', 'Hilsa Fish, Shorshe Ilish', 'Located near the National Press Club, accessible by taxi or public transport.'),
('Dhaka', 'Sonargaon', '09:00:00', '17:00:00', 'Sonargaon, the ancient capital of Bengal, features historical buildings and remnants of its rich cultural heritage.',
 'Historical Sites, Folk Art Museum', 'Tropical monsoon climate, hot summers, and mild winters.', 'Biryani, Bhuna Khichuri, Panta Bhat', 'Accessible by taxi, rickshaw, and local buses.'),
('Dhaka', 'Panam City', '09:00:00', '17:00:00', 'An ancient city near Sonargaon, famous for its old architecture, colonial buildings, and history.',
 'Colonial Architecture, Historical Significance', 'Tropical climate, with moderate humidity and occasional rainfall.', 'Kacchi Biryani, Fuchka', 'Accessible by taxi, rickshaw, and buses.'),
('Dhaka', 'Shahid Minar', '06:00:00', '20:00:00', 'A memorial dedicated to the martyrs of the 1952 Language Movement. It stands as a symbol of national pride.',
 'Language Movement Memorial, Historic Significance', 'Mild winter and hot summers.', 'Panta Bhat, Bhuna Khichuri', 'Located in central Dhaka, easily accessible by rickshaws or taxis.'),
('Dhaka', 'Baitul Mukarram Mosque', '09:00:00', '17:00:00', 'The national mosque of Bangladesh, known for its modernist architecture and religious importance.',
 'Islamic Architecture, National Mosque', 'Tropical climate with monsoon rains.', 'Kebabs, Biryani, Pulao', 'Well connected by buses, taxis, and rickshaws.'),
 ('Dhaka', 'Puran Dhaka', '09:00:00', '17:00:00', 'Old Dhaka, known for its colonial architecture, busy markets, and delicious street food.',
 'Old Markets, Colonial Buildings', 'Warm summers with occasional rainfall.', 'Fuchka, Kacchi Biryani', 'Easily accessible by rickshaws and public buses.'),
('Dhaka', 'Jatiyo Sangsad Bhaban', '09:00:00', '17:00:00', 'The National Parliament House of Bangladesh, designed by architect Louis Kahn. A masterpiece of modern architecture.',
 'Architectural Tours, National Assembly', 'Hot summers with tropical rains.', 'Chingri Malai Curry, Ilish Bhorta', 'Public transport, taxis, and rickshaws.'),
('Dhaka', 'Ramna Park', '06:00:00', '18:00:00', 'A large urban park located in central Dhaka. Popular for relaxation, jogging, and bird-watching.',
 'Nature Walks, Jogging Tracks', 'Humid and warm with occasional rains.', 'Shorshe Ilish, Chingri Bhorta', 'Easily accessible by local buses and rickshaws.'),
('Dhaka', 'Sundarbans Centre', '09:00:00', '17:00:00', 'A research and eco-tourism center focusing on the Sundarbans mangrove forest and its wildlife.',
 'Wildlife Tours, Eco-tourism', 'Tropical wet climate with high rainfall.', 'Panta Bhat, Sundarbans Fish Curry', 'Boat transport, local buses, and taxis.'),
('Dhaka', 'Mukti Juddha Museum', '09:00:00', '17:00:00', 'A museum dedicated to the 1971 Bangladesh Liberation War, showcasing artifacts and historical records.',
 'Historical Artifacts, War Exhibitions', 'Hot summers, mild winters.', 'Khichuri, Mutton Curry', 'Accessible by bus and rickshaw.'),
('Dhaka', 'Bangladesh Liberation War Museum', '09:00:00', '17:00:00', 'The museum holds exhibitions and collections related to the 1971 Liberation War.',
 'Historical Exhibits, War Documents', 'Hot summers, wet monsoon season.', 'Shorshe Ilish, Bhuna Khichuri', 'Easily accessible via rickshaw and taxis.'),
('Dhaka', 'Zia Memorial Museum', '09:00:00', '17:00:00', 'The museum dedicated to the memory of former President Ziaur Rahman.',
 'Zia Memorial, Historical Exhibits', 'Humid tropical climate.', 'Biryani, Khichuri', 'Easily accessible by taxi or rickshaw.'),

-- Chattogram Division
('Chattogram', 'Patenga Beach', '06:00:00', '18:00:00', 'A popular beach in Chattogram, known for its calm waters and scenic beauty. Great for relaxation and family outings.',
 'Beach Activities, Sunset Views', 'Tropical monsoon climate, with sunny and humid weather.', 'Mezban, Bhuna Khichuri', 'Accessible by taxis, local buses, or rickshaws.'),
('Chattogram', 'Kaptai Lake', '06:00:00', '18:00:00', 'A man-made lake surrounded by hills, offering boat rides and scenic views of nature.',
 'Boating, Scenic Views', 'Moderate temperatures with monsoon rains.', 'Panta Bhat, Chingri Malai Curry', 'Accessible via boat, rickshaws, and buses.'),
('Chattogram', 'Foy’s Lake', '09:00:00', '17:00:00', 'An artificial lake offering boating, an amusement park, and views of the surrounding hills.',
 'Amusement Park, Scenic Boating', 'Humid tropical climate with monsoons.', 'Shorshe Ilish, Bhorta', 'Accessible by taxis or local buses.'),
('Chattogram', 'Hill Tracts', '09:00:00', '17:00:00', 'A region of lush green hills, home to various indigenous tribes. Perfect for trekking and cultural exploration.',
 'Trekking, Tribal Culture', 'Cool climate, moderate rainfall.', 'Khichuri, Kacchi Biryani', 'Access by taxis, buses, and local transport.'),
('Chattogram', 'Cox’s Bazar', '00:00:00', '23:59:59', 'The world’s longest natural sea beach, stretching over 120 kilometers. Known for its beauty and relaxing atmosphere.',
 'Beaches, Water Sports', 'Tropical climate with humid conditions.', 'Ilish Bhorta, Panta Bhat', 'Accessible by taxis, local buses, and boats.'),
('Chattogram', 'Saint Martin’s Island', '06:00:00', '18:00:00', 'A small island in the Bay of Bengal, famous for its crystal-clear waters and coral reefs.',
 'Coral Reefs, Scuba Diving', 'Warm, sunny with occasional rains.', 'Chingri Malai Curry, Panta Bhat', 'Boat transport from Chattogram city.'),
('Chattogram', 'Karnaphuli River', '06:00:00', '18:00:00', 'A major river in Chattogram, offering boat trips and stunning views of the city and surrounding hills.',
 'River Cruises, Scenic Views', 'Humid tropical with frequent rains.', 'Mezban, Panta Bhat', 'Easily accessible via boat and taxis.'),
('Chattogram', 'Patenga Beach', '06:00:00', '18:00:00', 'A scenic beach located near the mouth of the Karnaphuli River, known for its calm waves and sunset views.',
 'Beach Views, Sunset Watching', 'Tropical climate with heavy rainfall in monsoon.', 'Bhuna Khichuri, Kacchi Biryani', 'Accessible by taxi and local transport.'),
('Chattogram', 'Bandarban', '06:00:00', '18:00:00', 'A hill district famous for its tea gardens, tribal cultures, and stunning waterfalls like Nilgiri and Nafakhum.',
 'Waterfalls, Tea Gardens', 'Cool climate, moderate rainfall.', 'Biryani, Khichuri', 'Accessible by bus, taxis, or private vehicles.'),
('Chattogram', 'Patiya', '06:00:00', '18:00:00', 'A charming region known for its rural beauty and peaceful landscapes, perfect for a quiet getaway.',
 'Cultural Sites, Rural Beauty', 'Moderate climate with mild winters.', 'Panta Bhat, Kacchi Biryani', 'Accessible by local buses and rickshaws.'),
('Chattogram', 'Lohagara', '06:00:00', '18:00:00', 'A peaceful upazila known for its natural beauty and vibrant rural culture.',
 'Tribal Culture, Village Life', 'Warm with occasional showers.', 'Bhuna Khichuri, Meze', 'Accessible by local buses and taxis.'),
('Chattogram', 'Chandranath Hill', '06:00:00', '18:00:00', 'A hilltop temple offering panoramic views of the surrounding landscape, ideal for trekking and sightseeing.',
 'Trekking, Temple', 'Cool weather with occasional rains.', 'Shorshe Ilish, Chingri Malai', 'Access by hiking or taxis.'),
('Chattogram', 'Kaptai National Park', '09:00:00', '17:00:00', 'A national park surrounded by dense forests and wildlife, ideal for nature lovers and trekkers.',
 'Wildlife Viewing, Trekking', 'Moderate temperature, rains in summer.', 'Kacchi Biryani, Shorshe Ilish', 'Accessible by boat, taxis, and local transport.'),
('Chattogram', 'Pahartali', '09:00:00', '17:00:00', 'A scenic region near the hills, known for its peaceful surroundings and natural beauty.',
 'Hills, Scenic Beauty', 'Tropical humid climate.', 'Biryani, Kacchi Biryani', 'Local buses and taxis accessible.'),

-- Rajshahi Division
('Rajshahi', 'Puthia Temple Complex', '09:00:00', '17:00:00', 'A historical site in Rajshahi with several temples, including the stunning Shiva temple. Known for its intricate architecture.',
 'Shiva Temple, Ancient Ruins', 'Moderate climate with dry winter and humid summer.', 'Biryani, Panta Bhat', 'Accessible by bus or local transport.'),
('Rajshahi', 'Varendra Research Museum', '09:00:00', '17:00:00', 'A museum showcasing the history and culture of ancient Bengal, with various artifacts and historical exhibits.',
 'Ancient Artifacts, Historical Exhibits', 'Mild winters and hot summers.', 'Khichuri, Bhorta', 'Local buses, rickshaws, and taxis.'),
('Rajshahi', 'Rajshahi University', '09:00:00', '17:00:00', 'A prestigious educational institution, surrounded by greenery and offering a peaceful environment for visitors.',
 'Green Campus, Research Exhibits', 'Mild and pleasant climate.', 'Panta Bhat, Shorshe Ilish', 'Easily accessible by rickshaws and taxis.'),
('Rajshahi', 'Bogra', '09:00:00', '17:00:00', 'A district known for its historical significance, including the ancient Mahasthangarh archaeological site.',
 'Mahasthangarh, Ancient Ruins', 'Warm, with rainy monsoon seasons.', 'Biryani, Bhuna Khichuri', 'Accessible by local buses and taxis.'),
('Rajshahi', 'Mahasthangarh', '09:00:00', '17:00:00', 'An ancient archaeological site dating back to the 3rd century BC. Known for its ruins and historical importance.',
 'Ancient City, Archaeological Ruins', 'Dry winters, humid summers.', 'Khichuri, Panta Bhat', 'Local transport or taxis available.'),
('Rajshahi', 'Chandraketugarh', '09:00:00', '17:00:00', 'An ancient historical site located in the Rajshahi district. It features artifacts from ancient Bengal’s rich culture.',
 'Archaeological Site, Historic Artifacts', 'Moderate climate, humid during summer.', 'Shorshe Ilish, Kacchi Biryani', 'Local buses, rickshaws, taxis.'),
('Rajshahi', 'Shah Makhdum Mazar', '09:00:00', '17:00:00', 'The shrine of Shah Makhdum, a revered saint. Known for its peaceful atmosphere and historical significance.',
 'Religious Site, Spiritual Peace', 'Cool winters, hot summers.', 'Panta Bhat, Shorshe Ilish', 'Accessible by taxis and buses.'),
('Rajshahi', 'Godagari', '09:00:00', '17:00:00', 'A rural area known for its agricultural beauty, with vast fields and a calm environment.',
 'Rice Fields, Rural Culture', 'Warm, with occasional rain.', 'Khichuri, Bhuna Khichuri', 'Local buses and rickshaws available.'),
('Rajshahi', 'Naogaon', '09:00:00', '17:00:00', 'A district known for its historical temples, rich culture, and rural landscapes.',
 'Historical Temples, Rural Beauty', 'Tropical climate with humid summers.', 'Biryani, Panta Bhat', 'Accessible via local transport.'),
('Rajshahi', 'Bagha Mosque', '09:00:00', '17:00:00', 'A historic mosque with impressive Mughal architecture, located in the Bagha region of Rajshahi.',
 'Mughal Architecture, Historical Sites', 'Moderate, dry climate.', 'Shorshe Ilish, Panta Bhat', 'Accessible by rickshaw and local buses.'),
('Rajshahi', 'Bogra Fort', '09:00:00', '17:00:00', 'A historical fort dating back to the Mughal era, located in the Bogra region.',
 'Fortifications, Historical Exhibits', 'Hot summers with moderate winters.', 'Biryani, Shorshe Ilish', 'Taxi and local transport accessible.'),
('Rajshahi', 'Chalan Beel', '09:00:00', '17:00:00', 'A large beel (lake) in Rajshahi, offering natural beauty and opportunities for bird watching.',
 'Bird Watching, Scenic Views', 'Mild and cool, with monsoon rains.', 'Khichuri, Bhuna Khichuri', 'Local buses and rickshaws available.'),
('Rajshahi', 'Kusumgram', '09:00:00', '17:00:00', 'A picturesque village located near the Padma River, known for its agricultural beauty and peaceful atmosphere.',
 'Agriculture, River Views', 'Cool winters, humid summers.', 'Kacchi Biryani, Shorshe Ilish', 'Easily accessible by rickshaw and local transport.'),
('Rajshahi', 'Sariska', '09:00:00', '17:00:00', 'A small town with a rich history, famous for its temples and rural charm.',
 'Historic Temples, Local Culture', 'Hot summers and pleasant winters.', 'Khichuri, Kacchi Biryani', 'Local buses and taxis available.'),
('Rajshahi', 'Shibganj', '09:00:00', '17:00:00', 'A town in Rajshahi known for its historical significance and local agriculture.',
 'Agriculture, Rural Beauty', 'Warm climate, with occasional rainfall.', 'Biryani, Bhorta', 'Taxi and rickshaw accessible.'),
('Rajshahi', 'Bonpara', '09:00:00', '17:00:00', 'A scenic area offering a glimpse of rural life, with fertile land and traditional agricultural practices.',
 'Traditional Farming, Scenic Views', 'Hot summer, mild winter.', 'Khichuri, Panta Bhat', 'Local buses and taxis available.'),

 -- Khulna Division
('Khulna', 'Sundarbans', '06:00:00', '18:00:00', 'The Sundarbans is the world’s largest mangrove forest and a UNESCO World Heritage site, famous for its unique ecosystem.',
 'Royal Bengal Tiger, Wildlife Safari', 'Tropical monsoon climate with heavy rainfall during the monsoon.', 'Sundarbans Fish Curry, Panta Bhat', 'Access by boat, local buses, and taxis.'),
('Khulna', 'Shat Gombuj Mosque', '09:00:00', '17:00:00', 'A historic mosque in Bagerhat, known for its unique architecture and historical significance.',
 'UNESCO World Heritage Site, Ancient Mosque', 'Hot summers with occasional rains, mild winters.', 'Ilish Bhorta, Khichuri', 'Accessible by rickshaw or local transport.'),
('Khulna', 'Bagerhat Museum', '10:00:00', '17:00:00', 'A museum showcasing the history of Bagerhat, featuring exhibits related to the area’s historical landmarks.',
 'Bagerhat Heritage, Museum Exhibits', 'Tropical climate, high humidity.', 'Panta Bhat, Shorshe Ilish', 'Accessible via local buses and taxis.'),
('Khulna', 'Karamjal Eco-Tourism Center', '09:00:00', '17:00:00', 'A nature reserve within the Sundarbans, focused on eco-tourism, with boat rides and wildlife watching.',
 'Eco-Tourism, Sundarbans Wildlife', 'Hot summers, mild winters, and a humid monsoon season.', 'Panta Bhat, Sundarbans Fish Curry', 'Accessible by boat and rickshaws.'),
('Khulna', 'Rupsha Bridge', '00:00:00', '23:59:59', 'A bridge spanning the Rupsha River, providing stunning views of the surrounding areas.',
 'Scenic Views, Local Markets', 'Humid tropical climate with summer rains.', 'Chingri Malai Curry, Bhuna Khichuri', 'Accessible by taxi and local buses.'),
('Khulna', 'Mongla Port', '00:00:00', '23:59:59', 'A major seaport in Bangladesh, offering views of ships and maritime activities.',
 'Port Views, River Cruises', 'Tropical monsoon climate, hot summers, mild winters.', 'Sundarbans Fish Curry, Bhuna Khichuri', 'Accessible by boat, local buses, and taxis.'),
('Khulna', 'Riverside Park', '06:00:00', '18:00:00', 'A scenic park located along the river with walking trails and picnic areas.',
 'Nature Walks, Scenic Views', 'Warm climate, with high humidity during the summer months.', 'Panta Bhat, Shorshe Ilish', 'Accessible by taxi and local transport.'),
('Khulna', 'Bagerhat Shahi Mosque', '09:00:00', '17:00:00', 'A historic mosque from the 15th century, known for its impressive architecture.',
 'Ancient Mosque, Historical Significance', 'Tropical climate, high humidity.', 'Ilish Bhorta, Shorshe Ilish', 'Accessible by local transport and rickshaws.'),
('Khulna', 'Sonargaon Museum', '09:00:00', '17:00:00', 'A museum showcasing artifacts and sculptures from the ancient capital of Bengal.',
 'Ancient Artifacts, Museum Exhibits', 'Humid tropical climate, summer rains.', 'Panta Bhat, Kacchi Biryani', 'Accessible by local buses and taxis.'),
('Khulna', 'Khulna City Park', '06:00:00', '20:00:00', 'A spacious park in the city, great for picnics and leisure walks.',
 'Walking Trails, Greenery', 'Warm tropical climate, humid summers.', 'Shorshe Ilish, Bhuna Khichuri', 'Accessible by taxis and local transport.'),
('Khulna', 'Bagerhat Historical Mosque', '09:00:00', '17:00:00', 'An iconic mosque in Bagerhat with a rich historical background.',
 'Ancient Mosque, Historical Tours', 'Hot summers with occasional rains, mild winters.', 'Ilish Bhorta, Panta Bhat', 'Accessible by local transport and rickshaws.'),
('Khulna', 'Sundarbans Reserve Forest', '06:00:00', '18:00:00', 'A UNESCO site and largest mangrove forest in the world, home to diverse wildlife.',
 'Mangrove Forest, Bengal Tiger Viewing', 'Tropical monsoon climate, rainy season.', 'Sundarbans Fish Curry, Bhuna Khichuri', 'Accessible by boat, rickshaws, and local buses.'),
('Khulna', 'Shibganj River', '06:00:00', '18:00:00', 'A picturesque river offering boating opportunities and scenic views.',
 'River Cruises, Fishing', 'Tropical weather with humid summers.', 'Panta Bhat, Ilish Bhorta', 'Accessible by boat and local transport.'),

 -- Barishal Division
('Barishal', 'Fatrar Char', '06:00:00', '18:00:00', 'A charming island with clear waters, a great place for relaxation and beach activities.',
 'Beach Relaxation, Water Sports', 'Tropical climate with mild winters.', 'Chingri Bhorta, Panta Bhat', 'Accessible by boat and local transport.'),
('Barishal', 'Madhavpasha Lake', '06:00:00', '18:00:00', 'A peaceful lake known for its serene atmosphere and boat rides.',
 'Lake Tours, Scenic Views', 'Moderate temperature with humid summers.', 'Ilish Bhorta, Panta Bhat', 'Accessible by boat and local buses.'),
('Barishal', 'Bauphal Tea Garden', '07:00:00', '18:00:00', 'A beautiful tea garden known for its picturesque landscapes and peaceful ambiance.',
 'Tea Garden Tour, Photography', 'Tropical climate with rainy monsoon.', 'Chingri Malai, Bhorta', 'Accessible by local transport.'),
('Barishal', 'Allepalli Mosque', '09:00:00', '17:00:00', 'An ancient mosque with beautiful architecture, known for its historic significance.',
 'Historical Architecture, Religious Tourism', 'Tropical climate with seasonal rainfall.', 'Panta Bhat, Ilish Bhorta', 'Accessible by taxi and local transport.'),
('Barishal', 'Gournadi Ferry', '06:00:00', '18:00:00', 'A scenic ferry ride offering views of the rivers and surrounding countryside.',
 'Ferry Ride, River Views', 'Tropical monsoon climate, humid summers.', 'Ilish Bhorta, Bhuna Khichuri', 'Accessible by boat and local transport.'),
('Barishal', 'Barguna River', '06:00:00', '18:00:00', 'A serene river offering boat rides and a peaceful environment.',
 'River Cruises, Boat Rides', 'Humid and warm weather, with rainy monsoons.', 'Panta Bhat, Shorshe Ilish', 'Accessible by boat and local buses.'),
('Barishal', 'Kantaji Temple', '10:00:00', '17:00:00', 'A revered Hindu temple with intricate terracotta artwork.',
 'Religious Tourism, Terracotta Art', 'Moderate temperature with humid summers.', 'Kacchi Biryani, Bhorta', 'Accessible by local transport and rickshaws.'),
('Barishal', 'Lalachara Wildlife Sanctuary', '06:00:00', '18:00:00', 'A sanctuary offering hiking trails and an opportunity to observe local wildlife.',
 'Wildlife Viewing, Nature Trails', 'Tropical climate with high humidity.', 'Chingri Malai, Panta Bhat', 'Accessible by local buses and taxis.'),
('Barishal', 'Baitul Aman Mosque', '09:00:00', '17:00:00', 'An elegant mosque known for its unique and grand architecture.',
 'Architectural Tour, Religious Significance', 'Moderate tropical climate.', 'Shorshe Ilish, Panta Bhat', 'Accessible by local buses and rickshaws.'),
('Barishal', 'Vikramshila Buddhist Monastery', '08:00:00', '17:00:00', 'A Buddhist monastery of historical significance, showcasing the area’s ancient heritage.',
 'Historical Buddhism, Ancient Architecture', 'Tropical climate with high humidity.', 'Panta Bhat, Ilish Bhorta', 'Accessible by rickshaw and local buses.'),
('Barishal', 'Bogra Hill', '06:00:00', '18:00:00', 'A scenic hill offering great views and a peaceful environment for hiking.',
 'Hiking, Scenic Views', 'Moderate climate with warm summers.', 'Shorshe Ilish, Bhuna Khichuri', 'Accessible by local transport and taxis.'),
('Barishal', 'Kotalipara Mosque', '09:00:00', '17:00:00', 'A historical mosque known for its impressive architecture and cultural heritage.',
 'Architectural Tour, Religious Tourism', 'Humid, tropical climate with summer rains.', 'Ilish Bhorta, Panta Bhat', 'Accessible by local transport and taxis.'),

 -- Sylhet Division
('Sylhet', 'Ratargul Swamp Forest', '06:00:00', '18:00:00', 'A freshwater swamp forest in Sylhet, known for its unique ecosystem and boat rides through the dense forest.',
 'Swamp Boat Ride, Rare Flora and Fauna', 'Cool weather with a tropical monsoon climate.', 'Shorshe Ilish, Bhuna Khichuri', 'Boat transport and local buses.'),
('Sylhet', 'Jaflong', '06:00:00', '18:00:00', 'A hill station known for its tea gardens, tribal culture, and scenic beauty.',
 'Tea Gardens, Trekking', 'Cool climate, with rainy monsoon season.', 'Panta Bhat, Ilish Bhorta', 'Accessible by local buses and taxis.'),
('Sylhet', 'Madhabkunda Waterfall', '09:00:00', '17:00:00', 'One of the largest waterfalls in Bangladesh, located in the scenic Sylhet region.',
 'Waterfalls, Scenic Trekking', 'Mild winters and warm, humid summers.', 'Kacchi Biryani, Bhorta', 'Accessible by taxi or local buses.'),
('Sylhet', 'Lawachara National Park', '06:00:00', '18:00:00', 'A vast forest reserve, ideal for nature lovers, featuring diverse wildlife and walking trails.',
 'Wildlife Viewing, Nature Walks', 'Moderate weather, cool winters.', 'Shorshe Ilish, Panta Bhat', 'Accessible by local buses and taxis.'),
('Sylhet', 'Shahjalal University of Science and Technology (SUST)', '09:00:00', '17:00:00', 'A renowned university known for its lush green campus and peaceful atmosphere.',
 'Campus Tours, Educational Tours', 'Moderate climate with cool winters.', 'Ilish Bhorta, Bhuna Khichuri', 'Accessible by taxi and local buses.'),
('Sylhet', 'Shahjalal University Mosque', '09:00:00', '17:00:00', 'A beautifully designed mosque within the university campus, a serene place for reflection.',
 'Religious Tourism, Architecture', 'Moderate weather with mild winters.', 'Panta Bhat, Bhorta', 'Accessible by local buses and taxis.'),
('Sylhet', 'Jadukata River', '06:00:00', '18:00:00', 'A river in Sylhet known for its crystal-clear water and scenic surroundings.',
 'River Cruises, Photography', 'Tropical climate with heavy rains during the monsoon.', 'Ilish Bhorta, Panta Bhat', 'Accessible by boat and local transport.'),
('Sylhet', 'Srimangal Tea Gardens', '06:00:00', '18:00:00', 'Known as the "Tea Capital of Bangladesh," this region features lush tea plantations.',
 'Tea Garden Tours, Photography', 'Cool weather, with high rainfall in monsoon season.', 'Shorshe Ilish, Bhuna Khichuri', 'Accessible by bus or taxi.'),
('Sylhet', 'Tanguar Haor', '06:00:00', '18:00:00', 'A wetland area known for its migratory birds and natural beauty.',
 'Bird Watching, Boat Tours', 'Mild winters and warm summers with seasonal monsoon rains.', 'Panta Bhat, Bhorta', 'Accessible by boat and local transport.'),
('Sylhet', 'Moulvibazar', '06:00:00', '18:00:00', 'A beautiful town surrounded by tea gardens and scenic views, perfect for a getaway.',
 'Tea Gardens, Nature Walks', 'Moderate climate with cool winters.', 'Ilish Bhorta, Kacchi Biryani', 'Accessible by local transport and rickshaws.'),
('Sylhet', 'Shah Paran Mazar Sharif', '09:00:00', '17:00:00', 'A significant religious site, the tomb of Hazrat Shah Paran, attracting pilgrims.',
 'Religious Tourism, Historical Significance', 'Mild winters with cool breezes.', 'Panta Bhat, Bhorta', 'Accessible by taxi and local buses.'),
('Sylhet', 'Bichanakandi', '06:00:00', '18:00:00', 'A scenic spot near Jaflong, with crystal-clear streams and beautiful views.',
 'Nature Walks, Photography', 'Cool weather with occasional rainfall.', 'Shorshe Ilish, Bhuna Khichuri', 'Accessible by local transport and taxis.'),
('Sylhet', 'Dawki', '06:00:00', '18:00:00', 'A village known for its beautiful river views and crystal-clear waters.',
 'River Cruises, Photography', 'Tropical climate, with monsoon season rains.', 'Ilish Bhorta, Panta Bhat', 'Accessible by local buses and taxis.'),
('Sylhet', 'Rustompur Lake', '06:00:00', '18:00:00', 'A picturesque lake surrounded by lush green hills, ideal for relaxation and boating.',
 'Lake Tours, Scenic Views', 'Tropical monsoon climate with moderate temperatures.', 'Shorshe Ilish, Kacchi Biryani', 'Accessible by boat and local transport.'),
('Sylhet', 'Khasia Village', '06:00:00', '18:00:00', 'A picturesque village inhabited by the Khasia tribe, known for their culture and lifestyle.',
 'Tribal Culture, Photography', 'Mild winters with cool temperatures.', 'Ilish Bhorta, Bhuna Khichuri', 'Accessible by local transport and trekking.'),


 -- Rangpur Division
('Rangpur', 'Tajhat Palace', '10:00:00', '17:00:00', 'A grand palace built during the British colonial era, offering a glimpse of royal life in the past.',
 'Palace Tours, Historical Exhibits', 'Moderate climate with warm summers and mild winters.', 'Shorshe Ilish, Bhuna Khichuri', 'Accessible by taxi and local transport.'),
('Rangpur', 'Ramsagar National Park', '09:00:00', '17:00:00', 'A national park with a variety of flora and fauna, perfect for nature lovers and wildlife enthusiasts.',
 'Wildlife Viewing, Nature Trails', 'Mild winter and warm, humid summers.', 'Chingri Malai, Shorshe Ilish', 'Accessible by bus, taxis, and rickshaws.'),
('Rangpur', 'Kantajew Temple', '10:00:00', '17:00:00', 'An ancient Hindu temple, known for its intricate terracotta carvings and architectural beauty.',
 'Terracotta Temples, Historical Significance', 'Moderate climate with dry winters and humid summers.', 'Panta Bhat, Kacchi Biryani', 'Accessible by rickshaw and local buses.'),
('Rangpur', 'Pirgachha Shahi Mosque', '09:00:00', '17:00:00', 'A significant historical mosque with impressive architecture located in Rangpur.',
 'Historical Sites, Religious Significance', 'Moderate climate with dry winters and humid summers.', 'Shorshe Ilish, Bhuna Khichuri', 'Accessible by local transport.'),
('Rangpur', 'Kurigram', '06:00:00', '18:00:00', 'A scenic region near the Brahmaputra River, known for its rural beauty and cultural heritage.',
 'River Views, Rural Tourism', 'Moderate climate with seasonal rainfall.', 'Shorshe Ilish, Panta Bhat', 'Accessible by bus and local transport.'),
('Rangpur', 'Gojira Dighi', '06:00:00', '18:00:00', 'A picturesque lake located in Rangpur, popular for fishing and local sightseeing.',
 'Lake Tours, Fishing', 'Mild winters, warm humid summers.', 'Chingri Malai, Bhuna Khichuri', 'Accessible by local buses and taxis.'),
('Rangpur', 'Sadar Ghat', '09:00:00', '17:00:00', 'A busy area along the river with local markets and a scenic view of the water.',
 'Riverside Market, Photography', 'Warm summers with high humidity.', 'Panta Bhat, Shorshe Ilish', 'Accessible by boat, taxi, and rickshaws.'),
('Rangpur', 'Baliadangi', '06:00:00', '18:00:00', 'A scenic location with a combination of tea gardens and hilly areas.',
 'Tea Garden Tours, Photography', 'Cool weather with occasional rains.', 'Shorshe Ilish, Bhuna Khichuri', 'Accessible by local buses and taxis.'),
('Rangpur', 'Teesta Barrage', '09:00:00', '17:00:00', 'A large dam that controls water flow in the Teesta River, offering breathtaking views.',
 'River View, Photography', 'Warm climate with rainy monsoon season.', 'Panta Bhat, Kacchi Biryani', 'Accessible by taxi and local transport.'),
('Rangpur', 'Natore Rajbari', '10:00:00', '17:00:00', 'An ancient palace with stunning architecture, once home to the Rajbari royal family.',
 'Royal Palace Tours, Historical Exhibits', 'Moderate weather with cool winters.', 'Ilish Bhorta, Bhuna Khichuri', 'Accessible by local transport.'),
('Rangpur', 'Brahmaputra River', '06:00:00', '18:00:00', 'A major river of South Asia offering picturesque views and boat tours.',
 'River Cruises, Scenic Views', 'Humid tropical climate with seasonal monsoon rains.', 'Shorshe Ilish, Kacchi Biryani', 'Accessible by boat and local transport.'),
('Rangpur', 'Narsingdi', '09:00:00', '17:00:00', 'A city known for its agricultural activities and local crafts.',
 'Crafts Market, Rural Tourism', 'Moderate climate with hot summers.', 'Ilish Bhorta, Panta Bhat', 'Accessible by taxi and local buses.'),
('Rangpur', 'Mithapukur', '06:00:00', '18:00:00', 'A small town in Rangpur known for its historical sites and local culture.',
 'Historical Sites, Local Culture', 'Warm summers, with occasional rains.', 'Panta Bhat, Bhuna Khichuri', 'Accessible by local buses and rickshaws.'),
('Rangpur', 'Kochu Khet', '06:00:00', '18:00:00', 'A rural village known for its lush green fields and peaceful environment.',
 'Rural Nature Walks, Photography', 'Cool winters and hot humid summers.', 'Shorshe Ilish, Kacchi Biryani', 'Accessible by local transport.'),
('Rangpur', 'Mithapukur Lake', '06:00:00', '18:00:00', 'A serene lake in Mithapukur, perfect for nature walks and boating.',
 'Lake Tours, Photography', 'Moderate climate with seasonal rains.', 'Ilish Bhorta, Bhuna Khichuri', 'Accessible by local transport.'),

 -- Mymensingh Division
('Mymensingh', 'Shashi Lodge', '10:00:00', '17:00:00', 'A historical mansion located in the city, showcasing colonial-era architecture.',
 'Colonial Architecture, Historic Lodge', 'Mild winters, warm humid summers.', 'Shorshe Ilish, Panta Bhat', 'Accessible by local transport and taxis.'),
('Mymensingh', 'Birishiri', '09:00:00', '17:00:00', 'A picturesque village known for its rural beauty, vibrant tribal culture, and the famous Birishiri Pottery.',
 'Tribal Culture, Pottery Tours', 'Cool winters and warm, humid summers.', 'Panta Bhat, Ilish Bhorta', 'Accessible by local transport and rickshaws.'),
('Mymensingh', 'Muktagacha Zamindar Bari', '09:00:00', '17:00:00', 'A historical mansion belonging to the Zamindar family, showcasing the area’s colonial past.',
 'Historical Mansion, Zamindar Heritage', 'Warm summers with humid conditions.', 'Kacchi Biryani, Shorshe Ilish', 'Accessible by taxi and local buses.'),
('Mymensingh', 'Bishweshwar Temple', '06:00:00', '18:00:00', 'An ancient temple dedicated to Lord Shiva, known for its religious significance and architectural beauty.',
 'Religious Tourism, Temple Tours', 'Moderate climate with cool winters.', 'Ilish Bhorta, Bhuna Khichuri', 'Accessible by local transport.'),
('Mymensingh', 'Mymensingh Museum', '09:00:00', '17:00:00', 'A museum displaying the history and culture of the Mymensingh region.',
 'Museum Exhibits, Cultural Heritage', 'Warm summers, cool winters.', 'Panta Bhat, Shorshe Ilish', 'Accessible by local buses and taxis.'),
('Mymensingh', 'Kalikapur Village', '06:00:00', '18:00:00', 'A scenic village known for its rice paddies and peaceful environment.',
 'Rural Tourism, Photography', 'Cool winters with warm humid summers.', 'Ilish Bhorta, Bhuna Khichuri', 'Accessible by local transport.'),
('Mymensingh', 'Jamalpur', '06:00:00', '18:00:00', 'A city known for its vibrant local markets and agricultural activities.',
 'Local Markets, Agricultural Tours', 'Warm summers, occasional rains.', 'Shorshe Ilish, Panta Bhat', 'Accessible by taxi and local buses.'),
('Mymensingh', 'Kishoreganj', '06:00:00', '18:00:00', 'A historical town with significant landmarks related to Bangladesh’s liberation.',
 'Liberation History, Historical Tours', 'Warm and humid summers, mild winters.', 'Ilish Bhorta, Bhuna Khichuri', 'Accessible by local transport.'),
('Mymensingh', 'Madhupur', '09:00:00', '17:00:00', 'A town known for its ancient temples and the nearby Madhupur Forest.',
 'Forest Walks, Historical Sites', 'Moderate climate with dry winters and humid summers.', 'Shorshe Ilish, Kacchi Biryani', 'Accessible by local transport.'),
('Mymensingh', 'Tekerhat', '06:00:00', '18:00:00', 'A small village with stunning landscapes and lush greenery.',
 'Nature Walks, Scenic Views', 'Moderate temperatures with seasonal rains.', 'Panta Bhat, Bhuna Khichuri', 'Accessible by local transport.'),
('Mymensingh', 'Salimullah Bhaban', '09:00:00', '17:00:00', 'A colonial-era building located in the city, known for its architecture.',
 'Architecture Tours, History', 'Warm summers and cool winters.', 'Shorshe Ilish, Bhorta', 'Accessible by taxi and local buses.'),
('Mymensingh', 'Madhupur Garh', '06:00:00', '18:00:00', 'A historical fort located in the Madhupur area, offering a glimpse into the region’s past.',
 'Historical Forts, Photography', 'Cool winters and warm humid summers.', 'Ilish Bhorta, Panta Bhat', 'Accessible by local transport.'),
('Mymensingh', 'Trishal', '06:00:00', '18:00:00', 'A town known for its historic temples, museums, and local attractions.',
 'Temple Visits, Cultural Heritage', 'Moderate temperatures with cool winters.', 'Shorshe Ilish, Kacchi Biryani', 'Accessible by local buses and taxis.'),
('Mymensingh', 'Dhanu River', '06:00:00', '18:00:00', 'A peaceful river with opportunities for boat tours and scenic walks.',
 'River Tours, Photography', 'Warm and humid climate, occasional rains.', 'Panta Bhat, Bhuna Khichuri', 'Accessible by local transport.');

 SELECT * FROM final_destinations;

-- Modified `photos` table (Store images by `username` instead of `user_id`)
CREATE TABLE IF NOT EXISTS photos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    image BLOB NOT NULL,
    caption TEXT,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE
);
 SELECT * FROM photos;
-- View uploaded photos
ALTER TABLE photos MODIFY image LONGBLOB;


-- Modified `likes` table (Now references `username` instead of `user_id`)
CREATE TABLE IF NOT EXISTS likes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    photo_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE,
    FOREIGN KEY (photo_id) REFERENCES photos(id) ON DELETE CASCADE
);

-- Modified `comments` table (Now references `username` instead of `user_id`)
CREATE TABLE IF NOT EXISTS comments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    photo_id INT NOT NULL,
    comment TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE,
    FOREIGN KEY (photo_id) REFERENCES photos(id) ON DELETE CASCADE
);

