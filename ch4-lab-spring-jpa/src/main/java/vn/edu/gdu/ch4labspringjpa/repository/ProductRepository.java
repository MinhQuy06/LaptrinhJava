package vn.edu.gdu.ch4labspringjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.gdu.ch4labspringjpa.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. Derived Query Method
    List<Product> findByNameContainingIgnoreCase(String keyword);

    // 2. Derived Query Method
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // 3. JPQL
    @Query("SELECT p FROM Product p WHERE p.price > :minPrice")
    List<Product> findExpensiveProducts(@Param("minPrice") BigDecimal minPrice);

    // 4. Native SQL
    @Query(value = "SELECT * FROM products ORDER BY price DESC LIMIT 3",
            nativeQuery = true)
    List<Product> findTop3ExpensiveProducts();

    // 5. Update
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = p.price * :rate WHERE p.id = :id")
    int updateProductPrice(@Param("id") Long id,
                           @Param("rate") BigDecimal rate);
}