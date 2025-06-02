-- ========================================
-- Eliminar usuarios si ya existen
-- ========================================
BEGIN
  EXECUTE IMMEDIATE 'DROP USER developer1 CASCADE';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -01918 THEN
      RAISE;
    END IF;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP USER developer2 CASCADE';
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
CREATE USER developer1 IDENTIFIED BY "Lospinos1234";
CREATE USER developer2 IDENTIFIED BY "Lospinos1234";

-- ========================================
-- Otorgar privilegios a developer1
-- ========================================
GRANT CONNECT, RESOURCE TO developer1;
GRANT CREATE TABLE, CREATE VIEW, CREATE SEQUENCE, CREATE PROCEDURE, CREATE TRIGGER TO developer1;
ALTER USER developer1 QUOTA UNLIMITED ON DATA;

-- ========================================
-- Otorgar privilegios a developer2
-- ========================================
GRANT CONNECT, RESOURCE TO developer2;
GRANT CREATE TABLE, CREATE VIEW, CREATE SEQUENCE, CREATE PROCEDURE, CREATE TRIGGER TO developer2;
ALTER USER developer2 QUOTA UNLIMITED ON DATA;