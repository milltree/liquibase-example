INSERT INTO productgroup (name) VALUES ('Weather');

INSERT INTO product (name, price, productgroup_id) VALUES ('Snow', 13.59, (SELECT g.id FROM productgroup g WHERE g.name = 'Weather'));
INSERT INTO product (name, price, productgroup_id) VALUES ('Sun', 50.0, (SELECT g.id FROM productgroup g WHERE g.name = 'Weather'));
INSERT INTO product (name, price, productgroup_id) VALUES ('Rain', 199.9, (SELECT g.id FROM productgroup g WHERE g.name = 'Weather'));