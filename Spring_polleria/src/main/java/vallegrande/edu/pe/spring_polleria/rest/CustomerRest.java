package vallegrande.edu.pe.spring_polleria.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vallegrande.edu.pe.spring_polleria.model.Customer;
import vallegrande.edu.pe.spring_polleria.service.CustomerService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")

@CrossOrigin(origins = "*")  // Permite solicitudes desde cualquier origen
public class CustomerRest {

    @Autowired
    private CustomerService customerService;

    // Listar todos los clientes activos
    @GetMapping("/activos")
    public List<Customer> listarClientesActivos() {
        return customerService.listarClientesActivos();
    }

    // Listar todos los clientes inactivos
    @GetMapping("/inactivos")
    public List<Customer> listarClientesInactivos() {
        return customerService.listarClientesInactivos();
    }

    // buscar por id
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo cliente
    @PostMapping("/crear")
    public ResponseEntity<Customer> crearCliente(@RequestBody Customer customer) {
        Customer nuevoCliente = customerService.createCustomer(customer);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }


    // Actualizar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Restablecer un cliente (activar)
    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreCustomer(@PathVariable Long id) {
        try {
            customerService.restablecerCliente(id);
            return ResponseEntity.ok().build(); // Respuesta 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo de errores
        }
    }


    // Eliminar lógicamente un cliente
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String mail = credentials.get("mail");
        String password = credentials.get("password");

        return customerService.login(mail, password)
                .map(customer -> {
                    // Create response body with customer info and role
                    Map<String, Object> response = new HashMap<>();
                    response.put("customer", customer);
                    response.put("role", customer.getRole());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    // Obtener los datos del cliente que ha iniciado sesión
    @GetMapping("/loggedInCustomer/{id}")
    public ResponseEntity<Customer> getLoggedInCustomer(@PathVariable Long id) {
    Optional<Customer> customer = customerService.getCustomerById(id);
    return customer.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}