-- ========================================
-- Crear tablas y secuencias
-- ========================================

-- Tabla customer
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

-- Tabla Suggestion
CREATE TABLE Suggestion (
    id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    description VARCHAR2(255),
    date_suggestion DATE DEFAULT SYSDATE,
    activo NUMBER(1) DEFAULT 1,
    CONSTRAINT fk_customer_suggestion FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Tabla reserve
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

-- Tabla detail_reserve
CREATE TABLE detail_reserve (
    id NUMBER PRIMARY KEY,
    reserve_id NUMBER,
    product_id NUMBER,
    quantity NUMBER,
    CONSTRAINT fk_reserve FOREIGN KEY (reserve_id) REFERENCES reserve(id)
);

-- Secuencias
CREATE SEQUENCE seq_customer START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_suggestion START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_reserve START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_detail_reserve START WITH 1 INCREMENT BY 1;

-- Permisos a developer2
GRANT SELECT, INSERT, UPDATE, DELETE ON customer TO developer2;
GRANT SELECT, INSERT, UPDATE, DELETE ON Suggestion TO developer2;
GRANT SELECT, INSERT, UPDATE, DELETE ON reserve TO developer2;
GRANT SELECT, INSERT, UPDATE, DELETE ON detail_reserve TO developer2;

-- Datos de ejemplo
INSERT INTO customer (id, name, lastName, documentType, document, phone, mail, address) 
VALUES (seq_customer.NEXTVAL, 'Juan', 'Pérez', 'DNI', '12345678', '987654321', 'juan@example.com', 'Av. Principal 123');

INSERT INTO customer (id, name, lastName, documentType, document, phone, mail, address) 
VALUES (seq_customer.NEXTVAL, 'María', 'López', 'DNI', '87654321', '123456789', 'maria@example.com', 'Calle Secundaria 456');