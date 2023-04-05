CREATE TABLE addresses
(
  id         INT AUTO_INCREMENT NOT NULL,
  city       VARCHAR(255)       NULL,
  street     VARCHAR(255)       NULL,
  member_id  INT                NOT NULL,
  PRIMARY KEY (id)
) COMMENT '주소';

CREATE TABLE categories
(
  id             INT AUTO_INCREMENT NOT NULL,
  category_code  VARCHAR(255)       NULL,
  description    TEXT               NULL,
  parent_id      INT                NOT NULL,
  PRIMARY KEY (id)
) COMMENT '카테고리';

CREATE TABLE deliveries
(
  id          INT AUTO_INCREMENT NOT NULL,
  courier     VARCHAR(255)       NULL,
  tracking_no VARCHAR(255)       NULL,
  address_id  INT                NOT NULL,
  PRIMARY KEY (id)
) COMMENT '배송';

CREATE TABLE items
(
  id           INT AUTO_INCREMENT NOT NULL,
  name         VARCHAR(255)       NULL,
  price        DECIMAL(10, 2)     NULL,
  category_id  INT                NOT NULL,
  PRIMARY KEY (id)
) COMMENT '상품';

CREATE TABLE members
(
  id          INT AUTO_INCREMENT NOT NULL,
  name        VARCHAR(255)       NULL,
  email       VARCHAR(255)       NULL UNIQUE,
  password    VARCHAR(255)       NULL,
  phone       VARCHAR(255)       NULL,
  role        ENUM('user', 'admin') DEFAULT 'user',
  created_at  TIMESTAMP          NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  TIMESTAMP          NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) COMMENT '회원';

CREATE TABLE orders
(
  id               INT AUTO_INCREMENT NOT NULL,
  total_price      DECIMAL(10, 2)     NULL,
  member_id        INT                NOT NULL,
  order_status_id  INT                NOT NULL,
  delivery_id      INT                NOT NULL,
  PRIMARY KEY (id)
) COMMENT '주문';

CREATE TABLE order_items
(
  id        INT AUTO_INCREMENT NOT NULL,
  quantity  INT                NULL,
  price     DECIMAL(10, 2)     NULL,
  item_id   INT                NOT NULL,
  order_id  INT                NOT NULL,
  PRIMARY KEY (id)
) COMMENT '주문상품';

CREATE TABLE order_statuses
(
  id      INT AUTO_INCREMENT NOT NULL,
  status  VARCHAR(255)       NULL,
  PRIMARY KEY (id)
) COMMENT '주문상태';

ALTER TABLE addresses
  ADD CONSTRAINT FK_members_TO_addresses
    FOREIGN KEY (member_id)
    REFERENCES members (id);

ALTER TABLE categories
  ADD CONSTRAINT FK_categories_TO_categories
    FOREIGN KEY (parent_id)
    REFERENCES categories (id);

ALTER TABLE items
  ADD CONSTRAINT FK_categories_TO_items
    FOREIGN KEY (category_id)
    REFERENCES categories (id);

ALTER TABLE order_items
  ADD CONSTRAINT FK_items_TO_order_items
    FOREIGN KEY (item_id)
    REFERENCES items (id);

ALTER TABLE order_items
  ADD CONSTRAINT FK_orders_TO_order_items
    FOREIGN KEY (order_id)
    REFERENCES orders (id);

ALTER TABLE orders
  ADD CONSTRAINT FK_members_TO_orders
    FOREIGN KEY (member_id)
    REFERENCES members (id);

ALTER TABLE orders
  ADD CONSTRAINT FK_order_statuses_TO_orders
    FOREIGN KEY (order_status_id)
    REFERENCES order_statuses (id);

ALTER TABLE deliveries
  ADD CONSTRAINT FK_addresses_TO_deliveries
    FOREIGN KEY (address_id)
    REFERENCES addresses (id);

ALTER TABLE orders
  ADD CONSTRAINT FK_deliveries_TO_orders
    FOREIGN KEY (delivery_id)
    REFERENCES deliveries (id);
