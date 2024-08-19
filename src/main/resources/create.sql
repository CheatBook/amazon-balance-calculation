CREATE TABLE product_category (
    sky VARCHAR(255) PRIMARY KEY,                     -- sky: VARCHAR型で最大255文字
    ASIN VARCHAR(255) UNIQUE,            -- ASIN: ユニーク制約を持つVARCHAR型
    category_id  VARCHAR(255),
    product_name VARCHAR(255),           -- product_name: VARCHAR型で最大255文字
    unit_price DECIMAL(10, 2)            -- unit_price: DECIMAL型で最大10桁（うち2桁小数点）
);

CREATE TABLE settlement_info (
    settlement_id VARCHAR(255) PRIMARY KEY,
    settlement_start_date DATE,
    settlement_end_date DATE,
    deposit_date DATE,
    total_amount DECIMAL(10, 2),
    currency VARCHAR(10)
) ENGINE=InnoDB;

CREATE TABLE settlement_info_detail (
    settlement_id VARCHAR(255),
    order_id VARCHAR(100),
    amount_description VARCHAR(100),
    transaction_type VARCHAR(50),
    merchant_order_id VARCHAR(255),
    adjustment_id VARCHAR(255),
    shipment_id VARCHAR(255),
    marketplace_name VARCHAR(255),
    amount_type VARCHAR(50),
    amount DECIMAL(10, 2),
    fulfillment_id VARCHAR(255),
    posted_date DATE,
    posted_date_time DATETIME,
    order_item_code VARCHAR(255),
    merchant_order_item_id VARCHAR(255),
    merchant_adjustment_item_id VARCHAR(255),
    sku VARCHAR(255),
    quantity_purchased INT,
    promotion_id VARCHAR(255),
    PRIMARY KEY (settlement_id, order_id, amount_description, transaction_type),
    FOREIGN KEY (settlement_id) REFERENCES settlement_info(settlement_id)
) ENGINE=InnoDB;
    
CREATE TABLE Adexpenses (
    target_date DATE NOT NULL,
    sku VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (target_date, sku)
);