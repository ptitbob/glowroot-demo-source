START TRANSACTION;

INSERT INTO city_citystatus
  SELECT
    c.city_id,
    status.id
  FROM city c, (SELECT x.id
                FROM generate_series(1, 5) AS x(id)) AS status
  WHERE citystatus_id_fk = 1;
INSERT INTO city_citystatus
  SELECT
    c.city_id,
    status.id
  FROM city c, (SELECT x.id
                FROM generate_series(2, 5) AS x(id)) AS status
  WHERE citystatus_id_fk = 2;
INSERT INTO city_citystatus
  SELECT
    c.city_id,
    status.id
  FROM city c, (SELECT x.id
                FROM generate_series(3, 5) AS x(id)) AS status
  WHERE citystatus_id_fk = 3;
INSERT INTO city_citystatus
  SELECT
    c.city_id,
    status.id
  FROM city c, (SELECT x.id
                FROM generate_series(4, 5) AS x(id)) AS status
  WHERE citystatus_id_fk = 4;
INSERT INTO city_citystatus
  SELECT
    c.city_id,
    status.id
  FROM city c, (SELECT x.id
                FROM generate_series(5, 5) AS x(id)) AS status
  WHERE citystatus_id_fk = 5;

COMMIT;
END TRANSACTION;