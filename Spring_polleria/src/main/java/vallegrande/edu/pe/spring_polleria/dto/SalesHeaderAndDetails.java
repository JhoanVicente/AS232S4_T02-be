package vallegrande.edu.pe.spring_polleria.dto;


import vallegrande.edu.pe.spring_polleria.model.SaleDetail;
import vallegrande.edu.pe.spring_polleria.model.SalesHeader;

import java.util.List;

public class SalesHeaderAndDetails {

    private SalesHeader salesHeader;
    private List<SaleDetail> saleDetails;

    // Getters and Setters
    public SalesHeader getSalesHeader() {
        return salesHeader;
    }

    public void setSalesHeader(SalesHeader salesHeader) {
        this.salesHeader = salesHeader;
    }

    public List<SaleDetail> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetail> saleDetails) {
        this.saleDetails = saleDetails;
    }
}