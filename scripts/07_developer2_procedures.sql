-- Procedimientos y funciones para Developer 2

-- Procedimiento para crear un nuevo producto
CREATE OR REPLACE PROCEDURE create_product(
    p_name IN VARCHAR2,
    p_description IN VARCHAR2,
    p_price IN NUMBER,
    p_category_id IN NUMBER,
    p_product_id OUT NUMBER
) AS
BEGIN
    SELECT seq_product.NEXTVAL INTO p_product_id FROM DUAL;
    
    INSERT INTO product (product_id, name, description, price, state, category_id)
    VALUES (p_product_id, p_name, p_description, p_price, 'Activo', p_category_id);
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END create_product;
/

-- Procedimiento para crear una nueva venta
CREATE OR REPLACE PROCEDURE create_sale(
    p_user_id IN NUMBER,
    p_delivery IN VARCHAR2,
    p_delivery_address IN VARCHAR2,
    p_note IN VARCHAR2,
    p_payment_type IN NUMBER,
    p_ticket_id OUT NUMBER
) AS
BEGIN
    -- Crear el ticket de venta
    SELECT seq_sales_ticket.NEXTVAL INTO p_ticket_id FROM DUAL;    
    INSERT INTO sales_ticket (ticket_id, ticket_date, total_payment, delivery, delivery_address, note, user_id, id_type_state, id_payment_type)
    VALUES (p_ticket_id, SYSDATE, 0, p_delivery, p_delivery_address, p_note, p_user_id, 1, p_payment_type); -- Estado 1: Pendiente
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END create_sale;
/

-- Procedimiento para agregar un producto a una venta
CREATE OR REPLACE PROCEDURE add_product_to_sale(
    p_ticket_id IN NUMBER,
    p_product_id IN NUMBER,
    p_amount IN NUMBER
) AS
    v_price NUMBER(10,2);
    v_current_total NUMBER(10,2);
BEGIN
    -- Obtener el precio del producto
    SELECT price INTO v_price FROM product WHERE product_id = p_product_id;
    
    -- Insertar el detalle del producto
    INSERT INTO product_detail (id_detail_product, amount, ticket_id, product_id)
    VALUES (seq_product_detail.NEXTVAL, p_amount, p_ticket_id, p_product_id);
    
    -- Actualizar el total del ticket
    SELECT total_payment INTO v_current_total FROM sales_ticket WHERE ticket_id = p_ticket_id;
    
    UPDATE sales_ticket 
    SET total_payment = v_current_total + (v_price * p_amount)
    WHERE ticket_id = p_ticket_id;
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END add_product_to_sale;
/

-- Función para calcular el total de ventas por día
CREATE OR REPLACE FUNCTION get_daily_sales(p_date IN DATE) 
RETURN NUMBER AS
    v_total NUMBER(10,2);
BEGIN
    SELECT NVL(SUM(total_payment), 0)
    INTO v_total
    FROM sales_ticket
    WHERE TRUNC(ticket_date) = TRUNC(p_date);
    
    RETURN v_total;
END get_daily_sales;
/

-- Vista para mostrar las ventas del día con detalles
CREATE OR REPLACE VIEW v_daily_sales_detail AS
SELECT 
    st.ticket_id,
    st.ticket_date,
    st.user_id,
    ost.name AS status,
    pt.name AS payment_method,
    st.total_payment,
    st.delivery,
    st.delivery_address,
    st.note
FROM 
    sales_ticket st
JOIN 
    order_status_type ost ON st.id_type_state = ost.id_type_state
JOIN 
    payment_type pt ON st.id_payment_type = pt.id_payment_type
WHERE 
    TRUNC(st.ticket_date) = TRUNC(SYSDATE);

COMMIT;