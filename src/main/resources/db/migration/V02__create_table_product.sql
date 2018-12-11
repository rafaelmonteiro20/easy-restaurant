CREATE TABLE product (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    sku VARCHAR(8) NOT NULL,
    name VARCHAR(60) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INTEGER NOT NULL,
    active BOOLEAN NOT NULL,
    image VARCHAR(255),
    category_id BIGINT(20) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
