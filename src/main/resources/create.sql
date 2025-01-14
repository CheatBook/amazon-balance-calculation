-- 製品カテゴリ
CREATE TABLE product_category (
    sky VARCHAR(255) PRIMARY KEY,
    ASIN VARCHAR(255) UNIQUE,
    category_id  VARCHAR(255),
    product_name VARCHAR(255),
    unit_price DECIMAL(10, 2)
);

-- 決済情報
CREATE TABLE settlement_info (
    settlement_id VARCHAR(255) PRIMARY KEY,
    settlement_start_date DATE,
    settlement_end_date DATE,
    deposit_date DATE,
    total_amount DECIMAL(10, 2),
    currency VARCHAR(10)
) ENGINE=InnoDB;

CREATE TABLE `orders` ( 
    `id` bigint(20) NOT NULL AUTO_INCREMENT
    , `amazon_order_id` VARCHAR (255) COLLATE utf8mb4_bin NOT NULL
    , `merchant_order_id` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `purchase_date` datetime DEFAULT NULL
    , `last_updated_date` datetime DEFAULT NULL
    , `order_status` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `fulfillment_channel` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `sales_channel` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `order_channel` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `url` text COLLATE utf8mb4_bin
    , `ship_service_level` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `product_name` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `sku` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `asin` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `item_status` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `quantity` INT (11) DEFAULT NULL
    , `currency` VARCHAR (10) COLLATE utf8mb4_bin DEFAULT NULL
    , `item_price` DECIMAL (19, 4) DEFAULT NULL
    , `item_tax` DECIMAL (19, 4) DEFAULT NULL
    , `shipping_price` DECIMAL (19, 4) DEFAULT NULL
    , `shipping_tax` DECIMAL (19, 4) DEFAULT NULL
    , `gift_wrap_price` DECIMAL (19, 4) DEFAULT NULL
    , `gift_wrap_tax` DECIMAL (19, 4) DEFAULT NULL
    , `item_promotion_discount` DECIMAL (19, 4) DEFAULT NULL
    , `ship_promotion_discount` DECIMAL (19, 4) DEFAULT NULL
    , `ship_city` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `ship_state` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `ship_postal_code` VARCHAR (20) COLLATE utf8mb4_bin DEFAULT NULL
    , `ship_country` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL
    , `promotion_ids` text COLLATE utf8mb4_bin
    , PRIMARY KEY (`id`)
) ENGINE = InnoDB;



-- 決済情報詳細
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
    PRIMARY KEY (settlement_id, order_id, amount_description, transaction_type, amount_type),
    FOREIGN KEY (settlement_id) REFERENCES settlement_info(settlement_id)
) ENGINE=InnoDB;

-- 広告費
CREATE TABLE Adexpenses (
    target_date DATE NOT NULL,
    sku VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (target_date, sku)
);