package pe.edu.vallegrande.backend.service;

import pe.edu.vallegrande.backend.model.PaymentType;
import java.util.List;
import java.util.Optional;

public interface PaymentTypeService {
    List<PaymentType> getAll();
    Optional<PaymentType> findById(Long id);
    PaymentType save(PaymentType paymentType);
    PaymentType update(PaymentType paymentType);
}