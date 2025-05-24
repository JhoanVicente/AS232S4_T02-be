package vallegrande.edu.pe.spring_polleria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "detalle_reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_reserva", nullable = false)
    @JsonBackReference
    private Reserva reserva;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva = LocalDate.now(); // Solo fecha

    @Column(name = "hora_reserva", nullable = false)
    private LocalTime horaReserva;

    @Column(name = "numero_personas", nullable = false)
    private int numeroPersonas;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "status", nullable = false)
    private String status = "in process";



    @PreUpdate
    public void checkStatus() {
        LocalDateTime fechaHoraRegistro = LocalDateTime.of(fechaReserva, horaReserva);
        if (this.status.equals("aceptado") && LocalDateTime.now().isAfter(fechaHoraRegistro.plusHours(12))) {
            this.status = "terminado";
        }
        if (this.status.equals("in process") && LocalDateTime.now().isAfter(fechaHoraRegistro.plusHours(24))) {
            this.status = "terminado";
        }
    }
}
