-- -----------------------------------------------------
-- Schema web_store
-- -----------------------------------------------------
-- DROP SCHEMA IF EXISTS `WEB_STORE` ;

-- -----------------------------------------------------
-- Schema web_store
-- -----------------------------------------------------
-- CREATE SCHEMA IF NOT EXISTS `WEB_STORE` DEFAULT CHARACTER SET utf8 ;
-- USE `WEB_STORE` ;

-- -----------------------------------------------------
-- Table `web_store`.`category`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`category` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TINYTEXT NULL,
  `is_available` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  FULLTEXT INDEX `name_idx` (`name` ASC),
  INDEX `category_id_idx` (`id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`brand`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`brand` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TINYTEXT NULL,
  `is_available` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  FULLTEXT INDEX `name_idx` (`name` ASC),
  INDEX `brand_available_idx` (`is_available` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`thing`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`thing` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `category_id` SMALLINT UNSIGNED NULL,
  `description` VARCHAR(1000) NULL,
  `creation_date` DATE NULL,
  `review` MEDIUMTEXT NULL,
  `brand_id` INT UNSIGNED NULL,
  `is_available` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_category_id` (`category_id` ASC),
  INDEX `fk_thing_brand1_idx` (`brand_id` ASC),
  FULLTEXT INDEX `name_idx` (`name` ASC),
  INDEX `thing_available_idx` (`is_available` ASC),
  CONSTRAINT `fk_category_id`
  FOREIGN KEY (`category_id`)
  REFERENCES `web_store`.`category` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_thing_brand1`
  FOREIGN KEY (`brand_id`)
  REFERENCES `web_store`.`brand` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`discount`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`discount` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `value` TINYINT(2) UNSIGNED NOT NULL,
  `start_date` DATETIME NOT NULL,
  `finish_date` DATETIME NOT NULL,
  `is_available` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `discount_available_idx` (`is_available` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`product`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`product` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(135) NOT NULL,
  `category_id` SMALLINT UNSIGNED NULL,
  `is_available` TINYINT(1) NOT NULL DEFAULT 1,
  `discount_id` INT UNSIGNED NULL,
  `price` DECIMAL(9,4) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `discount_id_idx` (`discount_id` ASC),
  INDEX `fk_product_category_idx` (`category_id` ASC),
  FULLTEXT INDEX `name_idx` (`name` ASC),
  INDEX `product_price_idx` (`price` ASC),
  INDEX `product_available_idx` (`is_available` ASC),
  CONSTRAINT `discount_id`
  FOREIGN KEY (`discount_id`)
  REFERENCES `web_store`.`discount` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_product_category1`
  FOREIGN KEY (`category_id`)
  REFERENCES `web_store`.`category` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`role`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `value_UNIQUE` (`value` ASC),
  INDEX `role_id_idx` (`id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(135) NOT NULL,
  `password` VARCHAR(135) NOT NULL,
  `is_active` TINYINT(1) NOT NULL DEFAULT 1,
  `role_id` INT UNSIGNED NOT NULL,
  `locale` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_role_id_idx` (`role_id` ASC),
  FULLTEXT INDEX `user_email_idx` (`email` ASC),
  FULLTEXT INDEX `user_password_idx` (`password` ASC),
  INDEX `user_role_idx` (`role_id` ASC),
  INDEX `user_is_active_idx` (`is_active` ASC),
  CONSTRAINT `fk_role_id`
  FOREIGN KEY (`role_id`)
  REFERENCES `web_store`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`order`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`order` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_id` INT UNSIGNED NOT NULL,
  `creation_date` DATETIME NOT NULL,
  `price` DECIMAL(9,4) NOT NULL,
  `is_complete` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_customer_id` (`customer_id` ASC),
  INDEX `order_complete_idx` (`is_complete` ASC),
  CONSTRAINT `fk_customer`
  FOREIGN KEY (`customer_id`)
  REFERENCES `web_store`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`product_order`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`product_order` (
  `product_id` INT UNSIGNED NOT NULL,
  `order_id` INT UNSIGNED NOT NULL,
  `product_amount` TINYINT(2) UNSIGNED NOT NULL,
  INDEX `fk_product_id` (`product_id` ASC),
  INDEX `fk_order_id` (`order_id` ASC),
  PRIMARY KEY (`product_id`, `order_id`),
  CONSTRAINT `fk_product`
  FOREIGN KEY (`product_id`)
  REFERENCES `web_store`.`product` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order`
  FOREIGN KEY (`order_id`)
  REFERENCES `web_store`.`order` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`payment`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`payment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `payment_date` DATETIME NOT NULL,
  `card_number` VARCHAR(17) NOT NULL,
  `amount` DECIMAL(9,4) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `payment_id_idx` (`id` ASC),
  INDEX `payment_amount_idx` (`amount` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`image`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`image` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `href` VARCHAR(350) NOT NULL,
  `real_name` VARCHAR(255) NOT NULL,
  `thing_id` INT UNSIGNED NULL,
  `is_available` TINYINT(1) NOT NULL DEFAULT 1,
  `product_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_image_thing_idx` (`thing_id` ASC),
  FULLTEXT INDEX `href_idx` (`href` ASC),
  INDEX `image_available_idx` (`is_available` ASC),
  INDEX `image_id_idx` (`id` ASC),
  INDEX `fk_image_product1_idx` (`product_id` ASC),
  CONSTRAINT `fk_product_image_thing1`
  FOREIGN KEY (`thing_id`)
  REFERENCES `web_store`.`thing` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_image_product1`
  FOREIGN KEY (`product_id`)
  REFERENCES `web_store`.`product` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`rating_to_customer`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`rating_to_customer` (
  `thing_id` INT UNSIGNED NOT NULL,
  `customer_id` INT UNSIGNED NOT NULL,
  `value` TINYINT(2) UNSIGNED NOT NULL,
  `rating_date` DATETIME NOT NULL,
  PRIMARY KEY (`thing_id`, `customer_id`),
  INDEX `customer_id_idx` (`customer_id` ASC),
  INDEX `fk_raiting_to_customer_thing_idx` (`thing_id` ASC),
  INDEX `rating_value_idx` (`value` ASC),
  CONSTRAINT `customer_id`
  FOREIGN KEY (`customer_id`)
  REFERENCES `web_store`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_raiting_to_customer_thing1`
  FOREIGN KEY (`thing_id`)
  REFERENCES `web_store`.`thing` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`thing_to_product`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`thing_to_product` (
  `thing_id` INT UNSIGNED NOT NULL,
  `product_id` INT UNSIGNED NOT NULL,
  `amount` INT UNSIGNED NULL,
  PRIMARY KEY (`thing_id`, `product_id`),
  INDEX `product_id_idx` (`product_id` ASC),
  CONSTRAINT `thing_id`
  FOREIGN KEY (`thing_id`)
  REFERENCES `web_store`.`thing` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `product_id`
  FOREIGN KEY (`product_id`)
  REFERENCES `web_store`.`product` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`customer_info`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`customer_info` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `gender` ENUM('Male', 'Female', 'Undefined') NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `customer_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_administrator_customer1_idx` (`customer_id` ASC),
  INDEX `user_id_idx` (`customer_id` ASC),
  CONSTRAINT `fk_administrator_customer1`
  FOREIGN KEY (`customer_id`)
  REFERENCES `web_store`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `web_store`.`payment_to_order`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `web_store`.`payment_to_order` (
  `date` DATETIME NOT NULL,
  `payment_id` INT UNSIGNED NOT NULL,
  `order_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`payment_id`, `order_id`),
  INDEX `fk_payment_to_order_payment1_idx` (`payment_id` ASC),
  INDEX `fk_payment_to_order_order1_idx` (`order_id` ASC),
  CONSTRAINT `fk_payment_to_order_payment1`
  FOREIGN KEY (`payment_id`)
  REFERENCES `web_store`.`payment` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_payment_to_order_order1`
  FOREIGN KEY (`order_id`)
  REFERENCES `web_store`.`order` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

USE `web_store` ;

-- -----------------------------------------------------
-- Data for table `web_store`.`role`
-- -----------------------------------------------------
START TRANSACTION;

INSERT INTO `web_store`.`role` (`id`, `value`) VALUES (1, 'User');
INSERT INTO `web_store`.`role` (`id`, `value`) VALUES (2, 'Admin');

COMMIT;


DELIMITER $$

DROP TRIGGER IF EXISTS `web_store`.`thing_BEFORE_INSERT` $$
CREATE TRIGGER `web_store`.`thing_BEFORE_INSERT` BEFORE INSERT ON `thing` FOR EACH ROW
  BEGIN
    SET new.creation_date = now();
  END$$


DROP TRIGGER IF EXISTS `web_store`.`product_BEFORE_UPDATE` $$
CREATE TRIGGER `web_store`.`product_BEFORE_UPDATE` BEFORE UPDATE ON `product` FOR EACH ROW
  BEGIN
    IF NEW.discount_id = 0 THEN
      SET NEW.discount_id = NULL;
    END IF;
  END$$


DROP TRIGGER IF EXISTS `web_store`.`rating_to_customer_BEFORE_INSERT` $$
CREATE TRIGGER `web_store`.`rating_to_customer_BEFORE_INSERT` BEFORE INSERT ON `rating_to_customer` FOR EACH ROW
  BEGIN
    SET NEW.rating_date = now();
  END$$


DELIMITER ;
