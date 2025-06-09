Análisis de Roles y Responsabilidades: Developer 1 y Developer 2
Se realizó una búsqueda exhaustiva en el código base para identificar los métodos y endpoints correspondientes a cada desarrollador:

Developer 1:

Se analizaron los archivos ReservaRest.java y DetalleReservaRest.java para identificar sus endpoints.

Se revisó el archivo developer1.sql para verificar sus permisos y roles definidos en base de datos.

Developer 2:

Se investigaron los endpoints en los archivos SalesHeaderRest.java y SaleDetailRest.java.

Se revisó el archivo developer2.sql para comprender sus funciones y permisos asignados.

Adicionalmente, se visualizaron directamente los archivos SQL y los controladores REST para tener una comprensión completa de las funciones asignadas y los endpoints disponibles en el sistema.

      
# Métodos API para Todas las Tablas (Developer 1 y Developer 2)

A continuación, te proporciono todos los métodos API disponibles para probar en Postman, organizados por desarrollador y entidad. La URL base para todos los endpoints es: `https://psychic-fiesta-g4q7p5g7gg7r3ppq-8080.app.github.dev/`

## Developer 1

El Developer 1 es responsable de las entidades relacionadas con clientes, sugerencias, reservas y detalles de reservas.

### Customer API

1. **Crear Cliente**
   - Método: `POST`
   - Endpoint: `/api/customers`
   - Body:
   ```json
   {
     "name": "Juan",
     "lastName": "Pérez",
     "documentType": "DNI",
     "document": "12345678",
     "phone": "987654321",
     "mail": "juan.perez@example.com",
     "address": "Av. Principal 123",
     "password": "secreto123",
     "role": "CLIENTE",
     "activo": 1
   }
   ```

2. **Listar Todos los Clientes**
   - Método: `GET`
   - Endpoint: `/api/customers`

3. **Obtener Cliente por ID**
   - Método: `GET`
   - Endpoint: `/api/customers/{id}`
   - Ejemplo: `/api/customers/1`

4. **Actualizar Cliente**
   - Método: `PUT`
   - Endpoint: `/api/customers/{id}`
   - Ejemplo: `/api/customers/1`
   - Body:
   ```json
   {
     "name": "Juan",
     "lastName": "Pérez Actualizado",
     "documentType": "DNI",
     "document": "12345678",
     "phone": "987654321",
     "mail": "juan.perez@example.com",
     "address": "Av. Siempre Viva 789",
     "password": "secreto123",
     "role": "CLIENTE",
     "activo": 1
   }
   ```

5. **Restaurar Cliente**
   - Método: `PUT`
   - Endpoint: `/api/customers/{id}/restore`
   - Ejemplo: `/api/customers/2/restore`

6. **Login**
   - Método: `POST`
   - Endpoint: `/api/customers/login`
   - Body:
   ```json
   {
     "mail": "juan.perez@example.com",
     "password": "secreto123"
   }
   ```

### Suggestion API

1. **Crear Sugerencia**
   - Método: `POST`
   - Endpoint: `/api/suggestions`
   - Body:
   ```json
   {
     "customer": {
       "id": 1
     },
     "description": "Mejorar el servicio de atención al cliente"
   }
   ```

2. **Listar Todas las Sugerencias**
   - Método: `GET`
   - Endpoint: `/api/suggestions`

3. **Obtener Sugerencia por ID**
   - Método: `GET`
   - Endpoint: `/api/suggestions/{id}`
   - Ejemplo: `/api/suggestions/1`

4. **Actualizar Sugerencia**
   - Método: `PUT`
   - Endpoint: `/api/suggestions/{id}`
   - Ejemplo: `/api/suggestions/1`
   - Body:
   ```json
   {
     "description": "Mejorar el servicio de atención al cliente y la calidad de los productos"
   }
   ```

5. **Eliminar Sugerencia**
   - Método: `DELETE`
   - Endpoint: `/api/suggestions/{id}`
   - Ejemplo: `/api/suggestions/1`

### Reserva API

