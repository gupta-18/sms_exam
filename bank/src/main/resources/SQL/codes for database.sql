-- create  dataBase --
create dataBase bank_service;


-- use database --
use bank_service;


-- create user table --
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    middle_name VARCHAR(45),
    last_name VARCHAR(45) NOT NULL,
    mobile_number VARCHAR(10) NOT NULL,
    email VARCHAR(50) NOT NULL,
    gender CHAR(1) NOT NULL,
    date_of_birth DATE NOT NULL,
    cin VARCHAR(20) NOT NULL,
    adhaar_card VARCHAR(12) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    updated_at DATETIME,
    updated_by INT
);



-- create address table --
CREATE TABLE address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    address TEXT,
    pincode VARCHAR(7) NOT NULL,
    city_id INT NOT NULL,
    district_id INT NOT NULL,
    state_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (city_id) REFERENCES city(id),
    FOREIGN KEY (district_id) REFERENCES district(id),
    FOREIGN KEY (state_id) REFERENCES state(id)
);



-- create user_credential table --
CREATE TABLE user_credential (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    user_name VARCHAR(10) NOT NULL,
    password VARCHAR(200) NOT NULL,
    password_salt VARCHAR(100) NOT NULL,
    user_role ENUM('Admin', 'Employee', 'Customer') NOT NULL,
    login_date_time DATETIME ,
    created_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    updated_at DATETIME,
    updated_by INT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);
ALTER TABLE `bank_service`.`user_credential` 
DROP COLUMN `user_role`;



-- create account table --
CREATE TABLE account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    account_type VARCHAR(10) NOT NULL,
    balance DECIMAL(15,2) NOT NULL,
    account_number VARCHAR(14) NOT NULL,
    rate_of_interest DECIMAL(5,2) NOT NULL,
    branch_id INT NOT NULL,
    opening_date DATE NOT NULL,
    closing_date DATE,
    created_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    updated_at DATETIME,
    updated_by INT,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (branch_id) REFERENCES branch(id)
);


-- create state table --
CREATE TABLE state (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    code INT NOT NULL,
    created_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    updated_at DATETIME,
    updated_by INT
);


-- create district table --
CREATE TABLE district (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    code INT NOT NULL,
    state_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    updated_at DATETIME,
    updated_by INT,
    FOREIGN KEY (state_id) REFERENCES state(id)
);

-- create city table --
CREATE TABLE city (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    code INT NOT NULL,
    district_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    updated_at DATETIME,
    updated_by INT,
    FOREIGN KEY (district_id) REFERENCES district(id)
);

-- create branch table --    #admin can edit this table if new branch is Created
CREATE TABLE branch (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    code INT NOT NULL,
    ifsc_code VARCHAR(10) NOT NULL,
    city_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    updated_at DATETIME,
    updated_by INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_city_id FOREIGN KEY (city_id) REFERENCES city(id)
);



-- create transaction table --
CREATE TABLE transaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender_id INT NOT NULL,
    sender_acc_no VARCHAR(14),
    receiver_id INT,
    receiver_account_number VARCHAR(14),
    transfer_amount DOUBLE,
    sender_balance DOUBLE,
    receiver_balance DOUBLE,
    transaction_date_time DATETIME,
    created_at DATETIME,
    created_by INT,
    updated_at DATETIME,
    updated_by INT
)


-- create employee table --
CREATE TABLE bank_service.employee (
  id INT NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(45) NOT NULL,
  mobile_number VARCHAR(10) NOT NULL,
  email VARCHAR(45) NULL,
  gender CHAR(1) NOT NULL,
  date_of_birth DATE NOT NULL,
  adhaar_number VARCHAR(12) NOT NULL,
  address TEXT NULL,
  state VARCHAR(20) NOT NULL,
  city VARCHAR(20) NOT NULL,
  created_by INT NULL,
  created_at DATETIME NULL,
  updated_by INT NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX mobile_number_UNIQUE (mobile_number ASC) VISIBLE,
  UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE);

-- create employeeCredential table --
CREATE TABLE bank_service.employee_credential (
  id INT NOT NULL,
  employee_id INT NOT NULL,
  user_name VARCHAR(20) NOT NULL,
  password VARCHAR(200) NOT NULL,
  password_salt VARCHAR(100) NOT NULL,
  login_date_time DATETIME NULL,
  created_by INT NULL,
  created_at DATETIME NULL,
  updated_by INT NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES bank_service.employee(id)
);

-- create admin table --

CREATE TABLE bank_service.admin (
  id INT NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(45) NOT NULL,
  mobile_number VARCHAR(10) NOT NULL,
  email VARCHAR(45) NULL,
  gender CHAR(1) NOT NULL,
  date_of_birth DATE NOT NULL,
  adhaar_number VARCHAR(12) NOT NULL,
  address TEXT NULL,
  state VARCHAR(20) NOT NULL,
  city VARCHAR(20) NOT NULL,
  created_by INT NULL,
  created_at DATETIME NULL,
  updated_by INT NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX mobile_number_UNIQUE (mobile_number ASC) VISIBLE,
  UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE);


-- create adminCrededntial table --

CREATE TABLE bank_service.admin_credential (
  id INT NOT NULL,
  admin_id INT NOT NULL,
  user_name VARCHAR(20) NOT NULL,
  password VARCHAR(200) NOT NULL,
  password_salt VARCHAR(100) NOT NULL,
  login_date_time DATETIME NULL,
  created_by INT NULL,
  created_at DATETIME NULL,
  updated_by INT NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (admin_id) REFERENCES bank_service.admin(id)
);





