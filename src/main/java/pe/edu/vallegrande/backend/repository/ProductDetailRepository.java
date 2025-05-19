package pe.edu.vallegrande.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.backend.model.ProductDetail;
import pe.edu.vallegrande.backend.model.SalesTicket;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    List<ProductDetail> findByTicket(SalesTicket ticket);
}