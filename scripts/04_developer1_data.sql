-- Insertar datos de prueba en las tablas de Developer 1

-- Insertar tipos de usuario
INSERT INTO user_type (user_type_id, name) VALUES (seq_user_type.NEXTVAL, 'Administrador');
INSERT INTO user_type (user_type_id, name) VALUES (seq_user_type.NEXTVAL, 'Mesero');
INSERT INTO user_type (user_type_id, name) VALUES (seq_user_type.NEXTVAL, 'Chef');
INSERT INTO user_type (user_type_id, name) VALUES (seq_user_type.NEXTVAL, 'Cliente');

-- Insertar usuarios
INSERT INTO restaurant_user (user_id, user_name, password, names, surnames, date_of_birth, address, telephone, email, document_type, number_type, state, user_type_id)
VALUES (seq_restaurant_user.NEXTVAL, 'admin', 'admin123', 'Juan', 'Pérez', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 'Calle Principal 123', '555-1234', 'juan@lospinos.com', 'DNI', '12345678', 'Activo', 1);

INSERT INTO restaurant_user (user_id, user_name, password, names, surnames, date_of_birth, address, telephone, email, document_type, number_type, state, user_type_id)
VALUES (seq_restaurant_user.NEXTVAL, 'mesero1', 'mesero123', 'María', 'González', TO_DATE('1990-08-20', 'YYYY-MM-DD'), 'Av. Central 456', '555-5678', 'maria@lospinos.com', 'DNI', '87654321', 'Activo', 2);

INSERT INTO restaurant_user (user_id, user_name, password, names, surnames, date_of_birth, address, telephone, email, document_type, number_type, state, user_type_id)
VALUES (seq_restaurant_user.NEXTVAL, 'chef1', 'chef123', 'Carlos', 'Rodríguez', TO_DATE('1982-03-10', 'YYYY-MM-DD'), 'Calle Secundaria 789', '555-9012', 'carlos@lospinos.com', 'DNI', '23456789', 'Activo', 3);

-- Insertar una reserva (requiere que Developer 2 haya creado la tabla order_status_type)
-- Este código se ejecutará después de que Developer 2 haya creado sus tablas
DECLARE
    v_reservation_id NUMBER;
BEGIN
    -- Insertar la reserva y capturar el ID generado
    SELECT seq_reservation.NEXTVAL INTO v_reservation_id FROM DUAL;
    
    INSERT INTO reservation (reservation_id, reservation_name, reservation_date, user_id, id_type_state)
    VALUES (v_reservation_id, 'Reserva Familia García', TO_DATE('2023-12-15 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1);
    
    -- Insertar detalle de reserva usando el mismo ID de reserva
    INSERT INTO reservation_detail (id_reservacion_detalle, number_people, reservation_method, request, table_id, reservation_id)
    VALUES (seq_reservation_detail.NEXTVAL, 4, 'Teléfono', 'Mesa cerca de la ventana', 1, v_reservation_id);
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/