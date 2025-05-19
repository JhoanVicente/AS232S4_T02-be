package pe.edu.vallegrande.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.vallegrande.backend.dto.ProductDetailDTO;
import pe.edu.vallegrande.backend.dto.SalesTicketRequestDTO;
import pe.edu.vallegrande.backend.model.*;
import pe.edu.vallegrande.backend.repository.*;
import pe.edu.vallegrande.backend.service.SalesTicketService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SalesTicketServiceImpl implements SalesTicketService {

    private final SalesTicketRepository salesTicketRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductRepository productRepository;
    private final RestaurantUserRepository userRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final OrderStatusTypeRepository statusTypeRepository;

    @Autowired
    public SalesTicketServiceImpl(
            SalesTicketRepository salesTicketRepository,
            ProductDetailRepository productDetailRepository,
            ProductRepository productRepository,
            RestaurantUserRepository userRepository,
            PaymentTypeRepository paymentTypeRepository,
            OrderStatusTypeRepository statusTypeRepository) {
        this.salesTicketRepository = salesTicketRepository;
        this.productDetailRepository = productDetailRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.paymentTypeRepository = paymentTypeRepository;
        this.statusTypeRepository = statusTypeRepository;
    }

    @Override
    public List<SalesTicket> getAll() {
        return salesTicketRepository.findAll();
    }

    @Override
    public Optional<SalesTicket> findById(Long id) {
        return salesTicketRepository.findById(id);
    }

    @Override
    public List<SalesTicket> findByDateRange(Date startDate, Date endDate) {
        return salesTicketRepository.findByDateRange(startDate, endDate);
    }

    @Override
    @Transactional
    public SalesTicket createSalesTicket(SalesTicketRequestDTO request) {
        // Crear el ticket
        SalesTicket ticket = new SalesTicket();
        ticket.setTicketNumber(generateTicketNumber());
        ticket.setTicketDate(new Date());
        ticket.setTotalAmount(request.getTotalAmount());
        ticket.setDelivery(request.getDelivery());
        ticket.setDeliveryAddress(request.getDeliveryAddress());
        ticket.setNote(request.getNote());
        ticket.setState("A");
        
        // Asignar usuario
        RestaurantUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        ticket.setUser(user);
        
        // Asignar tipo de pago
        PaymentType paymentType = paymentTypeRepository.findById(request.getPaymentTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de pago no encontrado"));
        ticket.setPaymentType(paymentType);
        
        // Asignar estado
        if (request.getStatusTypeId() != null) {
            OrderStatusType statusType = statusTypeRepository.findById(request.getStatusTypeId())
                    .orElseThrow(() -> new RuntimeException("Tipo de estado no encontrado"));
            ticket.setStatusType(statusType);
        }
        
        // Guardar el ticket
        SalesTicket savedTicket = salesTicketRepository.save(ticket);
        
        // Procesar los detalles del producto
        if (request.getProducts() != null && !request.getProducts().isEmpty()) {
            List<ProductDetail> details = request.getProducts().stream()
                    .map(detailDTO -> createProductDetail(detailDTO, savedTicket))
                    .collect(Collectors.toList());
            
            productDetailRepository.saveAll(details);
        }
        
        return savedTicket;
    }

    private ProductDetail createProductDetail(ProductDetailDTO detailDTO, SalesTicket ticket) {
        Product product = productRepository.findById(detailDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        ProductDetail detail = new ProductDetail();
        detail.setTicket(ticket);
        detail.setProduct(product);
        detail.setQuantity(detailDTO.getQuantity());
        detail.setUnitPrice(detailDTO.getUnitPrice());
        detail.setSubtotal(detailDTO.getSubtotal());
        
        return detail;
    }

    @Override
    @Transactional
    public SalesTicket updateStatus(Long id, Long statusTypeId) {
        SalesTicket ticket = salesTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        
        OrderStatusType statusType = statusTypeRepository.findById(statusTypeId)
                .orElseThrow(() -> new RuntimeException("Tipo de estado no encontrado"));
        
        ticket.setStatusType(statusType);
        return salesTicketRepository.save(ticket);
    }

    @Override
    @Transactional
    public SalesTicket delete(Long id) {
        SalesTicket ticket = salesTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        
        ticket.setState("I");
        return salesTicketRepository.save(ticket);
    }
    
    private String generateTicketNumber() {
        return "TK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}