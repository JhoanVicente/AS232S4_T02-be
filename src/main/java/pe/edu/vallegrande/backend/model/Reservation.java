package pe.edu.vallegrande.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_name")
    private String reservationName;

    @Column(name = "reservation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private RestaurantUser user;

    @ManyToOne
    @JoinColumn(name = "status_type_id")
    private OrderStatusType statusType;
}