1. **Crear Reserva**
   - Método: `POST`
   - Endpoint: `/api/reservas`
   - Body:
   ```json
   {
     "reserva": {
       "name": "Juan Pérez",
       "phone": "987654321",
       "estado": "activo"
     },
     "detalles": [
       {
         "fechaReserva": "2023-11-20",
         "horaReserva": "19:00:00",
         "numeroPersonas": 4,
         "descripcion": "Cena familiar",
         "status": "in process"
       }
     ]
   }
   ```

2. **Listar Todas las Reservas**
   - Método: `GET`
   - Endpoint: `/api/reservas`

3. **Obtener Reserva por ID**
   - Método: `GET`
   - Endpoint: `/api/reservas/obtener/{id}`
   - Ejemplo: `/api/reservas/obtener/1`

4. **Listar Todas las Reservas con Detalles**
   - Método: `GET`
   - Endpoint: `/api/reservas/obtener-todas`

5. **Listar Reservas Inactivas**
   - Método: `GET`
   - Endpoint: `/api/reservas/obtener-inactivas`

6. **Editar Reserva**
   - Método: `PUT`
   - Endpoint: `/api/reservas/editar/{id}`
   - Ejemplo: `/api/reservas/editar/1`
   - Body:
   ```json
   {
     "name": "Juan Pérez Actualizado",
     "phone": "987654321",
     "estado": "activo"
   }
   ```

7. **Desactivar Reserva**
   - Método: `PUT`
   - Endpoint: `/api/reservas/desactivar/{id}`
   - Ejemplo: `/api/reservas/desactivar/1`

8. **Restaurar Reserva**
   - Método: `PUT`
   - Endpoint: `/api/reservas/restaurar/{id}`
   - Ejemplo: `/api/reservas/restaurar/1`

9. **Eliminar Reserva**
   - Método: `DELETE`
   - Endpoint: `/api/reservas/{id}`
   - Ejemplo: `/api/reservas/1`

10. **Exportar Reporte PDF de Todas las Reservas**
    - Método: `GET`
    - Endpoint: `/api/reservas/exportar-pdf`

11. **Exportar Ticket PDF de una Reserva**
    - Método: `GET`
    - Endpoint: `/api/reservas/exportar-ticket/{id}`
    - Ejemplo: `/api/reservas/exportar-ticket/1`

### DetalleReserva API

1. **Listar Todos los Detalles**
   - Método: `GET`
   - Endpoint: `/api/detalleReserva`

2. **Crear Detalle de Reserva**
   - Método: `POST`
   - Endpoint: `/api/detalleReserva`
   - Body:
   ```json
   {
     "reserva": {
       "id": 1
     },
     "fechaReserva": "2023-11-22",
     "horaReserva": "20:30:00",
     "numeroPersonas": 2,
     "descripcion": "Cena romántica",
     "status": "in process"
   }
   ```

3. **Obtener Detalle por ID**
   - Método: `GET`
   - Endpoint: `/api/detalleReserva/{id}`
   - Ejemplo: `/api/detalleReserva/1`

4. **Cambiar Estado de Detalle**
   - Método: `PUT`
   - Endpoint: `/api/detalleReserva/{id}/cambiar-estado?nuevoEstado={estado}`
   - Ejemplo: `/api/detalleReserva/1/cambiar-estado?nuevoEstado=aceptado`

5. **Editar Detalle de Reserva**
   - Método: `PUT`
   - Endpoint: `/api/detalleReserva/editar/{id}`
   - Ejemplo: `/api/detalleReserva/editar/1`
   - Body:
   ```json
   {
     "fechaReserva": "2023-11-25",
     "horaReserva": "21:00:00",
     "numeroPersonas": 3,
     "descripcion": "Cena de amigos actualizada",
     "status": "aceptado"
   }
   ```

6. **Eliminar Detalle de Reserva**
   - Método: `DELETE`
   - Endpoint: `/api/detalleReserva/{id}`
   - Ejemplo: `/api/detalleReserva/1`

## Developer 2

El Developer 2 es responsable de las entidades relacionadas con productos, encabezados de venta y detalles de venta.

### Products API

