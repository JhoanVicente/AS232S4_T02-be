package pe.edu.vallegrande.backend.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.backend.dto.ReservationRequestDTO;
import pe.edu.vallegrande.backend.model.Reservation;
import pe.edu.vallegrande.backend.service.ReservationService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/reservation")
@Tag(name = "Reservation API", description = "API para la gestión de reservas")
public class ReservationRest {

    private final ReservationService reservationService;

    @Autowired
    public ReservationRest(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las reservas", description = "Obtiene la lista de todas las reservas")
    public ResponseEntity<List<Reservation>> getAll() {
        List<Reservation> reservations = reservationService.getAll();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reserva por ID", description = "Obtiene una reserva por su ID")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        return reservation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/date/{date}")
    @Operation(summary = "Obtener reservas por fecha", description = "Obtiene la lista de reservas por fecha")
    public ResponseEntity<List<Reservation>> findByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Reservation> reservations = reservationService.findByDate(date);
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    @Operation(summary = "Crear reserva", description = "Crea una nueva reserva")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequestDTO request) {
        Reservation savedReservation = reservationService.createReservation(request);
        return ResponseEntity.ok(savedReservation);
    }

    @PatchMapping("/{id}/status/{statusTypeId}")
    @Operation(summary = "Actualizar estado de reserva", description = "Actualiza el estado de una reserva")
    public ResponseEntity<Reservation> updateStatus(
            @PathVariable Long id, @PathVariable Long statusTypeId) {
        Reservation updatedReservation = reservationService.updateStatus(id, statusTypeId);
        return ResponseEntity.ok(updatedReservation);
    }
}