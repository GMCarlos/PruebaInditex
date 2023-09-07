

INSERT INTO "brand" ("id", "name") VALUES (1, 'XYZ');

INSERT INTO "product" ("id", "rate") VALUES (35455, 'standard');

INSERT INTO "price"
("brand_id", "start_date", "end_date", "price_list", "product_id", "priority", "price", "currency")
VALUES
(1, {ts '2020-06-14 00:00:00.00'}, {ts '2020-12-31 23:59:59.00'}, 1, 35455, 0, 35.50,'EUR');

INSERT INTO "price"
("brand_id", "start_date", "end_date", "price_list", "product_id", "priority", "price", "currency")
VALUES
(1, {ts '2020-06-14 15:00:00.00'}, {ts '2020-12-14 18:30:00.00'}, 2, 35455, 0, 25.45,'EUR');

INSERT INTO "price"
("brand_id", "start_date", "end_date", "price_list", "product_id", "priority", "price", "currency")
VALUES
(1, {ts '2020-06-15 00:00:00.00'}, {ts '2020-12-15 11:00:00.00'}, 3, 35455, 1, 30.50,'EUR');

INSERT INTO "price"
("brand_id", "start_date", "end_date", "price_list", "product_id", "priority", "price", "currency")
VALUES
(1, {ts '2020-06-15 16:00:00.00'}, {ts '2020-12-31 23:59:59.00'}, 4, 35455, 1, 38.95,'EUR');
