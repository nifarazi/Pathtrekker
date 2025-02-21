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


CREATE TABLE IF NOT EXISTS destinations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    division VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    top_attractions TEXT,
    weather_info TEXT,
    local_cuisine TEXT,
    transport_info TEXT
);

INSERT INTO destinations (division, name, description, top_attractions, weather_info, local_cuisine, transport_info)
VALUES

-- Dhaka Division
('Dhaka', 'Lalbagh Fort', 'A Mughal fort in Dhaka, known for its historical significance and beautiful architecture. It is a popular tourist attraction.', 'Lalbagh Fort, Ahsan Manzil', 'Best visited in winter', 'Biriyani, Bhuna Khichuri', 'Accessible by bus, train, and car'),
('Dhaka', 'Ahsan Manzil', 'A historic palace in Dhaka, also known as the Pink Palace. It served as the official residential palace and seat of the Nawab of Dhaka.', 'Ahsan Manzil, Sadarghat', 'Best visited in winter', 'Biriyani, Bhuna Khichuri', 'Accessible by bus, train, and car'),
('Dhaka', 'National Parliament House', 'The house of the Parliament of Bangladesh, designed by architect Louis Kahn. It is one of the largest legislative complexes in the world.', 'Parliament House, Crescent Lake', 'Best visited in winter', 'Biriyani, Bhuna Khichuri', 'Accessible by bus, train, and car'),
('Dhaka', 'Hatirjheel', 'A lakefront in Dhaka, offering scenic views and recreational activities. It is a popular spot for locals and tourists alike.', 'Hatirjheel, Gulshan', 'Best visited in winter', 'Biriyani, Bhuna Khichuri', 'Accessible by bus, train, and car'),
('Dhaka', 'Bangladesh National Museum', 'The national museum of Bangladesh, showcasing the country\'s rich history, culture, and heritage through various exhibits.', 'National Museum, Shahbagh', 'Best visited in winter', 'Biriyani, Bhuna Khichuri', 'Accessible by bus, train, and car'),
('Dhaka', 'Dhakeshwari Temple', 'A Hindu temple in Dhaka, considered the most important Hindu place of worship in Bangladesh. It is a significant religious and cultural site.', 'Dhakeshwari Temple, Lalbagh Fort', 'Best visited in winter', 'Biriyani, Bhuna Khichuri', 'Accessible by bus, train, and car'),
('Dhaka', 'Ramna Park', 'A large park in Dhaka, offering green spaces, walking paths, and a serene environment. It is a popular spot for relaxation and recreation.', 'Ramna Park, Suhrawardy Udyan', 'Best visited in winter', 'Biriyani, Bhuna Khichuri', 'Accessible by bus, train, and car'),
('Dhaka', 'Suhrawardy Udyan', 'A national memorial in Dhaka, commemorating the country\'s struggle for independence. It is a significant historical and cultural site.', 'Suhrawardy Udyan, Ramna Park', 'Best visited in winter', 'Biriyani, Bhuna Khichuri', 'Accessible by bus, train, and car'),
('Dhaka', 'Baldha Garden', 'A botanical garden in Dhaka, known for its diverse collection of plants and serene environment. It is a popular spot for nature lovers.', 'Baldha Garden, Wari', 'Best visited in winter', 'Biriyani, Bhuna Khichuri', 'Accessible by bus, train, and car'),
('Dhaka', 'Sonargaon', 'Once the capital of Bengal, is rich in history and culture. Showcases traditional crafts.', 'Panam Nagar, Ancient Trading Center, Goaldi Mosque', 'Best visited in winter', 'Panta Bhat, Bhuna Khichuri', 'Accessible by bus, train, and car'),

