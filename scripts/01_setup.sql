-- Crear el usuario para la base de datos con prefijo común
CREATE USER C##db_lospinos IDENTIFIED BY lospinos123;

-- Otorgar privilegios necesarios
GRANT CONNECT, RESOURCE, DBA TO C##db_lospinos;
GRANT CREATE SESSION TO C##db_lospinos;
GRANT UNLIMITED TABLESPACE TO C##db_lospinos;

-- Conectarse como el nuevo usuario
ALTER SESSION SET CURRENT_SCHEMA = C##db_lospinos;

-- Ahora ejecutar los scripts de creación de tablas