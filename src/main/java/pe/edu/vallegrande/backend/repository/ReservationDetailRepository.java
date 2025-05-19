package pe.edu.vallegrande.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.backend.model.Reservation;
import pe.edu.vallegrande.backend.model.ReservationDetail;

import java.util.List;

@Repository
public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {
    List<ReservationDetail> findByReservation(Reservation reservation);
}