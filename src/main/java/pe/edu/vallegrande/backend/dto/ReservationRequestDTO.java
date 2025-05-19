package pe.edu.vallegrande.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {
    private String reservationName;
    private Date reservationDate;
    private Long userId;
    private Long statusTypeId;
    private Long tableId;
    private Integer numberPeople;
    private String reservationMethod;
    private String request;
}