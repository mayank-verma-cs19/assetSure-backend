package com.example.assetSure.sql;

public class TabelsQuery {

    /*

    database password - Magnum-2001
     Create database collateral_management;

use collateral_management;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100),
    role VARCHAR(50),
    phone VARCHAR(15),
    address VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    address VARCHAR(255),
    id_proof_type VARCHAR(50),
    id_proof_no VARCHAR(50),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE security_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    item_type VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    weight DECIMAL(10,2),
    purity VARCHAR(20),
    estimated_value DECIMAL(15,2),
    stored_at VARCHAR(100),
    status VARCHAR(50),
    remarks TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    security_id BIGINT NOT NULL,
    principal DECIMAL(15,2) NOT NULL,
    start_date DATE,
    end_date DATE,
    interest_type VARCHAR(20),
    interest_rate DECIMAL(5,2),
    compounded_freq VARCHAR(20),
    total_interest DECIMAL(15,2),
    status VARCHAR(50),
    remarks TEXT,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (security_id) REFERENCES security_items(id)
);

CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_id BIGINT NOT NULL,
    payment_date DATE,
    amount DECIMAL(15,2) NOT NULL,
    mode VARCHAR(50),
    remarks TEXT,
    FOREIGN KEY (transaction_id) REFERENCES transactions(id)
);

CREATE TABLE master_settings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    setting_key VARCHAR(50) NOT NULL,
    setting_value VARCHAR(100) NOT NULL,
    updated_by BIGINT,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (updated_by) REFERENCES users(id)
);

    * */
}