-- Chattogram Division
('Chattogram', 'Patenga Beach', 'A popular beach in Chattogram, known for its scenic beauty and vibrant atmosphere. It is a favorite spot for locals and tourists.', 'Patenga Beach, Foy\'s Lake', 'Best visited in winter', 'Seafood, Mezban', 'Accessible by bus, train, and car'),
('Chattogram', 'Foy\'s Lake', 'An amusement park and lake in Chattogram, offering various recreational activities and beautiful views.', 'Foy\'s Lake, Patenga Beach', 'Best visited in winter', 'Seafood, Mezban', 'Accessible by bus, train, and car'),
('Chattogram', 'Karnaphuli River', 'A major river in Chattogram, known for its importance in trade and transportation. It offers scenic boat rides.', 'Karnaphuli River, Patenga Beach', 'Best visited in winter', 'Seafood, Mezban', 'Accessible by bus, train, and car'),
('Chattogram', 'Chandanpura Mosque', 'A historic mosque in Chattogram, known for its unique architecture and cultural significance.', 'Chandanpura Mosque, Patenga Beach', 'Best visited in winter', 'Seafood, Mezban', 'Accessible by bus, train, and car'),
('Chattogram', 'Ethnological Museum', 'A museum in Chattogram, showcasing the diverse cultures and traditions of Bangladesh.', 'Ethnological Museum, Patenga Beach', 'Best visited in winter', 'Seafood, Mezban', 'Accessible by bus, train, and car'),
('Chattogram', 'War Cemetery', 'A cemetery for World War II soldiers, offering a solemn and peaceful environment.', 'War Cemetery, Patenga Beach', 'Best visited in winter', 'Seafood, Mezban', 'Accessible by bus, train, and car'),
('Chattogram', 'Shrine of Bayazid Bostami', 'A shrine in Chattogram, dedicated to the revered Sufi saint Bayazid Bostami.', 'Shrine of Bayazid Bostami, Patenga Beach', 'Best visited in winter', 'Seafood, Mezban', 'Accessible by bus, train, and car'),
('Chattogram', 'Boga Lake', 'A lake in Chattogram, known for its crystal-clear water and picturesque surroundings.', 'Boga Lake, Patenga Beach', 'Best visited in winter', 'Seafood, Mezban', 'Accessible by bus, train, and car'),
('Chattogram', 'Sitakunda Eco Park', 'An eco park in Chattogram, offering lush greenery and a variety of flora and fauna.', 'Sitakunda Eco Park, Patenga Beach', 'Best visited in winter', 'Seafood, Mezban', 'Accessible by bus, train, and car'),
('Chattogram', 'Bhatiari Lake', 'A lake in Chattogram, known for its serene environment and beautiful views.', 'Bhatiari Lake, Patenga Beach', 'Best visited in winter', 'Seafood, Mezban', 'Accessible by bus, train, and car'),

