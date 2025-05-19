package pe.edu.vallegrande.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.backend.model.OrderStatusType;

@Repository
public interface OrderStatusTypeRepository extends JpaRepository<OrderStatusType, Long> {
}