package vallegrande.edu.pe.spring_polleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vallegrande.edu.pe.spring_polleria.model.DetalleReserva;

@Repository
public interface DetalleReservaRepository extends JpaRepository<DetalleReserva, Long> {
}
