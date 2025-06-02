-- ========================================
-- Crear tablas y secuencias
-- ========================================

-- Tabla Products
CREATE TABLE Products (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    description VARCHAR2(255),
    price NUMBER(10,2) NOT NULL,
    stock NUMBER DEFAULT 0,
    activo NUMBER(1) DEFAULT 1
);

-- Tabla sales_header
CREATE TABLE sales_header (
    id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    date_sale DATE DEFAULT SYSDATE,
    subtotal NUMBER(10,2) DEFAULT 0,
    igv NUMBER(10,2) DEFAULT 0,
    total NUMBER(10,2) DEFAULT 0,
    activo NUMBER(1) DEFAULT 1
);

-- Tabla sale_detail
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

-- Secuencias
CREATE SEQUENCE seq_products START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_sales_header START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_sale_detail START WITH 1 INCREMENT BY 1;

-- Trigger para actualizar totales automáticamente
CREATE OR REPLACE TRIGGER update_sales_header_subtotal
AFTER INSERT OR UPDATE OR DELETE ON sale_detail
FOR EACH ROW
DECLARE
    v_subtotal NUMBER(10,2);
    v_igv NUMBER(10,2);
    v_total NUMBER(10,2);
    v_id NUMBER;
BEGIN
    IF INSERTING OR UPDATING THEN
        v_id := :NEW.sales_header_id;
    ELSIF DELETING THEN
        v_id := :OLD.sales_header_id;
    END IF;

    SELECT NVL(SUM(subtotal), 0) INTO v_subtotal
    FROM sale_detail
    WHERE sales_header_id = v_id;

    v_igv := v_subtotal * 0.18;
    v_total := v_subtotal + v_igv;

    UPDATE sales_header
    SET subtotal = v_subtotal,
        igv = v_igv,
        total = v_total
    WHERE id = v_id;
END;
/

-- Otorgar permisos a developer1 sobre las tablas de ventas
GRANT SELECT, INSERT, UPDATE, DELETE ON Products TO developer1;
GRANT SELECT, INSERT, UPDATE, DELETE ON sales_header TO developer1;
GRANT SELECT, INSERT, UPDATE, DELETE ON sale_detail TO developer1;

-- Insertar productos de ejemplo
INSERT INTO Products (id, name, description, price, stock) 
VALUES (seq_products.NEXTVAL, 'Pollo a la Brasa', 'Pollo a la brasa tradicional', 45.90, 100);

INSERT INTO Products (id, name, description, price, stock) 
VALUES (seq_products.NEXTVAL, 'Papas Fritas', 'Porción de papas fritas', 10.90, 200);

INSERT INTO Products (id, name, description, price, stock) 
VALUES (seq_products.NEXTVAL, 'Ensalada Mixta', 'Ensalada de verduras frescas', 8.90, 150);