package pe.edu.vallegrande.backend.service;

import pe.edu.vallegrande.backend.dto.SalesTicketRequestDTO;
import pe.edu.vallegrande.backend.model.SalesTicket;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SalesTicketService {
    List<SalesTicket> getAll();
    Optional<SalesTicket> findById(Long id);
    List<SalesTicket> findByDateRange(Date startDate, Date endDate);
    SalesTicket createSalesTicket(SalesTicketRequestDTO request);
    SalesTicket updateStatus(Long id, Long statusTypeId);
    SalesTicket delete(Long id);
}