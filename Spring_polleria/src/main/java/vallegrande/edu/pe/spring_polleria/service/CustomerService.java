package vallegrande.edu.pe.spring_polleria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.edu.pe.spring_polleria.model.Customer;
import vallegrande.edu.pe.spring_polleria.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Retorna solo los clientes activos
    public List<Customer> listarClientesActivos() {
        return customerRepository.findByActivo(1); // Buscar clientes activos
    }

    // Retorna solo los clientes inactivos
    public List<Customer> listarClientesInactivos() {
        return customerRepository.findByActivo(0); // Buscar clientes inactivos
    }

    // Crear un nuevo cliente
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Actualizar un cliente
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(updatedCustomer.getName());
                    customer.setLastName(updatedCustomer.getLastName());
                    customer.setDocumentType(updatedCustomer.getDocumentType());
                    customer.setDocument(updatedCustomer.getDocument());
                    customer.setPhone(updatedCustomer.getPhone());
                    customer.setMail(updatedCustomer.getMail());
                    customer.setAddress(updatedCustomer.getAddress());
                    customer.setBirthday(updatedCustomer.getBirthday());
                    customer.setPassword(updatedCustomer.getPassword());
                    // No cambiar 'activo' aquí a menos que sea intencional
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    // Obtener un cliente por ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Eliminar lógicamente un cliente (cambiar el valor de "activo" a 0)
    public void deleteCustomer(Long id) {
        customerRepository.findById(id).ifPresent(customer -> {
            customer.setActivo(0);
            customerRepository.save(customer);
        });
    }
    public void restablecerCliente(Long id) {
        customerRepository.findById(id).ifPresent(customer -> {
            customer.setActivo(1); // Asegúrate de que 'activo' se cambie a 1
            customerRepository.save(customer); // Guarda el cliente restaurado
        });
    }


    // Iniciar sesión
    public Optional<Customer> login(String mail, String password) {
        return customerRepository.findByMail(mail)
                .filter(c -> c.getPassword().equals(password));
    }
}
