package vallegrande.edu.pe.spring_polleria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_products;

    @Column(name = "Code_products", unique = true, nullable = false)
    private String code_products;

    @Column(name = "Series_products", nullable = false)
    private String series_products;

    @Column(name = "Name_Products", nullable = false)
    private String name_products;

    @Column(name = "Description_products", nullable = false)
    private String description_products;

    @Column(name = "Price", nullable = false)
    private Double price;

    @Column(name = "Category", nullable = false)
    private String category;

    @Column(name = "Image", nullable = false)
    private String image;

    @Column(name = "State", nullable = false)
    private String state;

    // Getters y Setters

    public Long getId_products() {
        return id_products;
    }

    public void setId_products(Long id_products) {
        this.id_products = id_products;
    }

    public String getCode_products() {
        return code_products;
    }

    public void setCode_products(String code_products) {
        this.code_products = code_products;
    }

    public String getSeries_products() {
        return series_products;
    }

    public void setSeries_products(String series_products) {
        this.series_products = series_products;
    }

    public String getName_products() {
        return name_products;
    }

    public void setName_products(String name_products) {
        this.name_products = name_products;
    }

    public String getDescription_products() {
        return description_products;
    }

    public void setDescription_products(String description_products) {
        this.description_products = description_products;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
