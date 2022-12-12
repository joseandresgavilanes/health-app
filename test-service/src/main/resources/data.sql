INSERT INTO tbl_categories (id, name) VALUES (1, 'sangre');
INSERT INTO tbl_categories (id, name) VALUES (2, 'embarazo');
INSERT INTO tbl_categories (id, name) VALUES (3, 'genético');

INSERT INTO tbl_tests (id, name, description, stock,price,status, create_at,category_id)
VALUES (1, 'Test de sangre avanzado','Test de sangre para medir tu porcentaje de grasa, sangre y oxigeno',15,15.99,'CREATED','2022-12-11',1);

INSERT INTO tbl_tests (id, name, description, stock,price,status, create_at,category_id)
VALUES (2, 'Test de embarazo', 'Prueba de embarazo simple pero efectiva',4,12.5,'CREATED','2022-12-11',2);


INSERT INTO tbl_tests (id, name, description, stock,price,status, create_at,category_id)
VALUES (3,'Test de paternindad', 'Test para comprobar la relación genética entre dos personas',12,40.5,'CREATED','2022-12-11',3);