-- Sylhet Division
('Sylhet', 'Ratargul Swamp Forest', 'A freshwater swamp forest, known for its unique ecosystem and boat rides through the forest.', 'Ratargul Swamp Forest, Jaflong', 'Best visited in monsoon', 'Pach Bhorta, Panta Ilish', 'Accessible by bus, train, and car'),
('Sylhet', 'Jaflong', 'A hill station in Sylhet, known for its scenic beauty and tea gardens.', 'Jaflong, Ratargul Swamp Forest', 'Best visited in monsoon', 'Pach Bhorta, Panta Ilish', 'Accessible by bus, train, and car'),
('Sylhet', 'Srimangal', 'A town known for its tea gardens and lush green landscapes.', 'Srimangal, Ratargul Swamp Forest', 'Best visited in monsoon', 'Pach Bhorta, Panta Ilish', 'Accessible by bus, train, and car'),
('Sylhet', 'Lawachara National Park', 'A national park in Sylhet, home to diverse wildlife and beautiful trails.', 'Lawachara National Park, Ratargul Swamp Forest', 'Best visited in monsoon', 'Pach Bhorta, Panta Ilish', 'Accessible by bus, train, and car'),
('Sylhet', 'Madhabkunda Waterfall', 'A waterfall in Sylhet, known for its stunning beauty and surrounding nature.', 'Madhabkunda Waterfall, Ratargul Swamp Forest', 'Best visited in monsoon', 'Pach Bhorta, Panta Ilish', 'Accessible by bus, train, and car'),
('Sylhet', 'Lalakhal', 'A river in Sylhet, known for its clear blue water and scenic boat rides.', 'Lalakhal, Ratargul Swamp Forest', 'Best visited in monsoon', 'Pach Bhorta, Panta Ilish', 'Accessible by bus, train, and car'),
('Sylhet', 'Hakaluki Haor', 'A marsh wetland in Sylhet, rich in biodiversity and a haven for bird watchers.', 'Hakaluki Haor, Ratargul Swamp Forest', 'Best visited in monsoon', 'Pach Bhorta, Panta Ilish', 'Accessible by bus, train, and car'),
('Sylhet', 'Tanguar Haor', 'A wetland in Sylhet, known for its natural beauty and diverse wildlife.', 'Tanguar Haor, Ratargul Swamp Forest', 'Best visited in monsoon', 'Pach Bhorta, Panta Ilish', 'Accessible by bus, train, and car'),
('Sylhet', 'Hum Hum Waterfall', 'A waterfall in Sylhet, offering a beautiful and adventurous trekking experience.', 'Hum Hum Waterfall, Ratargul Swamp Forest', 'Best visited in monsoon', 'Pach Bhorta, Panta Ilish', 'Accessible by bus, train, and car'),
('Sylhet', 'Tilagor Eco Park', 'An eco park in Sylhet, known for its lush greenery and serene environment.', 'Tilagor Eco Park, Ratargul Swamp Forest', 'Best visited in monsoon', 'Pach Bhorta, Panta Ilish', 'Accessible by bus, train, and car'),

-- Rajshahi Division
('Rajshahi', 'Puthia Temple Complex', 'A complex of Hindu temples, known for its beautiful architecture and historical significance.', 'Puthia Temple Complex, Varendra Museum', 'Best visited in winter', 'Rajshahi Mango, Sweets', 'Accessible by bus, train, and car'),
('Rajshahi', 'Varendra Research Museum', 'A museum in Rajshahi, showcasing the rich history and culture of the region.', 'Varendra Research Museum, Puthia Temple Complex', 'Best visited in winter', 'Rajshahi Mango, Sweets', 'Accessible by bus, train, and car'),
('Rajshahi', 'Bagha Mosque', 'A historic mosque in Rajshahi, known for its beautiful architecture and cultural significance.', 'Bagha Mosque, Puthia Temple Complex', 'Best visited in winter', 'Rajshahi Mango, Sweets', 'Accessible by bus, train, and car'),
('Rajshahi', 'Chhoto Sona Mosque', 'A historic mosque in Rajshahi, known for its intricate design and historical importance.', 'Chhoto Sona Mosque, Puthia Temple Complex', 'Best visited in winter', 'Rajshahi Mango, Sweets', 'Accessible by bus, train, and car'),
('Rajshahi', 'Kantajew Temple', 'A Hindu temple in Rajshahi, known for its stunning terracotta architecture.', 'Kantajew Temple, Puthia Temple Complex', 'Best visited in winter', 'Rajshahi Mango, Sweets', 'Accessible by bus, train, and car'),
('Rajshahi', 'Mahasthangarh', 'An ancient archaeological site, known for its historical significance and ruins.', 'Mahasthangarh, Puthia Temple Complex', 'Best visited in winter', 'Rajshahi Mango, Sweets', 'Accessible by bus, train, and car'),
('Rajshahi', 'Barendra Museum', 'A museum in Rajshahi, showcasing the region\'s archaeological and cultural heritage.', 'Barendra Museum, Puthia Temple Complex', 'Best visited in winter', 'Rajshahi Mango, Sweets', 'Accessible by bus, train, and car'),
('Rajshahi', 'Chapai Nawabganj', 'A district in Rajshahi, known for its mangoes and cultural heritage.', 'Chapai Nawabganj, Puthia Temple Complex', 'Best visited in winter', 'Rajshahi Mango, Sweets', 'Accessible by bus, train, and car'),
('Rajshahi', 'Paharpur', 'An archaeological site in Rajshahi, known for its ancient Buddhist Vihara.', 'Paharpur, Puthia Temple Complex', 'Best visited in winter', 'Rajshahi Mango, Sweets', 'Accessible by bus, train, and car'),
('Rajshahi', 'Naogaon', 'A district in Rajshahi, known for its historical sites and cultural significance.', 'Naogaon, Puthia Temple Complex', 'Best visited in winter', 'Rajshahi Mango, Sweets', 'Accessible by bus, train, and car'),

