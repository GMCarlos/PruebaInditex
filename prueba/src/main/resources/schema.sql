DROP TABLE IF EXISTS "price";
DROP TABLE IF EXISTS "brand";
DROP TABLE IF EXISTS "product";


CREATE TABLE "brand" (
    "id" BIGSERIAL NOT NULL,
    "name" VARCHAR(10),
    CONSTRAINT brand_pk PRIMARY KEY ("id")
);

CREATE TABLE "product" (
    "id" BIGSERIAL NOT NULL,
    "rate" VARCHAR(10),
    CONSTRAINT product_pk PRIMARY KEY ("id")
);

CREATE TABLE "price" (
    "brand_id" INTEGER NOT NULL,
    "start_date" TIMESTAMP NOT NULL,
    "end_date" TIMESTAMP NOT NULL,
    "price_list" BIGSERIAL NOT NULL,
    "product_id" INTEGER NOT NULL,
    "priority" INTEGER NOT NULL,
    "price" DECIMAL(10, 2) NOT NULL,
    "currency" VARCHAR(3) NOT NULL,
    CONSTRAINT price_pk PRIMARY KEY ("price_list"),
    CONSTRAINT price_brand_pk FOREIGN KEY ("brand_id") REFERENCES "brand"("id"),
    CONSTRAINT price_product_pk FOREIGN KEY ("product_id") REFERENCES "product"("id")
);