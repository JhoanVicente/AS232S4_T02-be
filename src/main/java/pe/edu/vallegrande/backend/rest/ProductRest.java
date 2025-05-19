package pe.edu.vallegrande.backend.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.backend.model.Product;
import pe.edu.vallegrande.backend.service.ProductService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/product")
@Tag(name = "Product API", description = "API para la gestión de productos")
public class ProductRest {

    private final ProductService productService;

    @Autowired
    public ProductRest(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Obtiene la lista de todos los productos")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Obtiene un producto por su ID")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Obtener productos por categoría", description = "Obtiene la lista de productos por categoría")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.findByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        if (product.getId() == null || !product.getId().equals(id)) {
            product.setId(id);
        }
        Product updatedProduct = productService.update(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping("/delete/{id}")
    @Operation(summary = "Eliminar producto lógicamente", description = "Elimina lógicamente un producto")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        Product deletedProduct = productService.delete(id);
        return ResponseEntity.ok(deletedProduct);
    }

    @PatchMapping("/restore/{id}")
    @Operation(summary = "Restaurar producto", description = "Restaura un producto eliminado lógicamente")
    public ResponseEntity<Product> restore(@PathVariable Long id) {
        Product restoredProduct = productService.restore(id);
        return ResponseEntity.ok(restoredProduct);
    }
}