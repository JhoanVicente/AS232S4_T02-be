package vallegrande.edu.pe.spring_polleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vallegrande.edu.pe.spring_polleria.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMail(String mail);

    List<Customer> findByActivo(Integer activo);
}
