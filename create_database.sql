-- Script principal para crear la base de datos db_LosPinos

-- Conectarse como usuario con privilegios de administrador
-- CONNECT system/password

-- Crear el usuario para la base de datos
CREATE USER C##db_lospinos IDENTIFIED BY lospinos123;

-- Otorgar privilegios necesarios
GRANT CONNECT, RESOURCE, DBA TO C##db_lospinos;
GRANT CREATE SESSION TO C##db_lospinos;
GRANT UNLIMITED TABLESPACE TO C##db_lospinos;

-- Conectarse como el nuevo usuario
-- CONNECT C##db_lospinos/lospinos123

-- Ahora ejecutar los scripts de creación de tablas en el siguiente orden:
-- 1. developer1_tables.sql
-- 2. developer2_tables.sql
-- 3. developer1_data.sql
-- 4. developer2_data.sql
-- 5. developer1_procedures.sql
-- 6. developer2_procedures.sql

COMMIT;