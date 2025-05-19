-- Procedimientos y funciones para Developer 1

-- Procedimiento para crear un nuevo usuario
CREATE OR REPLACE PROCEDURE create_user(
    p_user_name IN VARCHAR2,
    p_password IN VARCHAR2,
    p_names IN VARCHAR2,
    p_surnames IN VARCHAR2,
    p_date_of_birth IN DATE,
    p_address IN VARCHAR2,
    p_telephone IN VARCHAR2,
    p_email IN VARCHAR2,
    p_document_type IN VARCHAR2,
    p_number_type IN VARCHAR2,
    p_user_type_id IN NUMBER,
    p_user_id OUT NUMBER
) AS
BEGIN
    SELECT seq_restaurant_user.NEXTVAL INTO p_user_id FROM DUAL;
    
    INSERT INTO restaurant_user (
        user_id, user_name, password, names, surnames, 
        date_of_birth, address, telephone, email, 
        document_type, number_type, state, user_type_id
    ) VALUES (
        p_user_id, p_user_name, p_password, p_names, p_surnames, 
        p_date_of_birth, p_address, p_telephone, p_email, 
        p_document_type, p_number_type, 'Activo', p_user_type_id
    );
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END create_user;
/

-- Procedimiento para crear una nueva reserva
CREATE OR REPLACE PROCEDURE create_reservation(
    p_reservation_name IN VARCHAR2,
    p_date IN DATE,
    p_user_id IN NUMBER,
    p_number_people IN NUMBER,
    p_reservation_method IN VARCHAR2,
    p_request IN VARCHAR2,
    p_table_id IN NUMBER,
    p_reservation_id OUT NUMBER
) AS
BEGIN
    -- Insertar la reserva principal
    SELECT seq_reservation.NEXTVAL INTO p_reservation_id FROM DUAL;
    
    INSERT INTO reservation (reservation_id, reservation_name, reservation_date, user_id, id_type_state)
    VALUES (p_reservation_id, p_reservation_name, p_date, p_user_id, 1); -- Estado 1: Pendiente
    
    -- Insertar el detalle de la reserva
    INSERT INTO reservation_detail (id_reservacion_detalle, number_people, reservation_method, request, table_id, reservation_id)
    VALUES (seq_reservation_detail.NEXTVAL, p_number_people, p_reservation_method, p_request, p_table_id, p_reservation_id);
    
    -- Actualizar el estado de la mesa a 'Reservada' (requiere acceso a la tabla de Developer 2)
    -- Este código depende de que Developer 2 haya creado la tabla table
    UPDATE "TABLE" SET state = 'Reservada' WHERE table_id = p_table_id;
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END create_reservation;
/

-- Función para verificar credenciales de usuario
CREATE OR REPLACE FUNCTION verify_user_credentials(
    p_user_name IN VARCHAR2,
    p_password IN VARCHAR2
) RETURN NUMBER AS
    v_user_id NUMBER;
BEGIN
    SELECT user_id INTO v_user_id
    FROM restaurant_user
    WHERE user_name = p_user_name
    AND password = p_password
    AND state = 'Activo';
    
    RETURN v_user_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
END verify_user_credentials;
/

-- Vista para mostrar las reservas activas con detalles
CREATE OR REPLACE VIEW v_active_reservations AS
SELECT 
    r.reservation_id,
    r.reservation_name,
    r.reservation_date,
    ru.names || ' ' || ru.surnames AS user_full_name,
    r.id_type_state,
    rd.number_people,
    rd.reservation_method,
    rd.request,
    rd.table_id
FROM 
    reservation r
JOIN 
    restaurant_user ru ON r.user_id = ru.user_id
JOIN 
    reservation_detail rd ON r.reservation_id = rd.reservation_id
WHERE 
    r.id_type_state != 5; -- No mostrar reservas canceladas
COMMIT;