package pe.edu.vallegrande.backend.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.backend.dto.SalesTicketRequestDTO;
import pe.edu.vallegrande.backend.model.SalesTicket;
import pe.edu.vallegrande.backend.service.SalesTicketService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/sales")
@Tag(name = "Sales Ticket API", description = "API para la gestión de tickets de venta")
public class SalesTicketRest {

    private final SalesTicketService salesTicketService;

    @Autowired
    public SalesTicketRest(SalesTicketService salesTicketService) {
        this.salesTicketService = salesTicketService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los tickets", description = "Obtiene la lista de todos los tickets de venta")
    public ResponseEntity<List<SalesTicket>> getAll() {
        List<SalesTicket> tickets = salesTicketService.getAll();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ticket por ID", description = "Obtiene un ticket de venta por su ID")
    public ResponseEntity<SalesTicket> findById(@PathVariable Long id) {
        Optional<SalesTicket> ticket = salesTicketService.findById(id);
        return ticket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener tickets por rango de fechas", description = "Obtiene la lista de tickets de venta por rango de fechas")
    public ResponseEntity<List<SalesTicket>> findByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<SalesTicket> tickets = salesTicketService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    @Operation(summary = "Crear ticket", description = "Crea un nuevo ticket de venta")
    public ResponseEntity<SalesTicket> createSalesTicket(@RequestBody SalesTicketRequestDTO request) {
        SalesTicket savedTicket = salesTicketService.createSalesTicket(request);
        return ResponseEntity.ok(savedTicket);
    }

    @PatchMapping("/{id}/status/{statusTypeId}")
    @Operation(summary = "Actualizar estado del ticket", description = "Actualiza el estado de un ticket de venta")
    public ResponseEntity<SalesTicket> updateStatus(
            @PathVariable Long id,
            @PathVariable Long statusTypeId) {
        SalesTicket updatedTicket = salesTicketService.updateStatus(id, statusTypeId);
        return ResponseEntity.ok(updatedTicket);
    }

    @PatchMapping("/delete/{id}")
    @Operation(summary = "Eliminar ticket lógicamente", description = "Elimina lógicamente un ticket de venta")
    public ResponseEntity<SalesTicket> delete(@PathVariable Long id) {
        SalesTicket deletedTicket = salesTicketService.delete(id);
        return ResponseEntity.ok(deletedTicket);
    }
}