package pe.edu.vallegrande.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesTicketRequestDTO {
    private Long userId;
    private BigDecimal totalAmount;
    private String delivery;
    private String deliveryAddress;
    private String note;
    private Long statusTypeId;
    private Long paymentTypeId;
    private List<ProductDetailDTO> products;
}