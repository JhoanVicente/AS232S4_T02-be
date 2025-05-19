package pe.edu.vallegrande.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.backend.model.PaymentType;
import pe.edu.vallegrande.backend.repository.PaymentTypeRepository;
import pe.edu.vallegrande.backend.service.PaymentTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;

    @Autowired
    public PaymentTypeServiceImpl(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @Override
    public List<PaymentType> getAll() {
        return paymentTypeRepository.findAll();
    }

    @Override
    public Optional<PaymentType> findById(Long id) {
        return paymentTypeRepository.findById(id);
    }

    @Override
    public PaymentType save(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType);
    }

    @Override
    public PaymentType update(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType);
    }
}