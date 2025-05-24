package vallegrande.edu.pe.spring_polleria.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.edu.pe.spring_polleria.dto.SalesHeaderAndDetails;
import vallegrande.edu.pe.spring_polleria.model.Customer;
import vallegrande.edu.pe.spring_polleria.model.SaleDetail;
import vallegrande.edu.pe.spring_polleria.model.SalesHeader;
import vallegrande.edu.pe.spring_polleria.repository.SaleDetailRepository;
import vallegrande.edu.pe.spring_polleria.repository.SalesHeaderRepository;


import java.util.List;
import java.util.Optional;

@Service
public class SalesHeaderService {
    @Autowired
    private SalesHeaderRepository salesHeaderRepository;

    @Autowired
    private SaleDetailRepository saleDetailRepository;
    @Autowired
    private CustomerService customerService;

    // Método para insertar un nuevo encabezado de venta
    public SalesHeader createSalesHeader(SalesHeader salesHeader) {
        return salesHeaderRepository.save(salesHeader);
    }

    // Método para obtener el listado de todos los encabezados de venta
    public List<SalesHeader> getAllSalesHeaders() {
        return salesHeaderRepository.findAll();
    }

    // Buscar un encabezado de venta por ID
    public Optional<SalesHeader> getSalesHeaderById(Long id) {
        return salesHeaderRepository.findById(id);
    }

    @Transactional
    public SalesHeader createSalesHeaderWithDetails(SalesHeader salesHeader, List<SaleDetail> saleDetails) {
        // Calcular el subtotal sumando el total de cada producto en los detalles de venta
        double subtotal = 0.0;
        for (SaleDetail saleDetail : saleDetails) {
            double totalProduct = saleDetail.getAmountProducts() * saleDetail.getUnitPriceProducts();
            subtotal += totalProduct;
        }

        // Asignar el subtotal calculado al encabezado de venta
        salesHeader.setSubtotalProducts(subtotal);

        // Guardar el encabezado de venta
        SalesHeader createdSalesHeader = salesHeaderRepository.save(salesHeader);

        // Asociar el encabezado de venta a cada detalle y guardar los detalles
        for (SaleDetail saleDetail : saleDetails) {
            saleDetail.setHeaderSaleCode(createdSalesHeader.getCodeHeader());
            saleDetailRepository.save(saleDetail);
        }

        return createdSalesHeader;
    }

    // Obtener el encabezado de venta y sus detalles vinculados por el codeHeader
    public SalesHeaderAndDetails getSalesHeaderWithDetails(Long codeHeader) {
        SalesHeader salesHeader = salesHeaderRepository.findById(codeHeader)
                .orElseThrow(() -> new RuntimeException("Encabezado de venta no encontrado"));

        List<SaleDetail> saleDetails = saleDetailRepository.findByHeaderSaleCode(codeHeader);

        SalesHeaderAndDetails response = new SalesHeaderAndDetails();
        response.setSalesHeader(salesHeader);
        response.setSaleDetails(saleDetails);

        return response;
    }

}
