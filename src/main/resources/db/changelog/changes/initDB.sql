INSERT INTO country(id, name)
VALUES
    (1,'Russia');

INSERT INTO region(id, country_id, name)
VALUES
    (1,1,'Perm_region'),
    (2,1,'Moscow_region'),
    (3,1,'Sverdlovsk_region');

INSERT INTO city(id, lat, lon, name, region_id)
VALUES
    (1,55.7522,37.6156,'Moscow',2),
    (2,55.4242,37.5547,'Podolsk',2),
    (3,55.9017,37.4375,'Khimki',2),
    (4,55.678,37.2777,'Odintsovo',2),
    (5,55.8225,37.3181,'Krasnogorsk',2),
    (6,58.0174,56.2855,'Perm',1),
    (7,59.4152,56.8124,'Berezniki',1),
    (8,59.6196,56.7729,'Solikamsk',1),
    (9,57.4368,56.9593,'Kungur',1),
    (10,58.8386,57.5532,'Gubakha',1),
    (11,56.8575,60.6125,'Yekaterinburg',3),
    (12,56.9053,59.9436,'Pervouralsk',3),
    (13,56.4422,60.1878,'Polevskoy',3),
    (14,57.2439,60.0839,'Novouralsk',3),
    (15,59.7654,60.0085,'Karpinsk',3);