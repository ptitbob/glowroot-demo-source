START TRANSACTION;

CREATE INDEX IX_CITY_NAME
  ON city (name);
CREATE INDEX IX_CITY_REGION_INSEEID
  ON city (region_inseeid);
CREATE INDEX IX_CITY_REGION_INSEEID_NAME
  ON city (region_inseeid, name);
CREATE INDEX IX_CITY_REGION_INSEEID_CITYSTATUS_NAME
  ON city (region_inseeid, citystatus_id_fk, name);
CREATE INDEX IX_CITY_REGION_DISTRICT_CITYSTATUS_NAME
  ON city (region_inseeid, district_inseeid, citystatus_id_fk, name);


CREATE INDEX IX_ZIPCODE_ZIPCODE
  ON zipcode (zipcode);

CREATE INDEX IX_DISTRICT_NAME
  ON district (name_uppercase);

CREATE INDEX IX_REGION_NAME
  ON region (name_uppercase);

COMMIT;
END TRANSACTION;