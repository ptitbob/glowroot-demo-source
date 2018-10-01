START TRANSACTION;

ALTER TABLE citystatus
  ADD CONSTRAINT pk_citystatus PRIMARY KEY (citystatus_id);

ALTER TABLE city
  ADD CONSTRAINT pk_city PRIMARY KEY (city_id);
CREATE UNIQUE INDEX UX_CITY_INSEEID
  ON city (city_inseeid);

ALTER TABLE city_citystatus
  ADD CONSTRAINT pk_city_citystatus PRIMARY KEY (city_id_fk, citystatus_id_fk);

ALTER TABLE zipcode
  ADD CONSTRAINT pk_zipcode PRIMARY KEY (zipcode_id);

ALTER TABLE district
  ADD CONSTRAINT pk_district PRIMARY KEY (district_id);
CREATE UNIQUE INDEX UX_DISTRICT_INSEEID
  ON district (district_inseeid);

ALTER TABLE region
  ADD CONSTRAINT pk_region PRIMARY KEY (region_id);
CREATE UNIQUE INDEX UX_REGION_INSEEID
  ON region (region_inseeid);

COMMIT;
END TRANSACTION;