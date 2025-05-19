package pe.edu.vallegrande.backend.service;

import pe.edu.vallegrande.backend.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    Category update(Category category);
    Category delete(Long id);
    Category restore(Long id);
}