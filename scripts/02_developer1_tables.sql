-- Script de creación de tablas para Developer 1
-- Tablas Maestras: user_type, restaurant_user
-- Tablas Transaccionales: reservation, reservation_detail

-- Eliminar tablas existentes en caso de que existan
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE reservation_detail CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE reservation CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE restaurant_user CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE user_type CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

-- Eliminar secuencias existentes
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE seq_user_type';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE seq_restaurant_user';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE seq_reservation';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE seq_reservation_detail';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

-- Creación de tablas maestras
-- Tabla user_type
CREATE TABLE user_type (
    user_type_id NUMBER PRIMARY KEY,
    name VARCHAR2(50) NOT NULL
);

-- Tabla restaurant_user
CREATE TABLE restaurant_user (
    user_id NUMBER PRIMARY KEY,
    user_name VARCHAR2(50) NOT NULL,
    password VARCHAR2(100) NOT NULL,
    names VARCHAR2(100) NOT NULL,
    surnames VARCHAR2(100) NOT NULL,
    date_of_birth DATE,
    address VARCHAR2(200),
    telephone VARCHAR2(20),
    email VARCHAR2(100),
    document_type VARCHAR2(50),
    number_type VARCHAR2(50),
    state VARCHAR2(20) NOT NULL,
    user_type_id NUMBER,
    CONSTRAINT fk_user_type FOREIGN KEY (user_type_id) REFERENCES user_type(user_type_id)
);

-- Creación de tablas transaccionales
-- Tabla reservation
CREATE TABLE reservation (
    reservation_id NUMBER PRIMARY KEY,
    reservation_name VARCHAR2(100) NOT NULL,
    reservation_date DATE NOT NULL,
    user_id NUMBER,
    id_type_state NUMBER,
    CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES restaurant_user(user_id)
);

-- Tabla reservation_detail
CREATE TABLE reservation_detail (
    id_reservacion_detalle NUMBER PRIMARY KEY,
    number_people NUMBER NOT NULL,
    reservation_method VARCHAR2(50),
    request VARCHAR2(200),
    table_id NUMBER,
    reservation_id NUMBER,
    CONSTRAINT fk_reservation_detail_res FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id)
);

-- Crear secuencias para los IDs
CREATE SEQUENCE seq_user_type START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_restaurant_user START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_reservation START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_reservation_detail START WITH 1 INCREMENT BY 1;

COMMIT;