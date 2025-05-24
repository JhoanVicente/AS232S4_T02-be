package vallegrande.edu.pe.spring_polleria.rest;



import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vallegrande.edu.pe.spring_polleria.model.DetalleReserva;
import vallegrande.edu.pe.spring_polleria.model.Reserva;
import vallegrande.edu.pe.spring_polleria.model.ReservaRequest;
import vallegrande.edu.pe.spring_polleria.service.ReservaService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaRest {

        private final ReservaService reservaService;

        @PostMapping
        public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaRequest reservaRequest) {
        Reserva nuevaReserva = reservaService.crearReserva(reservaRequest.getReserva(), reservaRequest.getDetalles());
        return ResponseEntity.ok(nuevaReserva);
        }






        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
            reservaService.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        }
        @GetMapping("/obtener/{id}")
        public ResponseEntity<Reserva> obtenerReservaConDetalles(@PathVariable Long id) {
            Reserva reserva = reservaService.obtenerReservaConDetalles(id);
            return ResponseEntity.ok(reserva);
        }
        @GetMapping("/obtener-todas")
        public ResponseEntity<List<Reserva>> obtenerTodasLasReservasConDetalles() {
            // Listar solo las reservas activas
            List<Reserva> reservas = reservaService.obtenerTodasLasReservasConDetalles();
            return ResponseEntity.ok(reservas);
        }

        @GetMapping("/obtener-inactivas")
        public ResponseEntity<List<Reserva>> obtenerReservasInactivas() {
            // Listar solo las reservas inactivas
            List<Reserva> reservasInactivas = reservaService.obtenerReservasInactivas();
            return ResponseEntity.ok(reservasInactivas);
        }

        @GetMapping
        public ResponseEntity<List<Reserva>> listarReservas() {
            List<Reserva> reservas = reservaService.listarReservas();
            return ResponseEntity.ok(reservas);
        }
        @PutMapping("/editar/{id}")
        public ResponseEntity<Reserva> editarReserva(@PathVariable Long id, @RequestBody Reserva reservaActualizada) {
            try {
                Reserva reserva = reservaService.editarReserva(id, reservaActualizada);
                return ResponseEntity.ok(reserva);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
    @PutMapping("/desactivar/{id}")
    public ResponseEntity<Void> desactivarReserva(@PathVariable Long id) {
        try {
            // Llamamos al servicio para desactivar la reserva
            reservaService.desactivarReserva(id);
            return ResponseEntity.noContent().build();  // Respuesta 204 sin contenido
        } catch (RuntimeException e) {
            // Si la reserva no se encuentra o ocurre algún error, respondemos con un error
            return ResponseEntity.status(404).build();  // Respuesta 404 Not Found
        }
    }


    @PutMapping("/restaurar/{id}")
    public ResponseEntity<Void> restaurarReserva(@PathVariable Long id) {
        try {
            // Llamamos al servicio para restaurar la reserva
            reservaService.restaurarReserva(id);
            return ResponseEntity.noContent().build();  // Respuesta 204 sin contenido
        } catch (RuntimeException e) {
            // Si la reserva no se encuentra o ocurre algún error, respondemos con un error
            return ResponseEntity.status(404).build();  // Respuesta 404 Not Found
        }
    }
    // Reporte general de reservas
    @GetMapping("/exportar-pdf")
    public ResponseEntity<ByteArrayResource> exportarReservaPDF() throws JRException {
        InputStream jrxmlStream = getClass().getResourceAsStream("/reportes/reservas_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

        List<Reserva> reservas = reservaService.obtenerTodasLasReservasConDetalles();
        JRDataSource dataSource = new JRBeanCollectionDataSource(reservas);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservas_report.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(resource);
    }

    // Reporte individual (ticket) de una reserva
    @GetMapping("/exportar-ticket/{idReserva}")
    public ResponseEntity<ByteArrayResource> exportarTicketPDF(@PathVariable Long idReserva) throws JRException {
        InputStream jrxmlStream = getClass().getResourceAsStream("/reportes/ticket_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

        Reserva reserva = reservaService.obtenerReservaConDetalles(idReserva);
        JRDataSource dataSource = new JRBeanCollectionDataSource(reserva.getDetalleReservas());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("reservaId", idReserva);
        parameters.put("clienteNombre", reserva.getName());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket_" + idReserva + ".pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(resource);
    }


}
