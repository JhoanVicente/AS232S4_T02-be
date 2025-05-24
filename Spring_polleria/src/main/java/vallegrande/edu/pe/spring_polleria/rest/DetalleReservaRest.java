package vallegrande.edu.pe.spring_polleria.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vallegrande.edu.pe.spring_polleria.model.DetalleReserva;
import vallegrande.edu.pe.spring_polleria.service.DetalleReservaService;

import java.util.List;

@RestController
@RequestMapping("/api/detalleReserva")
public class DetalleReservaRest {

    @Autowired
    private DetalleReservaService detalleReservaService;

    @GetMapping
    public List<DetalleReserva> obtenerTodos() {
        return detalleReservaService.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<DetalleReserva> crearDetalleReserva(@RequestBody DetalleReserva detalleReserva) {
        DetalleReserva created = detalleReservaService.crearDetalleReserva(detalleReserva);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleReserva> obtenerPorId(@PathVariable Long id) {
        DetalleReserva detalleReserva = detalleReservaService.obtenerPorId(id);
        return detalleReserva != null ? ResponseEntity.ok(detalleReserva) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/cambiar-estado")
    public ResponseEntity<DetalleReserva> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        DetalleReserva detalleReserva = detalleReservaService.obtenerPorId(id);
        if (detalleReserva != null) {
            detalleReserva.setStatus(nuevoEstado); // Asegúrate de que el campo status esté presente en DetalleReserva
            DetalleReserva updated = detalleReservaService.crearDetalleReserva(detalleReserva);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<DetalleReserva> editarDetalleReserva(
            @PathVariable Long id,
            @RequestBody DetalleReserva detalleReservaActualizado) {
        DetalleReserva detalleReservaExistente = detalleReservaService.obtenerPorId(id);
        if (detalleReservaExistente != null) {
            detalleReservaExistente.setFechaReserva(detalleReservaActualizado.getFechaReserva());
            detalleReservaExistente.setHoraReserva(detalleReservaActualizado.getHoraReserva());
            detalleReservaExistente.setNumeroPersonas(detalleReservaActualizado.getNumeroPersonas());
            detalleReservaExistente.setDescripcion(detalleReservaActualizado.getDescripcion());
            detalleReservaExistente.setStatus(detalleReservaActualizado.getStatus());
            // Agregar más campos si es necesario

            DetalleReserva actualizado = detalleReservaService.crearDetalleReserva(detalleReservaExistente);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalleReserva(@PathVariable Long id) {
        detalleReservaService.eliminarDetalleReserva(id);
        return ResponseEntity.noContent().build();
    }
}
