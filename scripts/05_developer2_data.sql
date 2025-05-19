-- Insertar datos de prueba en las tablas de Developer 2

-- Insertar tipos de pago
INSERT INTO payment_type (id_payment_type, name) VALUES (seq_payment_type.NEXTVAL, 'Efectivo');
INSERT INTO payment_type (id_payment_type, name) VALUES (seq_payment_type.NEXTVAL, 'Tarjeta de Crédito');
INSERT INTO payment_type (id_payment_type, name) VALUES (seq_payment_type.NEXTVAL, 'Tarjeta de Débito');
INSERT INTO payment_type (id_payment_type, name) VALUES (seq_payment_type.NEXTVAL, 'Transferencia Bancaria');

-- Insertar tipos de estado de pedido
INSERT INTO order_status_type (id_type_state, name) VALUES (seq_order_status_type.NEXTVAL, 'Pendiente');
INSERT INTO order_status_type (id_type_state, name) VALUES (seq_order_status_type.NEXTVAL, 'En Preparación');
INSERT INTO order_status_type (id_type_state, name) VALUES (seq_order_status_type.NEXTVAL, 'Listo para Entrega');
INSERT INTO order_status_type (id_type_state, name) VALUES (seq_order_status_type.NEXTVAL, 'Entregado');
INSERT INTO order_status_type (id_type_state, name) VALUES (seq_order_status_type.NEXTVAL, 'Cancelado');

-- Insertar categorías
INSERT INTO category (category_id, name, state) VALUES (seq_category.NEXTVAL, 'Entradas', 'Activo');
INSERT INTO category (category_id, name, state) VALUES (seq_category.NEXTVAL, 'Platos Principales', 'Activo');
INSERT INTO category (category_id, name, state) VALUES (seq_category.NEXTVAL, 'Postres', 'Activo');
INSERT INTO category (category_id, name, state) VALUES (seq_category.NEXTVAL, 'Bebidas', 'Activo');

-- Insertar mesas
INSERT INTO "TABLE" (table_id, table_number, ability, state) VALUES (seq_table.NEXTVAL, 1, 4, 'Disponible');
INSERT INTO "TABLE" (table_id, table_number, ability, state) VALUES (seq_table.NEXTVAL, 2, 2, 'Disponible');
INSERT INTO "TABLE" (table_id, table_number, ability, state) VALUES (seq_table.NEXTVAL, 3, 6, 'Disponible');
INSERT INTO "TABLE" (table_id, table_number, ability, state) VALUES (seq_table.NEXTVAL, 4, 8, 'Disponible');

-- Insertar productos
INSERT INTO product (product_id, name, description, price, state, category_id)
VALUES (seq_product.NEXTVAL, 'Ensalada César', 'Ensalada fresca con aderezo César, crutones y queso parmesano', 12.50, 'Activo', 1);

INSERT INTO product (product_id, name, description, price, state, category_id)
VALUES (seq_product.NEXTVAL, 'Lomo Saltado', 'Plato tradicional con carne de res, cebolla, tomate y papas fritas', 25.00, 'Activo', 2);

INSERT INTO product (product_id, name, description, price, state, category_id)
VALUES (seq_product.NEXTVAL, 'Tiramisú', 'Postre italiano con café, queso mascarpone y cacao', 8.50, 'Activo', 3);

INSERT INTO product (product_id, name, description, price, state, category_id)
VALUES (seq_product.NEXTVAL, 'Limonada', 'Bebida refrescante de limón con hierbabuena', 5.00, 'Activo', 4);

-- Insertar un ticket de venta (requiere que Developer 1 haya creado la tabla restaurant_user)
-- Este código se ejecutará después de que Developer 1 haya creado sus tablas
INSERT INTO sales_ticket (ticket_id, ticket_date, total_payment, delivery, delivery_address, note, user_id, id_type_state, id_payment_type)
VALUES (seq_sales_ticket.NEXTVAL, SYSDATE, 50.50, 'NO', NULL, 'Cliente habitual', 2, 4, 1);

-- Insertar detalles de productos en el ticket
INSERT INTO product_detail (id_detail_product, amount, ticket_id, product_id)
VALUES (seq_product_detail.NEXTVAL, 2, 1, 1);

INSERT INTO product_detail (id_detail_product, amount, ticket_id, product_id)
VALUES (seq_product_detail.NEXTVAL, 1, 1, 2);

COMMIT;