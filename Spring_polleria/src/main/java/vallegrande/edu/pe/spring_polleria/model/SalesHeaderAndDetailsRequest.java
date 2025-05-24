package vallegrande.edu.pe.spring_polleria.model;

import java.util.List;

public class SalesHeaderAndDetailsRequest {
    private SalesHeader salesHeader;
    private List<SaleDetail> saleDetails;
    private Long customerId;

    // Getters y Setters
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}

