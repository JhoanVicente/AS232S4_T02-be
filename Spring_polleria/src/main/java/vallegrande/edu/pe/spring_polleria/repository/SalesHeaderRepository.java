package vallegrande.edu.pe.spring_polleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vallegrande.edu.pe.spring_polleria.model.SalesHeader;

import java.util.List;

public interface SalesHeaderRepository extends JpaRepository<SalesHeader, Long> {

    // Encuentra todos los encabezados de venta por el RUC
    List<SalesHeader> findByRuc(String ruc);

    // Encuentra el encabezado de venta con el mayor código
    @Query("SELECT MAX(sh.codeHeader) FROM SalesHeader sh")
    Long findMaxCodeHeader();
}
