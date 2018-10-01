DROP TABLE IF EXISTS citystatus;
DROP SEQUENCE IF EXISTS citystatus_seq;
CREATE SEQUENCE citystatus_seq START WITH 10;
CREATE TABLE citystatus (
  citystatus_id BIGINT NOT NULL DEFAULT nextval('citystatus_seq'),
  label         VARCHAR(100)
);

DROP TABLE IF EXISTS city;
DROP SEQUENCE IF EXISTS city_seq;
CREATE SEQUENCE city_seq START WITH 1;
DROP INDEX IF EXISTS PK_CITY;
CREATE TABLE city (
  city_id          BIGINT      NOT NULL DEFAULT nextval('city_seq'),
  city_inseeid     VARCHAR(10) NOT NULL,
  name             VARCHAR(100),
  elevation        INT,
  citystatus_id_fk BIGINT      NOT NULL,
  district_inseeid VARCHAR(10) NOT NULL,
  region_inseeid   VARCHAR(10) NOT NULL
);

CREATE TABLE city_citystatus (
  city_id_fk       BIGINT NOT NULL,
  citystatus_id_fk BIGINT NOT NULL
);

DROP TABLE IF EXISTS zipcode;
DROP SEQUENCE IF EXISTS zipcode_seq;
CREATE SEQUENCE zipcode_seq START WITH 1;
CREATE TABLE zipcode (
  zipcode_id   BIGINT      NOT NULL DEFAULT nextval('zipcode_seq'),
  zipcode      VARCHAR(10) NOT NULL,
  city_inseeid VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS district;
DROP SEQUENCE IF EXISTS district_seq;
CREATE SEQUENCE district_seq START WITH 1;
CREATE TABLE district (
  district_id      BIGINT      NOT NULL DEFAULT nextval('district_seq'),
  district_inseeid VARCHAR(10) NOT NULL,
  name             VARCHAR(100),
  name_uppercase   VARCHAR(100),
  cheflieu_inseeid VARCHAR(10),
  region_inseeid   VARCHAR(10)
);

DROP TABLE IF EXISTS region;
DROP SEQUENCE IF EXISTS region_seq;
CREATE SEQUENCE region_seq START WITH 1;
CREATE TABLE region (
  region_id        BIGINT      NOT NULL DEFAULT nextval('region_seq'),
  region_inseeid   VARCHAR(10) NOT NULL,
  name             VARCHAR(100),
  name_uppercase   VARCHAR(100),
  cheflieu_inseeid VARCHAR(10)
)