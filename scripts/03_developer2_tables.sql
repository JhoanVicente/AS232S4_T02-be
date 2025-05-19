-- Script de creación de tablas para Developer 2
-- Tablas Maestras: category, payment_type, order_status_type
-- Tablas Transaccionales: table, product, sales_ticket, product_detail

-- Eliminar tablas existentes en caso de que existan
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE product_detail CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE sales_ticket CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE product CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE "TABLE" CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE payment_type CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE order_status_type CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE category CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

-- Creación de tablas maestras
-- Tabla payment_type
CREATE TABLE payment_type (
    id_payment_type NUMBER PRIMARY KEY,
    name VARCHAR2(50) NOT NULL
);

-- Tabla order_status_type
CREATE TABLE order_status_type (
    id_type_state NUMBER PRIMARY KEY,
    name VARCHAR2(50) NOT NULL
);

-- Tabla category
CREATE TABLE category (
    category_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    state VARCHAR2(20) NOT NULL
);

-- Creación de tablas transaccionales
-- Tabla table (mesa)
CREATE TABLE "TABLE" (
    table_id NUMBER PRIMARY KEY,
    table_number NUMBER NOT NULL,
    ability NUMBER NOT NULL,
    state VARCHAR2(20) NOT NULL
);

-- Tabla product
CREATE TABLE product (
    product_id NUMBER PRIMARY KEY,
    image BLOB,
    name VARCHAR2(100) NOT NULL,
    description VARCHAR2(500),
    price NUMBER(10,2) NOT NULL,
    state VARCHAR2(20) NOT NULL,
    category_id NUMBER,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- Tabla sales_ticket
-- Tabla sales_ticket
CREATE TABLE sales_ticket (
    ticket_id NUMBER PRIMARY KEY,
    ticket_date DATE NOT NULL,
    total_payment NUMBER(10,2) NOT NULL,
    delivery VARCHAR2(3) CHECK (delivery IN ('SI', 'NO')),
    delivery_address VARCHAR2(200),
    note VARCHAR2(500),
    user_id NUMBER,
    id_type_state NUMBER,
    id_payment_type NUMBER,
    CONSTRAINT fk_ticket_state FOREIGN KEY (id_type_state) REFERENCES order_status_type(id_type_state),
    CONSTRAINT fk_ticket_payment FOREIGN KEY (id_payment_type) REFERENCES payment_type(id_payment_type)
);

-- Tabla product_detail
CREATE TABLE product_detail (
    id_detail_product NUMBER PRIMARY KEY,
    amount NUMBER NOT NULL,
    ticket_id NUMBER,
    product_id NUMBER,
    CONSTRAINT fk_detail_ticket FOREIGN KEY (ticket_id) REFERENCES sales_ticket(ticket_id),
    CONSTRAINT fk_detail_product FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- Crear secuencias para los IDs
CREATE SEQUENCE seq_payment_type START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_order_status_type START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_category START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_table START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_product START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_sales_ticket START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_product_detail START WITH 1 INCREMENT BY 1;

-- Agregar las restricciones de clave foránea que dependen de las tablas de Developer 1
-- Estas se agregarán después de que Developer 1 haya creado sus tablas
ALTER TABLE sales_ticket ADD CONSTRAINT fk_ticket_user 
FOREIGN KEY (user_id) REFERENCES restaurant_user(user_id);

-- Agregar las restricciones de clave foránea para la tabla reservation de Developer 1
-- Estas se agregarán después de que Developer 1 haya creado sus tablas
ALTER TABLE reservation ADD CONSTRAINT fk_reservation_state 
FOREIGN KEY (id_type_state) REFERENCES order_status_type(id_type_state);

-- Agregar las restricciones de clave foránea para la tabla reservation_detail de Developer 1
-- Estas se agregarán después de que Developer 1 haya creado sus tablas
ALTER TABLE reservation_detail ADD CONSTRAINT fk_reservation_detail_table 
FOREIGN KEY (table_id) REFERENCES "TABLE"(table_id);

COMMIT;