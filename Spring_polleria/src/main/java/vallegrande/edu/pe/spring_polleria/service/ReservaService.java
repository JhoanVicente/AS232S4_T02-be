package vallegrande.edu.pe.spring_polleria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vallegrande.edu.pe.spring_polleria.exception.ResourceNotFoundException;
import vallegrande.edu.pe.spring_polleria.model.Customer;
import vallegrande.edu.pe.spring_polleria.model.DetalleReserva;
import vallegrande.edu.pe.spring_polleria.model.Reserva;
import vallegrande.edu.pe.spring_polleria.repository.DetalleReservaRepository;
import vallegrande.edu.pe.spring_polleria.repository.ReservaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final DetalleReservaRepository detalleReservaRepository;

    @Transactional
    public Reserva crearReserva(Reserva reserva, List<DetalleReserva> detalles) {
        // Verificar si customer.id es null y asignar un valor por defecto (1)
        if (reserva.getCustomer() == null || reserva.getCustomer().getId() == null) {
            reserva.setCustomer(new Customer()); // Asegúrate de que el cliente no sea null
            reserva.getCustomer().setId(1L); // Asigna un valor predeterminado
        }

        // Guarda la reserva principal
        Reserva nuevaReserva = reservaRepository.save(reserva);

        // Asigna la reserva a cada detalle y guárdalos
        for (DetalleReserva detalle : detalles) {
            detalle.setReserva(nuevaReserva); // Asigna la referencia a la reserva
            detalleReservaRepository.save(detalle); // Guarda el detalle con la referencia correcta
        }

        return nuevaReserva;
    }
    public Reserva editarReserva(Long id, Reserva reservaActualizada) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        // Actualiza los datos principales
        reservaExistente.setName(reservaActualizada.getName());
        reservaExistente.setNumeroCelular(reservaActualizada.getNumeroCelular());
        reservaExistente.setFechaRegistro(reservaActualizada.getFechaRegistro());

        // Manejo de detalles: eliminar los existentes y añadir los nuevos
        reservaExistente.getDetalleReservas().clear();
        for (DetalleReserva detalle : reservaActualizada.getDetalleReservas()) {
            detalle.setReserva(reservaExistente); // Asocia el detalle con la reserva
            reservaExistente.getDetalleReservas().add(detalle);
        }

        return reservaRepository.save(reservaExistente);
    }

    @Transactional(readOnly = true)
    public List<Reserva> obtenerTodasLasReservasConDetalles() {
        // Obtener solo las reservas activas
        return reservaRepository.findByEstado("activo");
    }

    @Transactional(readOnly = true)
    public List<Reserva> obtenerReservasInactivas() {
        // Obtener solo las reservas inactivas
        return reservaRepository.findByEstado("inactivo");
    }

    @Transactional(readOnly = true)
    public Reserva obtenerReservaConDetalles(Long idReserva) {
        return reservaRepository.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }



    @Transactional
    public void eliminarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva not found"));
        reservaRepository.delete(reserva); // Eliminar físicamente la reserva
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }
    @Transactional
    public void desactivarReserva(Long id) {
        // Buscar la reserva por su ID
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con id: " + id));

        // Actualizar el estado de la reserva a "activo"
        reserva.setEstado("inactivo");

        // Guardar los cambios en la base de datos
        reservaRepository.save(reserva);
    }

    @Transactional
    public void restaurarReserva(Long id) {
        // Buscar la reserva por su ID
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con id: " + id));

        // Actualizar el estado de la reserva a "activo"
        reserva.setEstado("activo");

        // Guardar los cambios en la base de datos
        reservaRepository.save(reserva);
    }



}


