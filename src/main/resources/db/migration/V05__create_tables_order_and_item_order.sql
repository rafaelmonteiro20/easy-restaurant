CREATE TABLE `order` (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    created_on DATETIME NOT NULL,
    discount DECIMAL(10,2),
    total_price DECIMAL(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    customer_id BIGINT(20) NOT NULL,
    user_id BIGINT(20) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item_order (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    amount INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    product_id BIGINT(20) NOT NULL,
    order_id BIGINT(20) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (order_id) REFERENCES `order`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
