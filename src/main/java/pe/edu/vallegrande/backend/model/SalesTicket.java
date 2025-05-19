package pe.edu.vallegrande.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "sales_ticket")
public class SalesTicket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_number")
    private String ticketNumber;

    @Column(name = "ticket_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ticketDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    
    @Column(name = "delivery")
    private String delivery;
    
    @Column(name = "delivery_address")
    private String deliveryAddress;
    
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private RestaurantUser user;

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;
    
    @ManyToOne
    @JoinColumn(name = "status_type_id")
    private OrderStatusType statusType;

    @Column(name = "state")
    private String state;
}