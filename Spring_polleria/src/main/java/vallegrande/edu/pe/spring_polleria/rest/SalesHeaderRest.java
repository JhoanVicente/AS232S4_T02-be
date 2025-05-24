package vallegrande.edu.pe.spring_polleria.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vallegrande.edu.pe.spring_polleria.dto.SalesHeaderAndDetails;
import vallegrande.edu.pe.spring_polleria.model.SalesHeader;
import vallegrande.edu.pe.spring_polleria.model.SalesHeaderAndDetailsRequest;
import vallegrande.edu.pe.spring_polleria.service.SalesHeaderService;

import java.util.List;

@RestController
@RequestMapping("/sales-header")
public class SalesHeaderRest {

    @Autowired
    private SalesHeaderService salesHeaderService;

    // Endpoint para insertar un nuevo encabezado de venta
    @PostMapping("/create")
    public ResponseEntity<SalesHeader> createSalesHeader(@RequestBody SalesHeader salesHeader) {
        SalesHeader createdSalesHeader = salesHeaderService.createSalesHeader(salesHeader);
        return new ResponseEntity<>(createdSalesHeader, HttpStatus.CREATED);
    }

    // Endpoint para listar todos los encabezados de venta
    @GetMapping("/list")
    public ResponseEntity<List<SalesHeader>> getAllSalesHeaders() {
        List<SalesHeader> salesHeaders = salesHeaderService.getAllSalesHeaders();
        return new ResponseEntity<>(salesHeaders, HttpStatus.OK);
    }

    // Buscar un encabezado de venta por ID
    @GetMapping("/list/{id}")
    public ResponseEntity<SalesHeader> getSalesHeaderById(@PathVariable Long id) {
        return salesHeaderService.getSalesHeaderById(id)
                .map(salesHeader -> new ResponseEntity<>(salesHeader, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para insertar un nuevo encabezado de venta con sus detalles
    @PostMapping("/create-with-details")
    public ResponseEntity<SalesHeader> createSalesHeaderWithDetails(
            @RequestBody SalesHeaderAndDetailsRequest request) {
        SalesHeader createdSalesHeader = salesHeaderService.createSalesHeaderWithDetails(
                request.getSalesHeader(),
                request.getSaleDetails()
        );
        return new ResponseEntity<>(createdSalesHeader, HttpStatus.CREATED);
    }

    // Endpoint para obtener el encabezado de venta con sus detalles mediante el codeHeader
    @GetMapping("/details/{codeHeader}")
    public ResponseEntity<SalesHeaderAndDetails> getSalesHeaderWithDetails(@PathVariable Long codeHeader) {
        SalesHeaderAndDetails response = salesHeaderService.getSalesHeaderWithDetails(codeHeader);
        return ResponseEntity.ok(response);
    }
}