-- Khulna Division
('Khulna', 'Sundarbans', 'The largest mangrove forest in the world, home to the Royal Bengal Tiger and diverse wildlife.', 'Sundarbans, Bagerhat', 'Best visited in winter', 'Fish Curry, Panta Bhat', 'Accessible by bus, train, and boat'),
('Khulna', 'Bagerhat', 'A historic city in Khulna, known for its ancient mosques and rich history.', 'Bagerhat, Sundarbans', 'Best visited in winter', 'Fish Curry, Panta Bhat', 'Accessible by bus, train, and car'),
('Khulna', 'Shat Gombuj Mosque', 'A historic mosque in Khulna, famous for its sixty domes and architectural beauty.', 'Shat Gombuj Mosque, Sundarbans', 'Best visited in winter', 'Fish Curry, Panta Bhat', 'Accessible by bus, train, and car'),
('Khulna', 'Khan Jahan Ali\'s Tomb', 'A historic tomb in Khulna, dedicated to the revered saint Khan Jahan Ali.', 'Khan Jahan Ali\'s Tomb, Sundarbans', 'Best visited in winter', 'Fish Curry, Panta Bhat', 'Accessible by bus, train, and car'),
('Khulna', 'Rabindra Complex', 'A complex in Khulna, dedicated to the works and life of Rabindranath Tagore.', 'Rabindra Complex, Sundarbans', 'Best visited in winter', 'Fish Curry, Panta Bhat', 'Accessible by bus, train, and car'),
('Khulna', 'Mongla Port', 'A port in Khulna, known for its strategic importance and scenic views.', 'Mongla Port, Sundarbans', 'Best visited in winter', 'Fish Curry, Panta Bhat', 'Accessible by bus, train, and car'),
('Khulna', 'Karamjal Wildlife Center', 'A wildlife center in Khulna, offering a glimpse into the diverse flora and fauna of the region.', 'Karamjal Wildlife Center, Sundarbans', 'Best visited in winter', 'Fish Curry, Panta Bhat', 'Accessible by bus, train, and car'),
('Khulna', 'Dublar Char Island', 'An island in Khulna, known for its natural beauty and fishing activities.', 'Dublar Char Island, Sundarbans', 'Best visited in winter', 'Fish Curry, Panta Bhat', 'Accessible by bus, train, and car'),
('Khulna', 'Hiron Point', 'A point in Khulna, offering stunning views of the Sundarbans and its wildlife.', 'Hiron Point, Sundarbans', 'Best visited in winter', 'Fish Curry, Panta Bhat', 'Accessible by bus, train, and car'),
('Khulna', 'Karamjal Forest Station', 'A forest station in Khulna, serving as a gateway to the Sundarbans.', 'Karamjal Forest Station, Sundarbans', 'Best visited in winter', 'Fish Curry, Panta Bhat', 'Accessible by bus, train, and car'),

