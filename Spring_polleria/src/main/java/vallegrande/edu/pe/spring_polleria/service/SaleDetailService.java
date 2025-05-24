package vallegrande.edu.pe.spring_polleria.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.edu.pe.spring_polleria.model.SaleDetail;
import vallegrande.edu.pe.spring_polleria.repository.SaleDetailRepository;


import java.util.List;
import java.util.Optional;

@Service
public class SaleDetailService {
    @Autowired
    private SaleDetailRepository saleDetailRepository;

    // Método para insertar un nuevo detalle de venta
    public SaleDetail createSaleDetail(SaleDetail saleDetail) {
        return saleDetailRepository.save(saleDetail);
    }

    // Método para obtener el listado de todos los detalles de venta
    public List<SaleDetail> getAllSaleDetails() {
        return saleDetailRepository.findAll();
    }

    // Buscar un detalle de venta por ID
    public Optional<SaleDetail> getSaleDetailById(Long id) {
        return saleDetailRepository.findById(id);
    }

}

