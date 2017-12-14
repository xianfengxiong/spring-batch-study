
# DROP TABLE IF EXISTS product;
CREATE TABLE IF NOT EXISTS product (
  id INT NOT NULL ,
  name VARCHAR(128) NOT NULL ,
  description VARCHAR(128),
  quantity INT,
  PRIMARY KEY (id)
);