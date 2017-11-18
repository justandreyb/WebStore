-- -----------------------------------------------------
-- procedure getUser
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getUser`(in email varchar(120), in password varchar(135))
  BEGIN
    SELECT
      user.id,
      customer_info.first_name,
      customer_info.last_name,
      customer_info.gender,
      customer_info.phone_number,
      customer_info.address,
      user.locale,
      role.value AS role
    FROM web_store.user
      LEFT JOIN web_store.customer_info
        ON
          customer_info.customer_id = user.id
      LEFT JOIN web_store.role
        ON
          role.id = user.role_id
    WHERE
      user.email = email AND
      user.password = md5(password) AND
      user.is_active = 1
    ;

  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure searchProduct
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `searchProduct`(in request varchar(130))
  BEGIN
    SELECT
      product.id,
      product.name,
      product.price,
      discount.value AS discount
    FROM
      web_store.product
      LEFT JOIN web_store.discount ON
                                     discount.id = product.discount_id
    WHERE
      product.name LIKE CONCAT('%', request, '%') AND
      product.is_available = 1;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getThingsForProduct
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getThingsForProduct`(in product int)
  BEGIN
    SELECT
      thing_to_product.amount,
      brand.name AS brand,
      category.name AS category,
      thing.id,
      thing.name,
      thing.description,
      thing.creation_date,
      thing.review
    FROM
      web_store.thing_to_product
      INNER JOIN web_store.thing ON
                                   thing.id = thing_to_product.thing_id
      INNER JOIN web_store.brand ON
                                   thing.brand_id = brand.id
      INNER JOIN web_store.category ON
                                      thing.category_id = category.id
    WHERE
      thing_to_product.product_id = product AND
      thing.is_available = 1 AND
      category.is_available = 1;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getProducts
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getProducts`()
  BEGIN
    SELECT
      product.id,
      product.name,
      product.price,
      discount.value AS discount,
      category.id AS category_id,
      category.name AS category
    FROM
      web_store.product
      INNER JOIN web_store.category ON
                                      product.category_id = category.id
      LEFT JOIN web_store.discount ON
                                     product.discount_id = discount.id
    WHERE
      product.is_available = 1 AND
      (discount.is_available = 1 OR discount.id IS NULL)
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getUsers
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getUsers`()
  BEGIN
    SELECT
      user.id,
      user.email,
      customer_info.first_name,
      customer_info.last_name
    FROM web_store.customer_info
      INNER JOIN web_store.user
        ON
          customer_info.customer_id = user.id
    WHERE (
      user.role_id = 1
    );

  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getThings
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getThings`()
  BEGIN
    SELECT
      thing.id,
      thing.name,
      thing.creation_date,
      thing.description,
      brand.name AS brand,
      category.name AS category
    FROM web_store.thing
      LEFT JOIN web_store.brand
        ON thing.brand_id = brand.id
      LEFT JOIN web_store.category
        ON thing.category_id = category.id
    WHERE
      (brand.is_available = 1 OR brand.id IS NULL) AND
      (category.is_available = 1 OR category.id IS NULL) AND
      thing.is_available = 1
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getCategories
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getCategories` ()
  BEGIN
    SELECT
      category.id,
      category.name,
      category.description
    FROM web_store.category
    WHERE
      category.is_available = 1
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getDiscounts
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getDiscounts` ()
  BEGIN
    SELECT
      discount.id,
      discount.value,
      discount.start_date,
      discount.finish_date
    FROM web_store.discount
    WHERE
      discount.is_available = 1
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updateUser
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `updateUser` (
  in user_id int,
  in email varchar(135),
  in password varchar(135),
  in locale varchar(45),
  in first_name varchar(135),
  in last_name varchar(135),
  in gender enum('Male','Female', 'Undefined'),
  in phone_number varchar(15),
  in address varchar(255)
)
  BEGIN
    DECLARE `should_rollback` BOOL DEFAULT FALSE;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET `should_rollback` = TRUE;

    START TRANSACTION;

    UPDATE web_store.user
    SET
      user.email = email,
      user.password = md5(password),
      user.locale = locale
    WHERE
      user.id = user_id
    ;

    UPDATE web_store.customer_info
    SET
      customer_info.first_name = first_name,
      customer_info.last_name = last_name,
      customer_info.gender = gender,
      customer_info.phone_number = phone_number,
      customer_info.address = address
    WHERE
      customer_info.customer_id = user_id
    ;

    IF `should_rollback` THEN
      ROLLBACK;
    ELSE
      COMMIT;
    END IF;

  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updateThing
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `updateThing` (
  in thing_id int,
  in name varchar(45),
  in description varchar(1000),
  in creation_date date,
  in review mediumtext
)
  BEGIN
    UPDATE web_store.thing
    SET
      thing.name = name,
      thing.description = description,
      thing.creation_date = creation_date,
      thing.review = review
    WHERE
      thing.id = thing_id
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure updateProduct
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `updateProduct` (
  in product_id int,
  in name varchar(135),
  in price decimal(9,4),
  in category_id int,
  in discount_id int
)
  BEGIN
    UPDATE web_store.product
    SET
      product.name = name,
      product.price = price,
      product.category_id = category_id,
      product.discount_id = discount_id
    WHERE
      product.id = product_id
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getProductsForDiscount
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getProductsForDiscount` (in discount_id int)
  BEGIN
    SELECT
      product.id,
      product.name,
      product.price,
      image.href AS image
    FROM web_store.product
      LEFT JOIN web_store.image
        ON product.id = image.product_id
      INNER JOIN web_store.discount
        ON product.discount_id = discount.id
    WHERE
      discount.id = discount_id AND
      discount.is_available = 1 AND
      product.is_available = 1
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure addThingToProduct
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `addThingToProduct` ()
  BEGIN

  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getBrands
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getBrands`()
BEGIN
SELECT
  brand.id,
  brand.name,
  brand.description
FROM
  web_store.brand
WHERE
  brand.is_available = 1
;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure addUser
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `addUser`(
  in email varchar(135),
  in pass varchar(135),
  in locale varchar(45),
  in first_name varchar(135),
  in last_name varchar(135),
  in gender varchar(15),
  in phone_number varchar(15),
  in address varchar(255),
  in role int
)
  BEGIN
    DECLARE `should_rollback` BOOL DEFAULT FALSE;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET `should_rollback` = TRUE;

    START TRANSACTION;

    INSERT INTO web_store.user (
      user.email,
      user.password,
      user.locale,
      user.role_id
    ) VALUES (
      email,
      md5(pass),
      locale,
      role
    );

    INSERT INTO web_store.customer_info (
      customer_info.first_name,
      customer_info.last_name,
      customer_info.gender,
      customer_info.phone_number,
      customer_info.address,
      customer_info.customer_id
    ) VALUES (
      first_name,
      last_name,
      gender,
      address,
      phone_number,
      last_insert_id()
    );

    IF `should_rollback` THEN
      ROLLBACK;
    ELSE
      COMMIT;
    END IF;

  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getThing
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getThing`(in thing_id int)
  BEGIN
    SELECT
      thing.id,
      thing.name,
      thing.creation_date,
      thing.description,
      brand.name AS brand,
      category.name AS category
    FROM web_store.thing
      LEFT JOIN web_store.brand
        ON thing.brand_id = brand.id
      LEFT JOIN web_store.category
        ON thing.category_id = category.id
    WHERE
      thing.id = thing_id AND
      (brand.is_available = 1 OR brand.id IS NULL) AND
      (category.is_available = 1 OR category.id IS NULL)
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getProductsForCategory
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getProductsForCategory`(in categoryId int)
  BEGIN
    SELECT
      product.id,
      product.name,
      product.price,
      discount.value AS discount,
      image.href AS image
    FROM
      web_store.product
      INNER JOIN web_store.category ON
                                      product.category_id = category.id
      LEFT JOIN web_store.image ON
                                  product.id = image.product_id
      LEFT JOIN web_store.discount ON
                                     product.discount_id = discount.id
    WHERE
      category.id = categoryId AND
      product.is_available = 1 AND
      (discount.is_available = 1 OR discount.id IS NULL) AND
      (image.is_available = 1 OR image.id IS NULL);
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getProduct
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getProduct`(in product_id int)
  BEGIN
    SELECT
      product.id,
      product.name,
      product.price,
      discount.value AS discount,
      image.href AS image,
      category.id AS category_id,
      category.name AS category
    FROM
      web_store.product
      INNER JOIN web_store.category ON
                                      product.category_id = category.id
      LEFT JOIN web_store.image ON
                                  product.id = image.product_id
      LEFT JOIN web_store.discount ON
                                     product.discount_id = discount.id
    WHERE
      product.id = product_id AND
      product.is_available = 1 AND
      (discount.is_available = 1 OR discount.id IS NULL) AND
      (image.is_available = 1 OR image.id IS NULL);
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getPhotosForThing
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getPhotosForThing`(in thing_id int)
  BEGIN
    SELECT
      image.id,
      image.href
    FROM web_store.image
    WHERE
      image.thing_id = thing_id AND
      image.is_available = 1
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getPhotosForProduct
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getPhotosForProduct`(in product_id int)
  BEGIN
    SELECT
      image.id,
      image.href
    FROM web_store.image
    WHERE
      image.product_id = product_id AND
      image.is_available = 1
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getPhotos
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getPhotos`()
  BEGIN
    SELECT
      image.id,
      image.href
    FROM web_store.image
    WHERE
      image.is_available = 1
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getDiscount
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getDiscount`(in discount_id int)
  BEGIN
    SELECT
      discount.id,
      discount.value,
      discount.start_date,
      discount.finish_date
    FROM
      web_store.discount
    WHERE
      discount.is_available = 1 AND
      discount.id = discount_id
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getCategory
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getCategory`(in category_id int)
  BEGIN
    SELECT
      category.id,
      category.name,
      category.description
    FROM
      web_store.category
    WHERE
      category.is_available = 1 AND
      category.id = category_id
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getBrand
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getBrand`(in brand_id int)
  BEGIN
    SELECT
      brand.id,
      brand.name,
      brand.description
    FROM
      web_store.brand
    WHERE
      brand.is_available = 1 AND
      brand.id = brand_id
    ;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getProductsForOrder
-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE `getProductsForOrder`(in orderId int)
  BEGIN
    SELECT
      product.id,
      product.name,
      product.price,
      discount.value AS discount,
      image.href AS image
    FROM
      web_store.product
      LEFT JOIN web_store.product_order ON
                                          product.id = product_order.product_id
      LEFT JOIN web_store.image ON
                                  product.id = image.product_id
      LEFT JOIN web_store.discount ON
                                     product.discount_id = discount.id
    WHERE
      product_order.order_id = orderId AND
      product.is_available = 1 AND
      (discount.is_available = 1 OR discount.id IS NULL) AND
      (image.is_available = 1 OR image.id IS NULL);
  END$$

DELIMITER ;