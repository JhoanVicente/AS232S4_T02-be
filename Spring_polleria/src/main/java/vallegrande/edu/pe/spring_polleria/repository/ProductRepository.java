package vallegrande.edu.pe.spring_polleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vallegrande.edu.pe.spring_polleria.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Encuentra todos los productos con el estado "A" (activo)
    List<Product> findByState(String state);

    @Query("SELECT MAX(p.code_products) FROM Product p")
    String findMaxProductCode();

}