1. **Crear Producto**
   - Método: `POST`
   - Endpoint: `/api/products`
   - Body:
   ```json
   {
     "name": "Pollo a la Brasa",
     "description": "Pollo a la brasa tradicional",
     "price": 45.90,
     "stock": 100
   }
   ```

2. **Listar Todos los Productos**
   - Método: `GET`
   - Endpoint: `/api/products`

3. **Obtener Producto por ID**
   - Método: `GET`
   - Endpoint: `/api/products/{id}`
   - Ejemplo: `/api/products/1`

4. **Actualizar Producto**
   - Método: `PUT`
   - Endpoint: `/api/products/{id}`
   - Ejemplo: `/api/products/1`
   - Body:
   ```json
   {
     "name": "Pollo a la Brasa Premium",
     "description": "Pollo a la brasa tradicional con especias premium",
     "price": 49.90,
     "stock": 80
   }
   ```

5. **Eliminar Producto**
   - Método: `DELETE`
   - Endpoint: `/api/products/{id}`
   - Ejemplo: `/api/products/1`

### SalesHeader API

1. **Crear Encabezado de Venta**
   - Método: `POST`
   - Endpoint: `/sales-header/create`
   - Body:
   ```json
   {
     "ruc": "20606232544",
     "serieHeader": "S003",
     "registrationDate": "2023-11-15",
     "subtotalProducts": 150.00,
     "customerIdHeader": 1
   }
   ```

2. **Listar Todos los Encabezados**
   - Método: `GET`
   - Endpoint: `/sales-header/list`

3. **Obtener Encabezado por ID**
   - Método: `GET`
   - Endpoint: `/sales-header/list/{id}`
   - Ejemplo: `/sales-header/list/1`

4. **Crear Encabezado con Detalles**
   - Método: `POST`
   - Endpoint: `/sales-header/create-with-details`
   - Body:
   ```json
   {
     "salesHeader": {
       "ruc": "20606232544",
       "serieHeader": "S003",
       "registrationDate": "2023-11-15",
       "subtotalProducts": 0,
       "customerIdHeader": 1
     },
     "saleDetails": [
       {
         "amountProducts": 2,
         "descripcion": "Pollo a la brasa",
         "unitPriceProducts": 25.00,
         "productsIdDetail": 1
       },
       {
         "amountProducts": 1,
         "descripcion": "Ensalada mixta",
         "unitPriceProducts": 15.00,
         "productsIdDetail": 2
       }
     ]
   }
   ```

5. **Obtener Encabezado con Detalles**
   - Método: `GET`
   - Endpoint: `/sales-header/details/{codeHeader}`
   - Ejemplo: `/sales-header/details/1`

### SaleDetail API

1. **Crear Detalle de Venta**
   - Método: `POST`
   - Endpoint: `/sale-detail/create`
   - Body:
   ```json
   {
     "amountProducts": 3,
     "descripcion": "Papas fritas",
     "unitPriceProducts": 10.00,
     "productsIdDetail": 3,
     "headerSaleCode": 1
   }
   ```

2. **Listar Todos los Detalles de Venta**
   - Método: `GET`
   - Endpoint: `/sale-detail/list`

3. **Obtener Detalle de Venta por ID**
   - Método: `GET`
   - Endpoint: `/sale-detail/list/{id}`
   - Ejemplo: `/sale-detail/list/1`

## Notas Importantes

1. Recuerda que la URL base para todos los endpoints es: `https://psychic-fiesta-g4q7p5g7gg7r3ppq-8080.app.github.dev/`

2. Para las entidades que tienen relaciones, asegúrate de crear primero las entidades padre antes de crear las entidades hijas (por ejemplo, crear un SalesHeader antes de crear un SaleDetail asociado).

3. Los IDs se generan automáticamente mediante secuencias en la base de datos.

4. Algunos campos tienen valores por defecto, como el estado de las reservas (`PENDIENTE`) y el estado activo (`1`).

5. El sistema calcula automáticamente los subtotales, IGV y totales para las ventas mediante un trigger en la base de datos.

6. Para probar correctamente, sigue este orden:
   - Primero crea clientes
   - Luego crea productos
   - Después crea reservas o ventas que dependan de los clientes y productos creados