-- Barishal Division
('Barishal', 'Kuakata Beach', 'A beach with a panoramic view of both sunrise and sunset over the Bay of Bengal.', 'Kuakata Beach, Durga Sagar', 'Best visited in winter', 'Hilsa Fish, Coconut Water', 'Accessible by bus, train, and car'),
('Barishal', 'Durga Sagar', 'A large pond in Barishal, known for its serene environment and historical significance.', 'Durga Sagar, Kuakata Beach', 'Best visited in winter', 'Hilsa Fish, Coconut Water', 'Accessible by bus, train, and car'),
('Barishal', 'Lebur Char', 'An island in Barishal, offering beautiful views and a peaceful environment.', 'Lebur Char, Kuakata Beach', 'Best visited in winter', 'Hilsa Fish, Coconut Water', 'Accessible by bus, train, and car'),
('Barishal', 'Guthia Mosque', 'A mosque in Barishal, known for its stunning architecture and cultural significance.', 'Guthia Mosque, Kuakata Beach', 'Best visited in winter', 'Hilsa Fish, Coconut Water', 'Accessible by bus, train, and car'),
('Barishal', 'Floating Guava Market', 'A floating market in Barishal, offering a unique shopping experience on boats.', 'Floating Guava Market, Kuakata Beach', 'Best visited in winter', 'Hilsa Fish, Coconut Water', 'Accessible by bus, train, and car'),
('Barishal', 'Bibir Pukur', 'A pond in Barishal, known for its historical significance and serene environment.', 'Bibir Pukur, Kuakata Beach', 'Best visited in winter', 'Hilsa Fish, Coconut Water', 'Accessible by bus, train, and car'),
('Barishal', 'Fatrar Char', 'An island in Barishal, offering beautiful views and a peaceful environment.', 'Fatrar Char, Kuakata Beach', 'Best visited in winter', 'Hilsa Fish, Coconut Water', 'Accessible by bus, train, and car'),
('Barishal', 'Laldia Forest', 'A forest in Barishal, known for its lush greenery and diverse wildlife.', 'Laldia Forest, Kuakata Beach', 'Best visited in winter', 'Hilsa Fish, Coconut Water', 'Accessible by bus, train, and car'),
('Barishal', 'Sonar Char', 'An island in Barishal, offering beautiful views and a peaceful environment.', 'Sonar Char, Kuakata Beach', 'Best visited in winter', 'Hilsa Fish, Coconut Water', 'Accessible by bus, train, and car'),
('Barishal', 'Char Kukri Mukri', 'An island in Barishal, known for its natural beauty and serene environment.', 'Char Kukri Mukri, Kuakata Beach', 'Best visited in winter', 'Hilsa Fish, Coconut Water', 'Accessible by bus, train, and car'),

-- Rangpur Division
('Rangpur', 'Tajhat Palace', 'A historic palace in Rangpur, known for its stunning architecture and historical significance.', 'Tajhat Palace, Carmichael College', 'Best visited in winter', 'Pithas, Sweets', 'Accessible by bus, train, and car'),
('Rangpur', 'Carmichael College', 'A historic college in Rangpur, known for its beautiful campus and academic excellence.', 'Carmichael College, Tajhat Palace', 'Best visited in winter', 'Pithas, Sweets', 'Accessible by bus, train, and car'),
('Rangpur', 'Vinno Jogot', 'An amusement park in Rangpur, offering various rides and attractions for visitors.', 'Vinno Jogot, Tajhat Palace', 'Best visited in winter', 'Pithas, Sweets', 'Accessible by bus, train, and car'),
('Rangpur', 'Begum Rokeya Memorial Center', 'A memorial center in Rangpur, dedicated to the life and works of Begum Rokeya.', 'Begum Rokeya Memorial Center, Tajhat Palace', 'Best visited in winter', 'Pithas, Sweets', 'Accessible by bus, train, and car'),
('Rangpur', 'Rangpur Zoo', 'A zoo in Rangpur, home to various species of animals and birds.', 'Rangpur Zoo, Tajhat Palace', 'Best visited in winter', 'Pithas, Sweets', 'Accessible by bus, train, and car'),
('Rangpur', 'Town Hall', 'A historic building in Rangpur, known for its architectural beauty and cultural significance.', 'Town Hall, Tajhat Palace', 'Best visited in winter', 'Pithas, Sweets', 'Accessible by bus, train, and car'),
('Rangpur', 'Rangpur Museum', 'A museum in Rangpur, showcasing the rich history and culture of the region.', 'Rangpur Museum, Tajhat Palace', 'Best visited in winter', 'Pithas, Sweets', 'Accessible by bus, train, and car'),
('Rangpur', 'Mithapukur Zamindar Bari', 'A historic mansion in Rangpur, known for its architectural beauty and historical significance.', 'Mithapukur Zamindar Bari, Tajhat Palace', 'Best visited in winter', 'Pithas, Sweets', 'Accessible by bus, train, and car'),
('Rangpur', 'Teesta Barrage', 'A barrage in Rangpur, known for its importance in irrigation and scenic views.', 'Teesta Barrage, Tajhat Palace', 'Best visited in winter', 'Pithas, Sweets', 'Accessible by bus, train, and car'),
('Rangpur', 'Rangpur Central Park', 'A park in Rangpur, offering green spaces and recreational activities for visitors.', 'Rangpur Central Park, Tajhat Palace', 'Best visited in winter', 'Pithas, Sweets', 'Accessible by bus, train, and car'),

