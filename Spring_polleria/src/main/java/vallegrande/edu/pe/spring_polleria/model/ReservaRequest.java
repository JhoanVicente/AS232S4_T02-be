package vallegrande.edu.pe.spring_polleria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequest {
    private Reserva reserva;
    private List<DetalleReserva> detalles;
    // Getters y Setters
}

