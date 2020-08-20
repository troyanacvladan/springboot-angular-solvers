-- AFOS test data
INSERT INTO TBL_INGREDIENTS
    (id,code, name, price)
VALUES
    (1,'1000', 'Oats', 9.5),
    (2,'1004', 'Corn (Maize)', 94.3),
    (3,'1070', 'Fish Meal 60', 5.05);

INSERT INTO TBL_FORMULA_INGREDIENTS
    (id,ingredient_id, min, max)
VALUES
    (1,1, 30, null),
    (2,2, 10, null),
    (3,3, 20, 80);

INSERT INTO TBL_NUTRIENTS
(code, name)
VALUES
('100', 'Dry Matter'),
('101', 'Crude Protein'),
('102', 'Fat');