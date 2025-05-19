package pe.edu.vallegrande.backend.service;

import pe.edu.vallegrande.backend.dto.ReservationRequestDTO;
import pe.edu.vallegrande.backend.model.Reservation;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> getAll();
    Optional<Reservation> findById(Long id);
    List<Reservation> findByDate(Date date);
    Reservation createReservation(ReservationRequestDTO request);
    Reservation updateStatus(Long id, Long statusTypeId);
}