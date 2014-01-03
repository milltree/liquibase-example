DELETE FROM product WHERE productgroup_id IN (SELECT g.id FROM productgroup g WHERE g.name = 'Weather');

DELETE FROM productgroup WHERE name = 'Weather';
