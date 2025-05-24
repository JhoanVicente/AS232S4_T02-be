package vallegrande.edu.pe.spring_polleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vallegrande.edu.pe.spring_polleria.model.SaleDetail;


import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

    // Encuentra todos los detalles de venta por el código del encabezado
    List<SaleDetail> findByHeaderSaleCode(Long headerSaleCode);

    // Encuentra todos los detalles de venta por el ID del producto
    List<SaleDetail> findByProductsIdDetail(Long productsIdDetail);

    // Consulta personalizada para obtener el total de ventas para un encabezado específico
    @Query("SELECT SUM(sd.totalPriceProducts) FROM SaleDetail sd WHERE sd.headerSaleCode = :headerSaleCode")
    Double calculateTotalByHeaderSaleCode(Long headerSaleCode);
}
