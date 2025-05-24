-- ========================================
-- Eliminar usuarios si ya existen (evita errores de duplicidad)
-- ========================================
BEGIN
  EXECUTE IMMEDIATE 'DROP USER jhoan CASCADE';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -01918 THEN
      RAISE;
    END IF;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP USER angie CASCADE';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -01918 THEN
      RAISE;
    END IF;
END;
/

-- ========================================
-- Crear usuarios con contraseñas seguras
-- ========================================
CREATE USER jhoan IDENTIFIED BY "Lospinos1234";
CREATE USER angie IDENTIFIED BY "Lospinos1234";

-- ========================================
-- Otorgar privilegios y cuotas de almacenamiento a jhoan
-- ========================================
GRANT CONNECT, RESOURCE TO jhoan;
GRANT CREATE TABLE, CREATE VIEW, CREATE SEQUENCE, CREATE PROCEDURE, CREATE TRIGGER TO jhoan;
ALTER USER jhoan QUOTA UNLIMITED ON DATA;

-- ========================================
-- Otorgar privilegios y cuotas de almacenamiento a angie
-- ========================================
GRANT CONNECT, RESOURCE TO angie;
GRANT CREATE TABLE, CREATE VIEW, CREATE SEQUENCE, CREATE PROCEDURE, CREATE TRIGGER TO angie;
ALTER USER angie QUOTA UNLIMITED ON DATA;