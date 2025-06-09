package vallegrande.edu.pe.spring_polleria.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sale_detail")
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_detail_seq")
    @SequenceGenerator(name = "sale_detail_seq", sequenceName = "seq_sale_detail", allocationSize = 1)
    @Column(name = "id_detail")
    private Long idDetail;

    @Column(name = "Amount_products", nullable = false)
    private Integer amountProducts;
    
    @Column(name = "Descripcion", nullable = false, length = 90)
    private String descripcion;

    @Column(name = "Unit_price_products", nullable = false)
    private Double unitPriceProducts;

    @Column(name = "Total_price_products", insertable = false, updatable = false)
    private Double totalPriceProducts;

    @Column(name = "Products_id_detail", nullable = false)
    private Long productsIdDetail;

    @Column(name = "Header_sale_code", nullable = false)
    private Long headerSaleCode;

    // Getters and Setters

    public Long getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Long idDetail) {
        this.idDetail = idDetail;
    }

    public Integer getAmountProducts() {
        return amountProducts;
    }

    public void setAmountProducts(Integer amountProducts) {
        this.amountProducts = amountProducts;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getUnitPriceProducts() {
        return unitPriceProducts;
    }

    public void setUnitPriceProducts(Double unitPriceProducts) {
        this.unitPriceProducts = unitPriceProducts;
    }

    public Double getTotalPriceProducts() {
        return totalPriceProducts;
    }

    public Long getProductsIdDetail() {
        return productsIdDetail;
    }

    public void setProductsIdDetail(Long productsIdDetail) {
        this.productsIdDetail = productsIdDetail;
    }

    public Long getHeaderSaleCode() {
        return headerSaleCode;
    }

    public void setHeaderSaleCode(Long headerSaleCode) {
        this.headerSaleCode = headerSaleCode;
    }
}

