START TRANSACTION;

ALTER TABLE city
  ADD CONSTRAINT FK_CITY_CITYSTATUS FOREIGN KEY (citystatus_id_fk) REFERENCES citystatus (citystatus_id);
ALTER TABLE city
  ADD CONSTRAINT FK_CITY_REGION FOREIGN KEY (region_inseeid) REFERENCES region (region_inseeid);
ALTER TABLE city
  ADD CONSTRAINT FK_CITY_DISTRICT FOREIGN KEY (district_inseeid) REFERENCES district (district_inseeid);
ALTER TABLE zipcode
  ADD CONSTRAINT FK_ZIPCODE_CITY FOREIGN KEY (city_inseeid) REFERENCES city (city_inseeid);
ALTER TABLE city_citystatus
  ADD CONSTRAINT fk_city_citystatus_city FOREIGN KEY (city_id_fk) REFERENCES city (city_id);
ALTER TABLE city_citystatus
  ADD CONSTRAINT fk_city_citystatus_citystatus FOREIGN KEY (citystatus_id_fk) REFERENCES citystatus (citystatus_id);
ALTER TABLE district
  ADD CONSTRAINT FK_DISTRICT_REGION FOREIGN KEY (region_inseeid) REFERENCES region (region_inseeid);

COMMIT;
END TRANSACTION;