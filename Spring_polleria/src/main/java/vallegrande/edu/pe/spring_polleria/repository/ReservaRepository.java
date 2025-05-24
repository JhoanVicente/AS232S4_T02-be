package vallegrande.edu.pe.spring_polleria.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import vallegrande.edu.pe.spring_polleria.model.Reserva;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vallegrande.edu.pe.spring_polleria.model.Reserva;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {


    @EntityGraph(attributePaths = {"detalleReservas"})
    List<Reserva> findAll();
    List<Reserva> findByEstado(String estado);
}