-- Mymensingh Division
('Mymensingh', 'Shoshi Lodge', 'A historic lodge in Mymensingh, known for its architectural beauty and historical significance.', 'Shoshi Lodge, Brahmaputra River', 'Best visited in winter', 'Monda, Pithas', 'Accessible by bus, train, and car'),
('Mymensingh', 'Brahmaputra River', 'A major river in Mymensingh, offering scenic views and boat rides.', 'Brahmaputra River, Shoshi Lodge', 'Best visited in winter', 'Monda, Pithas', 'Accessible by bus, train, and car'),
('Mymensingh', 'Alexander Castle', 'A historic castle in Mymensingh, known for its architectural beauty and historical significance.', 'Alexander Castle, Shoshi Lodge', 'Best visited in winter', 'Monda, Pithas', 'Accessible by bus, train, and car'),
('Mymensingh', 'Muktagacha Zamindar Bari', 'A historic mansion in Mymensingh, known for its architectural beauty and historical significance.', 'Muktagacha Zamindar Bari, Shoshi Lodge', 'Best visited in winter', 'Monda, Pithas', 'Accessible by bus, train, and car'),
('Mymensingh', 'Mymensingh Museum', 'A museum in Mymensingh, showcasing the rich history and culture of the region.', 'Mymensingh Museum, Shoshi Lodge', 'Best visited in winter', 'Monda, Pithas', 'Accessible by bus, train, and car'),
('Mymensingh', 'Botanical Garden', 'A botanical garden in Mymensingh, offering a variety of plants and a serene environment.', 'Botanical Garden, Shoshi Lodge', 'Best visited in winter', 'Monda, Pithas', 'Accessible by bus, train, and car'),
('Mymensingh', 'Shilpacharya Zainul Abedin Museum', 'A museum in Mymensingh, dedicated to the works of the famous artist Zainul Abedin.', 'Shilpacharya Zainul Abedin Museum, Shoshi Lodge', 'Best visited in winter', 'Monda, Pithas', 'Accessible by bus, train, and car'),
('Mymensingh', 'Bhawal National Park', 'A national park in Mymensingh, known for its lush greenery and diverse wildlife.', 'Bhawal National Park, Shoshi Lodge', 'Best visited in winter', 'Monda, Pithas', 'Accessible by bus, train, and car'),
('Mymensingh', 'Garo Hills', 'A hill range in Mymensingh, offering beautiful views and a peaceful environment.', 'Garo Hills, Shoshi Lodge', 'Best visited in winter', 'Monda, Pithas', 'Accessible by bus, train, and car'),
('Mymensingh', 'Bijoypur', 'A town in Mymensingh, known for its natural beauty and cultural significance.', 'Bijoypur, Shoshi Lodge', 'Best visited in winter', 'Monda, Pithas', 'Accessible by bus, train, and car');


SELECT * FROM destinations;