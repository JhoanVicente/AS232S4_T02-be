-- ========================================
-- Crear tabla de clientes (usuario Angie)
-- ========================================
CREATE TABLE customer (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    lastName VARCHAR2(100) NOT NULL,
    documentType VARCHAR2(20),
    document VARCHAR2(20),
    phone VARCHAR2(15),
    mail VARCHAR2(100) UNIQUE,
    address VARCHAR2(255),
    birthday DATE,
    password VARCHAR2(100),
    activo NUMBER(1) DEFAULT 1
);

-- ========================================
-- Crear tabla de sugerencias (usuario Angie)
-- ========================================
CREATE TABLE Suggestion (
    id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    description VARCHAR2(255),
    date_suggestion DATE DEFAULT SYSDATE,
    activo NUMBER(1) DEFAULT 1,
    CONSTRAINT fk_customer_suggestion FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- ========================================
-- Crear tabla de reservas (usuario Angie)
-- ========================================
CREATE TABLE reserve (
    id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    date_reserve DATE,
    hour_reserve VARCHAR2(10),
    number_people NUMBER,
    description VARCHAR2(255),
    status VARCHAR2(20) DEFAULT 'PENDIENTE',
    activo NUMBER(1) DEFAULT 1,
    CONSTRAINT fk_customer_reserve FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- ========================================
-- Crear tabla detalle de reserva (usuario Angie)
-- ========================================
CREATE TABLE detail_reserve (
    id NUMBER PRIMARY KEY,
    reserve_id NUMBER,
    product_id NUMBER,
    quantity NUMBER,
    CONSTRAINT fk_reserve FOREIGN KEY (reserve_id) REFERENCES reserve(id)
);

-- ========================================
-- Crear tabla de productos (usuario Jhoan)
-- ========================================
CREATE TABLE Products (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    description VARCHAR2(255),
    price NUMBER(10,2) NOT NULL,
    stock NUMBER DEFAULT 0,
    activo NUMBER(1) DEFAULT 1
);

-- ========================================
-- Crear tabla cabecera de ventas (usuario Jhoan)
-- ========================================
CREATE TABLE sales_header (
    id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    date_sale DATE DEFAULT SYSDATE,
    subtotal NUMBER(10,2) DEFAULT 0,
    igv NUMBER(10,2) DEFAULT 0,
    total NUMBER(10,2) DEFAULT 0,
    activo NUMBER(1) DEFAULT 1
);

-- ========================================
-- Crear tabla detalle de ventas con claves foráneas (usuario Jhoan)
-- ========================================
CREATE TABLE sale_detail (
    id NUMBER PRIMARY KEY,
    sales_header_id NUMBER,
    product_id NUMBER,
    quantity NUMBER,
    price NUMBER(10,2),
    subtotal NUMBER(10,2),
    CONSTRAINT fk_sales_header FOREIGN KEY (sales_header_id) REFERENCES sales_header(id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES Products(id)
);

-- ========================================
-- Secuencias para usuario Angie (customer, suggestion, reserve, detail_reserve)
-- ========================================
CREATE SEQUENCE seq_customer START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_suggestion START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_reserve START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_detail_reserve START WITH 1 INCREMENT BY 1;

-- ========================================
-- Secuencias para usuario Jhoan (products, sales_header, sale_detail)
-- ========================================
CREATE SEQUENCE seq_products START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_sales_header START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_sale_detail START WITH 1 INCREMENT BY 1;

-- ========================================
-- Crear trigger para actualizar automáticamente el subtotal, IGV y total (usuario Jhoan)
-- ========================================
CREATE OR REPLACE TRIGGER update_sales_header_subtotal
AFTER INSERT OR UPDATE OR DELETE ON sale_detail
FOR EACH ROW
DECLARE
    v_subtotal NUMBER(10,2);     -- Subtotal acumulado
    v_igv NUMBER(10,2);          -- IGV (18%)
    v_total NUMBER(10,2);        -- Total final
    v_id NUMBER;                 -- ID afectado
BEGIN
    -- Determinar el ID de la cabecera modificada
    IF INSERTING OR UPDATING THEN
        v_id := :NEW.sales_header_id;
    ELSIF DELETING THEN
        v_id := :OLD.sales_header_id;
    END IF;

    -- Calcular el nuevo subtotal de la venta
    SELECT NVL(SUM(subtotal), 0) INTO v_subtotal
    FROM sale_detail
    WHERE sales_header_id = v_id;

    -- Calcular IGV y total
    v_igv := v_subtotal * 0.18;
    v_total := v_subtotal + v_igv;

    -- Actualizar cabecera de venta
    UPDATE sales_header
    SET subtotal = v_subtotal,
        igv = v_igv,
        total = v_total
    WHERE id = v_id;
END;
/

-- ========================================
-- Otorgar permisos para que cada usuario acceda a las tablas del otro
-- ========================================

-- Conectado como Angie para otorgar permisos a Jhoan
CONNECT angie/Lospinos1234;

GRANT SELECT, INSERT, UPDATE, DELETE ON customer TO jhoan;
GRANT SELECT, INSERT, UPDATE, DELETE ON Suggestion TO jhoan;
GRANT SELECT, INSERT, UPDATE, DELETE ON reserve TO jhoan;
GRANT SELECT, INSERT, UPDATE, DELETE ON detail_reserve TO jhoan;

-- Conectado como Jhoan para otorgar permisos a Angie
CONNECT jhoan/Lospinos1234;

GRANT SELECT, INSERT, UPDATE, DELETE ON Products TO angie;
GRANT SELECT, INSERT, UPDATE, DELETE ON sales_header TO angie;
GRANT SELECT, INSERT, UPDATE, DELETE ON sale_detail TO angie;

-- ========================================
-- Insertar datos de ejemplo para testing
-- ========================================

-- Clientes (usuario Angie)
INSERT INTO customer (id, name, lastName, documentType, document, phone, mail, address) 
VALUES (seq_customer.NEXTVAL, 'Juan', 'Pérez', 'DNI', '12345678', '987654321', 'juan@example.com', 'Av. Principal 123');

INSERT INTO customer (id, name, lastName, documentType, document, phone, mail, address) 
VALUES (seq_customer.NEXTVAL, 'María', 'López', 'DNI', '87654321', '123456789', 'maria@example.com', 'Calle Secundaria 456');

-- Productos (usuario Jhoan)
INSERT INTO Products (id, name, description, price, stock) 
VALUES (seq_products.NEXTVAL, 'Pollo a la Brasa', 'Pollo a la brasa tradicional', 45.90, 100);

INSERT INTO Products (id, name, description, price, stock) 
VALUES (seq_products.NEXTVAL, 'Papas Fritas', 'Porción de papas fritas', 10.90, 200);

INSERT INTO Products (id, name, description, price, stock) 
VALUES (seq_products.NEXTVAL, 'Ensalada Mixta', 'Ensalada de verduras frescas', 8.90, 150);