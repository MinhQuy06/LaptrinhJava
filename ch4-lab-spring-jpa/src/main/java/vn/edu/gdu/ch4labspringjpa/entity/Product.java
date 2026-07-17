package vn.edu.gdu.ch4labspringjpa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    // Khóa chính
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tên sản phẩm
    @Column(name = "product_name", nullable = false, length = 150)
    private String name;

    // Giá
    @Column(name = "price", nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    // Mã sản phẩm
    @Column(name = "sku", unique = true, nullable = false, length = 50)
    private String sku;

    // Thời gian tạo
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Quan hệ Nhiều - Một với Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // Constructor bắt buộc cho JPA
    protected Product() {
    }

    // Constructor cũ
    public Product(String name, BigDecimal price, String sku) {
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.createdAt = LocalDateTime.now();
    }

    // Constructor mới có Category
    public Product(String name, BigDecimal price, String sku, Category category) {
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.category = category;
        this.createdAt = LocalDateTime.now();
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sku='" + sku + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}