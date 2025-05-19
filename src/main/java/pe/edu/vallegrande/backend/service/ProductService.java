package pe.edu.vallegrande.backend.service;

import pe.edu.vallegrande.backend.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();
    Optional<Product> findById(Long id);
    List<Product> findByCategory(Long categoryId);
    Product save(Product product);
    Product update(Product product);
    Product delete(Long id);
    Product restore(Long id);
}