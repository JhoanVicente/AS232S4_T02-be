package vallegrande.edu.pe.spring_polleria.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vallegrande.edu.pe.spring_polleria.model.SaleDetail;
import vallegrande.edu.pe.spring_polleria.service.SaleDetailService;

import java.util.List;

@RestController
@RequestMapping("/sale-detail")
public class SaleDetailRest {

    @Autowired
    private SaleDetailService saleDetailService;

    // Endpoint para insertar un nuevo detalle de venta
    @PostMapping("/create")
    public ResponseEntity<SaleDetail> createSaleDetail(@RequestBody SaleDetail saleDetail) {
        SaleDetail createdSaleDetail = saleDetailService.createSaleDetail(saleDetail);
        return new ResponseEntity<>(createdSaleDetail, HttpStatus.CREATED);
    }

    // Endpoint para listar todos los detalles de venta
    @GetMapping("/list")
    public ResponseEntity<List<SaleDetail>> getAllSaleDetails() {
        List<SaleDetail> saleDetails = saleDetailService.getAllSaleDetails();
        return new ResponseEntity<>(saleDetails, HttpStatus.OK);
    }

    // Buscar un detalle de venta por ID
    @GetMapping("/list/{id}")
    public ResponseEntity<SaleDetail> getSaleDetailById(@PathVariable Long id) {
        return saleDetailService.getSaleDetailById(id)
                .map(saleDetail -> new ResponseEntity<>(saleDetail, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

