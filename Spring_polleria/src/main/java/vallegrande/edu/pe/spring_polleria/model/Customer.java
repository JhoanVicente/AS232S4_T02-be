package vallegrande.edu.pe.spring_polleria.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_customer", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document", nullable = false, unique = true)
    private String document;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "address", nullable = false)
    private String address;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role = "CLIENTE";

    @Column(name = "activo", nullable = false)
    private Integer activo ;

    // Getters and Setters (Generados por Lombok)
}
