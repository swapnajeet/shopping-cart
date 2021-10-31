DROP TABLE IF EXISTS product;

CREATE TABLE product
(
    product_id  INTEGER      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
    price       DECIMAL(6, 2) NOT NULL,
    PRIMARY KEY (product_id)
);

DROP TABLE IF EXISTS orderitem;
DROP TABLE IF EXISTS orders;

CREATE TABLE orders
(
    order_id     INTEGER     NOT NULL AUTO_INCREMENT,
    order_date   DATE        NOT NULL,
    order_status VARCHAR(10) NULL,
    total_price  DECIMAL(6, 2),
    user_id       VARCHAR(50) NOT NULL,
    PRIMARY KEY (order_id)
);


CREATE TABLE orderitem
(
    order_item_id INTEGER NOT NULL AUTO_INCREMENT,
    order_id      INTEGER NOT NULL,
    product_id    INTEGER NOT NULL,
    quantity      INTEGER NOT NULL,
    created_date  DATE    NOT NULL,
    PRIMARY KEY (order_item_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id)
);


DROP TABLE IF EXISTS news;

CREATE TABLE news
(
    news_id    INTEGER      NOT NULL AUTO_INCREMENT,
    content    VARCHAR(500) NOT NULL,
    start_date DATE         NOT NULL,
    end_date   DATE         NOT NULL,
    PRIMARY KEY (news_id)
);

DROP TABLE IF EXISTS cartitem;

CREATE TABLE cartitem
(
    entry_id     INTEGER     NOT NULL AUTO_INCREMENT,
    product_id   INTEGER     NOT NULL,
    quantity     INTEGER     NOT NULL,
    created_date DATE        NOT NULL,
    user_id      VARCHAR(50) NOT NULL,
    PRIMARY KEY (entry_id)
);