

use bank_service;
CREATE TABLE state(
    id INT(2) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    code INT NOT NULL,
    created_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    updated_at DATETIME,
    updated_by INT
) AUTO_INCREMENT = 01;


INSERT INTO state (name, code, created_at, created_by) VALUES
    ('Andhra Pradesh', 28, NOW(), 1),
    ('Arunachal Pradesh', 12, NOW(), 1),
    ('Assam', 18, NOW(), 1),
    ('Bihar', 10, NOW(), 1),
    ('Chhattisgarh', 22, NOW(), 1),
    ('Goa', 30, NOW(), 1),
    ('Gujarat', 24, NOW(), 1),
    ('Haryana', 6, NOW(), 1),
    ('Himachal Pradesh', 2, NOW(), 1),
    ('Jharkhand', 20, NOW(), 1),
    ('Maharashtra', 27, NOW(), 1);

    
    
    CREATE TABLE district (
    id INT(4) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    state_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    updated_at DATETIME,
    updated_by INT,
    FOREIGN KEY (state_id) REFERENCES state(id)
)AUTO_INCREMENT = 0001;

INSERT INTO district (name, state_id, created_at, created_by) VALUES
    -- Districts for Andhra Pradesh (state_id 0001)
    ('Anantapur', 1, NOW(), 1),
    ('Chittoor', 1, NOW(), 1),
    ('East Godavari', 1, NOW(), 1),
    
    -- Districts for Arunachal Pradesh (state_id 0002)
    ('Tawang', 2, NOW(), 1),
    ('West Kameng', 2, NOW(), 1),
    ('East Kameng', 2, NOW(), 1),
    
    -- Districts for Assam (state_id 0003)
    ('Barpeta', 3, NOW(), 1),
    ('Bongaigaon', 3, NOW(), 1),
    ('Cachar', 3, NOW(), 1),
    
    -- Districts for Bihar (state_id 0004)
    ('Araria', 4, NOW(), 1),
    ('Arwal', 4, NOW(), 1),
    ('Aurangabad', 4, NOW(), 1),
    
    -- Districts for Chhattisgarh (state_id 0005)
    ('Balod', 5, NOW(), 1),
    ('Baloda Bazar', 5, NOW(), 1),
    ('Balrampur', 5, NOW(), 1),
    
    -- Districts for Goa (state_id 0006)
    ('North Goa', 6, NOW(), 1),
    ('South Goa', 6, NOW(), 1),
    
    -- Districts for Gujarat (state_id 0007)
    ('Ahmedabad', 7, NOW(), 1),
    ('Amreli', 7, NOW(), 1),
    ('Anand', 7, NOW(), 1),
    
    -- Districts for Haryana (state_id 0008)
    ('Ambala', 8, NOW(), 1),
    ('Bhiwani', 8, NOW(), 1),
    ('Charkhi Dadri', 8, NOW(), 1),
    
    -- Districts for Himachal Pradesh (state_id 0009)
    ('Bilaspur', 9, NOW(), 1),
    ('Chamba', 9, NOW(), 1),
    ('Hamirpur', 9, NOW(), 1),
    
    -- Districts for Jharkhand (state_id 0010)
    ('Bokaro', 10, NOW(), 1),
    ('Chatra', 10, NOW(), 1),
    ('Deoghar', 10, NOW(), 1),
    
    -- Districts for Maharashtra (state_id 0011)
    ('Ahmednagar', 11, NOW(), 1),
    ('Akola', 11, NOW(), 1),
    ('Amravati', 11, NOW(), 1),
    ('Nagpur', 11, NOW(), 1),
    ('Mumbai', 11, NOW(), 1);


CREATE TABLE city (
    id INT(4) ZEROFILL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    district_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    updated_at DATETIME,
    updated_by INT,
    FOREIGN KEY (district_id) References district(id)
)AUTO_INCREMENT = 0001;


