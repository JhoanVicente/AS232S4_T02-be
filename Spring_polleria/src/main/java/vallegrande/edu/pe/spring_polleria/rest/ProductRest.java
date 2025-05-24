package vallegrande.edu.pe.spring_polleria.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vallegrande.edu.pe.spring_polleria.model.Product;
import vallegrande.edu.pe.spring_polleria.repository.ProductRepository;
import vallegrande.edu.pe.spring_polleria.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/activos")
    public List<Product> getAllActiveProducts() {
        return productRepository.findByState("A");
    }

    // Obtener productos inactivos (estado "I")
    @GetMapping("/inactivos")
    public List<Product> getAllInactiveProducts() {
        return productRepository.findByState("I");
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // Validar campos obligatorios
        if (product.getCode_products() == null || product.getName_products() == null || product.getPrice() == null) {
            return ResponseEntity.badRequest().build(); // Retorna 400 Bad Request si falla la validación
        }

        // Inicializa el estado a "A" (Activo) si no se proporciona
        if (product.getState() == null) {
            product.setState("A"); // Estado predeterminado a Activo
        }

        try {
            Product createdProduct = productRepository.save(product); // Guarda el nuevo producto
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct); // Retorna 201 Created con el producto creado
        } catch (Exception e) {
            // Manejo de errores
            System.err.println("Error creando producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Retorna 500 Internal Server Error
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setCode_products(productDetails.getCode_products());
                    product.setSeries_products(productDetails.getSeries_products());
                    product.setName_products(productDetails.getName_products());
                    product.setDescription_products(productDetails.getDescription_products());
                    product.setPrice(productDetails.getPrice());
                    product.setCategory(productDetails.getCategory());
                    product.setImage(productDetails.getImage());
                    product.setState(productDetails.getState());
                    Product updatedProduct = productRepository.save(product);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setState("I"); // Cambia el estado a "I" para marcarlo como inactivo
                    productRepository.save(product); // Guarda el producto con el estado actualizado
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Void> activateProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setState("A"); // Cambia el estado a "A" para marcarlo como activo
                    productRepository.save(product); // Guarda el producto con el estado actualizado
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/max-code")
    public ResponseEntity<String> getMaxProductCode() {
        try {
            String maxCode = productService.getMaxProductCode();
            return ResponseEntity.ok(maxCode);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching max product code: " + e.getMessage());
        }
    }

}
