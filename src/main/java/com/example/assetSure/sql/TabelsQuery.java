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



CREATE TABLE ledger_main_table (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    father_name VARCHAR(255),
    address TEXT,
    phone VARCHAR(50),
    amount DOUBLE,
    estimate_days INT,
    roi DOUBLE,
    date DATE,
    description TEXT,
    status VARCHAR(32) DEFAULT 'ACTIVE',
    repayment_amount DOUBLE DEFAULT 0,
    interest_charged DOUBLE DEFAULT 0,
    closed_on TIMESTAMP NULL,
    closed_by_name VARCHAR(255) NULL,
    closed_by_contact VARCHAR(50) NULL,
    final_comments TEXT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on TIMESTAMP NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE ledger_transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ledger_id BIGINT NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,   -- e.g., 'REPAYMENT', 'INTEREST_CHARGE'
    amount DOUBLE NOT NULL,
    interest DOUBLE DEFAULT 0,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    comments TEXT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on TIMESTAMP NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_ledger_transaction FOREIGN KEY (ledger_id) REFERENCES ledger_main_table(id) ON DELETE CASCADE
);

CREATE TABLE collateral_deposite (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ledger_id BIGINT NOT NULL,
    collateral_master_id BIGINT NOT NULL,
    weight DOUBLE,
    notes TEXT,
    is_returned BOOLEAN DEFAULT FALSE NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on TIMESTAMP NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    CONSTRAINT fk_ledger FOREIGN KEY (ledger_id) REFERENCES ledger_main_table(id) ON DELETE CASCADE,
    CONSTRAINT fk_collateral_master FOREIGN KEY (collateral_master_id) REFERENCES collateral_master(id) ON DELETE RESTRICT
);

CREATE TABLE collateral_master (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    material VARCHAR(255),
    purity DOUBLE,
    est_weight DOUBLE,
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on TIMESTAMP NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

ALTER TABLE collateral_master
ADD COLUMN notes VARCHAR(255),
ADD COLUMN created_by_id INT;

ALTER TABLE collateral_master
MODIFY created_on TIMESTAMP,
MODIFY updated_on TIMESTAMP;

- 1. Drop the wrong foreign key (replace FK1vjgyw2qt7nm2m8wm07bh8dk with your actual constraint name if different)
ALTER TABLE `collateral_deposite`
DROP FOREIGN KEY `FK1vjgyw2qt7nm2m8wm07bh8dk`;

-- 2. Make sure collateral_master_id has the correct foreign key
ALTER TABLE `collateral_deposite`
DROP FOREIGN KEY `FKg1wresqpta1b1pj3umjgihix9`;

ALTER TABLE `collateral_deposite`
ADD CONSTRAINT `FK_collateral_master`
FOREIGN KEY (`collateral_master_id`) REFERENCES `collateral_master` (`id`);

-- 3. Add correct foreign key for ledger_id
ALTER TABLE `collateral_deposite`
ADD CONSTRAINT `FK_ledger_main`
FOREIGN KEY (`ledger_id`) REFERENCES `ledger_main_table` (`id`);

CREATE TABLE `lender` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE settings (
    id BIGINT PRIMARY KEY,   -- singleton row with id=1
    roi DOUBLE,
    estimate_days INT,
    default_borrower_id BIGINT,
    created_by VARCHAR(100),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_default_lender FOREIGN KEY (default_lender_id) REFERENCES lender(id)
);

ALTER TABLE ledger_main_table
DROP FOREIGN KEY FK4xivbdsw35krqgixcymbm57fb;

ALTER TABLE ledger_main_table
ADD CONSTRAINT fk_lender FOREIGN KEY (lender_id) REFERENCES lender(id);


    * */
}
