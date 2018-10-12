START TRANSACTION;
INSERT INTO citystatus (citystatus_id, label) VALUES (1, 'Capitale d''état');
INSERT INTO citystatus (citystatus_id, label) VALUES (2, 'Préfecture de région');
INSERT INTO citystatus (citystatus_id, label) VALUES (3, 'Préfecture');
INSERT INTO citystatus (citystatus_id, label) VALUES (4, 'Sous-préfecture');
INSERT INTO citystatus (citystatus_id, label) VALUES (5, 'Chef-lieu canton');
INSERT INTO citystatus (citystatus_id, label) VALUES (6, 'Commune simple');
COMMIT;
END TRANSACTION;