INSERT INTO city (name, district_id, created_at, created_by) VALUES
    -- Cities for Anantapur district (district_id 0001)
    ('Anantapur City', 1, NOW(), 1),
    ('Dharmavaram', 1, NOW(), 1),
    ('Hindupur', 1, NOW(), 1),
    
    -- Cities for Chittoor district (district_id 0002)
    ('Chittoor City', 2, NOW(), 1),
    ('Tirupati', 2, NOW(), 1),
    ('Madanapalle', 2, NOW(), 1),
    
    -- Cities for East Godavari district (district_id 0003)
    ('Kakinada', 3, NOW(), 1),
    ('Rajahmundry', 3, NOW(), 1),
    ('Amalapuram', 3, NOW(), 1),
    
    -- Cities for Tawang district (district_id 0004)
    ('Tawang City', 4, NOW(), 1),
    ('Dirang', 4, NOW(), 1),
    ('Bomdila', 4, NOW(), 1),
    
    -- Cities for West Kameng district (district_id 0005)
    ('Bomdila', 5, NOW(), 1),
    ('Dirang', 5, NOW(), 1),
    ('Tawang City', 5, NOW(), 1),
    
    -- Cities for East Kameng district (district_id 0006)
    ('Seppa', 6, NOW(), 1),
    ('Chayang Tajo', 6, NOW(), 1),
    ('Bameng', 6, NOW(), 1),
    
    -- Cities for Barpeta district (district_id 0007)
    ('Barpeta City', 7, NOW(), 1),
    ('Sarthebari', 7, NOW(), 1),
    ('Sorbhog', 7, NOW(), 1),
    
    -- Cities for Bongaigaon district (district_id 0008)
    ('Bongaigaon City', 8, NOW(), 1),
    ('Abhayapuri', 8, NOW(), 1),
    ('North Bongaigaon', 8, NOW(), 1),
    
    -- Cities for Cachar district (district_id 0009)
    ('Silchar', 9, NOW(), 1),
    ('Hailakandi', 9, NOW(), 1),
    ('Karimganj', 9, NOW(), 1),
    
    -- Cities for Araria district (district_id 0010)
    ('Araria City', 10, NOW(), 1),
    ('Forbesganj', 10, NOW(), 1),
    ('Jokihat', 10, NOW(), 1),
    
    -- Cities for Arwal district (district_id 0011)
    ('Arwal City', 11, NOW(), 1),
    ('Kaler', 11, NOW(), 1),
    ('Karpi', 11, NOW(), 1),
    
    -- Cities for Aurangabad district (district_id 0012)
    ('Aurangabad City', 12, NOW(), 1),
    ('Gaya', 12, NOW(), 1),
    ('Bihar Sharif', 12, NOW(), 1),
    
    -- Cities for Balod district (district_id 0013)
    ('Balod City', 13, NOW(), 1),
    ('Dondi', 13, NOW(), 1),
    ('Gunderdehi', 13, NOW(), 1),
    
    -- Cities for Baloda Bazar district (district_id 0014)
    ('Baloda Bazar City', 14, NOW(), 1),
    ('Palari', 14, NOW(), 1),
    ('Kasdol', 14, NOW(), 1),
    
    -- Cities for Balrampur district (district_id 0015)
    ('Balrampur City', 15, NOW(), 1),
    ('Ramanujganj', 15, NOW(), 1),
    ('Shankargarh', 15, NOW(), 1),
    
    -- Cities for North Goa district (district_id 0016)
    ('Panaji', 16, NOW(), 1),
    ('Mapusa', 16, NOW(), 1),
    ('Bicholim', 16, NOW(), 1),
    
    -- Cities for South Goa district (district_id 0017)
    ('Margao', 17, NOW(), 1),
    ('Vasco da Gama', 17, NOW(), 1),
    ('Ponda', 17, NOW(), 1),
    
    -- Cities for Ahmedabad district (district_id 0018)
    ('Ahmedabad City', 18, NOW(), 1),
    ('Gandhinagar', 18, NOW(), 1),
    ('Kalol', 18, NOW(), 1),
    
    -- Cities for Amreli district (district_id 0019)
    ('Amreli City', 19, NOW(), 1),
    ('Dhari', 19, NOW(), 1),
    ('Savarkundla', 19, NOW(), 1),
    
    -- Cities for Anand district (district_id 0020)
    ('Anand City', 20, NOW(), 1),
    ('Nadiad', 20, NOW(), 1),
    ('Petlad', 20, NOW(), 1),
    
    -- Cities for Ambala district (district_id 0021)
    ('Ambala City', 21, NOW(), 1),
    ('Kurukshetra', 21, NOW(), 1),
    ('Panchkula', 21, NOW(), 1),
    
    -- Cities for Bhiwani district (district_id 0022)
    ('Bhiwani City', 22, NOW(), 1),
    ('Charkhi Dadri', 22, NOW(), 1),
    ('Loharu', 22, NOW(), 1),
    
    -- Cities for Charkhi Dadri district (district_id 0023)
    ('Charkhi Dadri City', 23, NOW(), 1),
    ('Badhra', 23, NOW(), 1),
    ('Siwani', 23, NOW(), 1),
    
    -- Cities for Bilaspur district (district_id 0024)
    ('Bilaspur City', 24, NOW(), 1),
    ('Ghumarwin', 24, NOW(), 1),
    ('Naina Devi', 24, NOW(), 1),
    
    -- Cities for Chamba district (district_id 0025)
    ('Chamba City', 25, NOW(), 1),
    ('Dalhousie', 25, NOW(), 1),
    ('Bharmour', 25, NOW(), 1),
    
    -- Cities for Hamirpur district (district_id 0026)
    ('Hamirpur City', 26, NOW(), 1),
    ('Nadaun', 26, NOW(), 1),
    ('Barsar', 26, NOW(), 1),
    
    -- Cities for Bokaro district (district_id 0027)
    ('Bokaro Steel City', 27, NOW(), 1),
    ('Chandrapura', 27, NOW(), 1),
    ('Phusro', 27, NOW(), 1),
    
    -- Cities for Chatra district (district_id 0028)
    ('Chatra City', 28, NOW(), 1),
    ('Simaria', 28, NOW(), 1),
    ('Tandwa', 28, NOW(), 1),
    
    -- Cities for Deoghar district (district_id 0029)
    ('Deoghar City', 29, NOW(), 1),
    ('Jasidih', 29, NOW(), 1),
    ('Madhupur', 29, NOW(), 1),
    
    -- Cities for Ahmednagar district (district_id 0030)
    ('Ahmednagar City', 30, NOW(), 1),
    ('Rahata', 30, NOW(), 1),
    ('Sangamner', 30, NOW(), 1),
    
    -- Cities for Akola district (district_id 0031)
    ('Akola City', 31, NOW(), 1),
    ('Balapur', 31, NOW(), 1),
    ('Murtizapur', 31, NOW(), 1),
    
    -- Cities for Amravati district (district_id 0032)
    ('Amravati City', 32, NOW(), 1),
    ('Achalpur', 32, NOW(), 1),
    ('Anjangaon', 32, NOW(), 1),
    
    -- Cities for Nagpur district (district_id 0033)
    ('Nagpur City', 33, NOW(), 1),
    ('Kamptee', 33, NOW(), 1),
    ('Umred', 33, NOW(), 1),
    
    -- Cities for Mumbai district (district_id 0034)
    ('Mumbai City', 34, NOW(), 1),
    ('Thane', 34, NOW(), 1),
    ('Navi Mumbai', 34, NOW(), 1);
    

