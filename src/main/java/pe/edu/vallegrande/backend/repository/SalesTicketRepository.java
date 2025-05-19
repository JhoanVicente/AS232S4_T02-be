package pe.edu.vallegrande.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.backend.model.SalesTicket;

import java.util.Date;
import java.util.List;

@Repository
public interface SalesTicketRepository extends JpaRepository<SalesTicket, Long> {
    List<SalesTicket> findByState(String state);
    
    @Query("SELECT s FROM SalesTicket s WHERE s.ticketDate BETWEEN ?1 AND ?2")
    List<SalesTicket> findByDateRange(Date startDate, Date endDate);
}