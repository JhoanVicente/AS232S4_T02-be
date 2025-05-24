package vallegrande.edu.pe.spring_polleria.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales_header")
public class SalesHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code_header")
    private Long codeHeader;

    @Column(name = "Ruc", nullable = false, length = 11)
    private String ruc = "20606232544";

    @Column(name = "Serie_header", nullable = false, length = 4)
    private String serieHeader = "S003";

    @Column(name = "Registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate = new Date();

    @Column(name = "Subtotal_products", nullable = false)
    private Double subtotalProducts;

    @Column(name = "customer_id_header", nullable = false)
    private Long customerIdHeader;

    // Getters and Setters

    public Long getCodeHeader() {
        return codeHeader;
    }

    public void setCodeHeader(Long codeHeader) {
        this.codeHeader = codeHeader;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getSerieHeader() {
        return serieHeader;
    }

    public void setSerieHeader(String serieHeader) {
        this.serieHeader = serieHeader;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Double getSubtotalProducts() {
        return subtotalProducts;
    }

    public void setSubtotalProducts(Double subtotalProducts) {
        this.subtotalProducts = subtotalProducts;
    }

    public Long getCustomerIdHeader() {
        return customerIdHeader;
    }

    public void setCustomerIdHeader(Long customerIdHeader) {
        this.customerIdHeader = customerIdHeader;
    }
}
