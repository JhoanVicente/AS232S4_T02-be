package vallegrande.edu.pe.spring_polleria.service;



import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.edu.pe.spring_polleria.exception.ResourceNotFoundException;
import vallegrande.edu.pe.spring_polleria.model.DetalleReserva;
import vallegrande.edu.pe.spring_polleria.repository.DetalleReservaRepository;

import java.util.List;

@Service
public class DetalleReservaService {

    @Autowired
    private DetalleReservaRepository detalleReservaRepository;

    public List<DetalleReserva> obtenerTodos() {
        return detalleReservaRepository.findAll();
    }

    @Transactional
    public DetalleReserva crearDetalleReserva(DetalleReserva detalleReserva) {
        return detalleReservaRepository.save(detalleReserva);
    }

    public DetalleReserva obtenerDetalleReserva(Long id) {
        return detalleReservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleReserva not found with id: " + id));
    }

    public DetalleReserva obtenerPorId(Long id) {
        return detalleReservaRepository.findById(id).orElse(null);
    }

    public void eliminarDetalleReserva(Long id) {
        detalleReservaRepository.deleteById(id);
    }

    // Agrega más métodos según sea necesario
}
