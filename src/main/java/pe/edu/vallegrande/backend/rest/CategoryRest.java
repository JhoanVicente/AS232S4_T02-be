package pe.edu.vallegrande.backend.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.backend.model.Category;
import pe.edu.vallegrande.backend.service.CategoryService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/category")
@Tag(name = "Category API", description = "API para la gestión de categorías")
public class CategoryRest {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las categorías", description = "Obtiene la lista de todas las categorías")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID", description = "Obtiene una categoría por su ID")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear categoría", description = "Crea una nueva categoría")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoría", description = "Actualiza una categoría existente")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        if (category.getId() == null || !category.getId().equals(id)) {
            category.setId(id);
        }
        Category updatedCategory = categoryService.update(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @PatchMapping("/delete/{id}")
    @Operation(summary = "Eliminar categoría lógicamente", description = "Elimina lógicamente una categoría")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        Category deletedCategory = categoryService.delete(id);
        return ResponseEntity.ok(deletedCategory);
    }

    @PatchMapping("/restore/{id}")
    @Operation(summary = "Restaurar categoría", description = "Restaura una categoría eliminada lógicamente")
    public ResponseEntity<Category> restore(@PathVariable Long id) {
        Category restoredCategory = categoryService.restore(id);
        return ResponseEntity.ok(restoredCategory);
    }
}