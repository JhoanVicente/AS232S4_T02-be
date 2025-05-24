package vallegrande.edu.pe.spring_polleria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.edu.pe.spring_polleria.repository.ProductRepository;

@Service
public class ProductService { // Renombrado a ProductService

    @Autowired
    private ProductRepository productRepository;

    public String getMaxProductCode() {
        String maxCode = productRepository.findMaxProductCode();

        if (maxCode != null && maxCode.length() > 1) {
            // Suponiendo que los códigos de productos tienen formato "P001", "P002", etc.
            String numericPart = maxCode.substring(1); // Quitar la "P"
            try {
                int newCodeNumber = Integer.parseInt(numericPart) + 1; // Incrementar el valor numérico
                return "P" + String.format("%03d", newCodeNumber); // Asegurarse de que tenga 3 dígitos (ejemplo: "P003")
            } catch (NumberFormatException e) {
                // Manejar el caso en que el formato no es numérico
                // Podrías lanzar una excepción o manejarlo de otra manera
                return "P001"; // Valor de respaldo
            }
        } else {
            return "P001"; // Si no hay productos, empieza en "P001"
        }
    }

}
