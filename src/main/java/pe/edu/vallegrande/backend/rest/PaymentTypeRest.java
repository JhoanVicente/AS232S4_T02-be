package pe.edu.vallegrande.backend.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.backend.model.PaymentType;
import pe.edu.vallegrande.backend.service.PaymentTypeService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/payment-type")
@Tag(name = "Payment Type API", description = "API para la gestión de tipos de pago")
public class PaymentTypeRest {

    private final PaymentTypeService paymentTypeService;

    @Autowired
    public PaymentTypeRest(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los tipos de pago", description = "Obtiene la lista de todos los tipos de pago")
    public ResponseEntity<List<PaymentType>> getAll() {
        List<PaymentType> paymentTypes = paymentTypeService.getAll();
        return ResponseEntity.ok(paymentTypes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tipo de pago por ID", description = "Obtiene un tipo de pago por su ID")
    public ResponseEntity<PaymentType> findById(@PathVariable Long id) {
        Optional<PaymentType> paymentType = paymentTypeService.findById(id);
        return paymentType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear tipo de pago", description = "Crea un nuevo tipo de pago")
    public ResponseEntity<PaymentType> save(@RequestBody PaymentType paymentType) {
        PaymentType savedPaymentType = paymentTypeService.save(paymentType);
        return ResponseEntity.ok(savedPaymentType);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tipo de pago", description = "Actualiza un tipo de pago existente")
    public ResponseEntity<PaymentType> update(@PathVariable Long id, @RequestBody PaymentType paymentType) {
        if (paymentType.getId() == null || !paymentType.getId().equals(id)) {
            paymentType.setId(id);
        }
        PaymentType updatedPaymentType = paymentTypeService.update(paymentType);
        return ResponseEntity.ok(updatedPaymentType);
    }
}