-- AFOS test data
INSERT INTO TBL_INGREDIENTS
    (code, name, price)
VALUES
    ('1000', 'Oats', 9.5),
    ('1004', 'Corn (Maize)', 94.3),
    ('1070', 'Fish Meal 60', 5.05);

INSERT INTO TBL_FORMULA_INGREDIENTS
    (ingredient_id, min, max)
VALUES
    (1, 30, null),
    (2, 10, null),
    (3, 20, 80);

INSERT INTO TBL_NUTRIENTS
    (code, name)
VALUES
    ('100', 'Dry Matter'),
    ('101', 'Crude Protein'),
    ('102', 'Fat');

INSERT INTO TBL_FORMULA_NUTRIENTS
    (nutrient_id, min, max)
VALUES
    (1, 60, null),
    (2, 20, null),
    (3, null, 20);

INSERT INTO TBL_INGREDIENT_NUTRIENTS
    (ingredient_id,nutrient_id, value)
VALUES
    (1, 1, 87),
    (1, 2, 10.5),
    (1, 3, 5),
    (2, 1, 87),
    (2, 2, 9),
    (2, 3, 4),
    (3, 1, 87),
    (3, 2, 9),
    (3, 3, 4);
