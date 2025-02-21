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
('Dhaka', 'Shishu Park', 'An amusement park in Dhaka, offering various rides and attractions for children. It is a popular destination for families.', 'Shishu Park, Shahbagh', 'Best visited in winter', 'Biriyani, Bhuna Khichuri', 'Accessible by bus, train, and car'),

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

