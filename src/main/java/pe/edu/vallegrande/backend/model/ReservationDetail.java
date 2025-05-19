package pe.edu.vallegrande.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "reservation_detail")
public class ReservationDetail {

    @Id
    @Column(name = "detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_people")
    private Integer numberPeople;

    @Column(name = "reservation_method")
    private String reservationMethod;

    @Column(name = "request")
    private String request;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}