package vn.edu.gdu.ch4labspringjpa.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.gdu.ch4labspringjpa.entity.Product;
import vn.edu.gdu.ch4labspringjpa.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;
    // Constructor Injection (tiêm phụ thuộc qua Constructor)
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    // ── 1. API lấy danh sách tất cả sản phẩm ──
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    // ── 2. API lấy sản phẩm theo ID ──
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // ── 3. API thêm mới sản phẩm ──
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
    // ── 4. API cập nhật sản phẩm ──
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product productDetails) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setSku(productDetails.getSku());
            Product updatedProduct = productRepository.save(product);
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }
    // ── 5. API xóa sản phẩm ──
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    // ── 6. API đếm tổng số sản phẩm ──
    @GetMapping("/count")
    public long countProducts() {
        return productRepository.count();
    }

    //- API tìm theo tên
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam String keyword) {

        return productRepository
                .findByNameContainingIgnoreCase(keyword);
    }

    //- API tìm theo khoảng giá
    @GetMapping("/price-range")
    public List<Product> getProductsByPriceRange(

            @RequestParam BigDecimal min,

            @RequestParam BigDecimal max) {

        return productRepository
                .findByPriceBetween(min, max);
    }

    // -API JPQL
    @GetMapping("/expensive")
    public List<Product> getExpensiveProducts(

            @RequestParam BigDecimal minPrice) {

        return productRepository
                .findExpensiveProducts(minPrice);
    }

    //-API Native SQL
    @GetMapping("/top3")
    public List<Product> getTop3Expensive() {

        return productRepository
                .findTop3ExpensiveProducts();
    }

    // API Update
    @PutMapping("/{id}/increase-price")
    public ResponseEntity<String> increaseProductPrice(

            @PathVariable Long id,

            @RequestParam BigDecimal rate) {

        int updatedRows =
                productRepository.updateProductPrice(id, rate);

        if (updatedRows > 0) {

            return ResponseEntity.ok(
                    "Cập nhật giá thành công cho sản phẩm ID: " + id);

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Không tìm thấy sản phẩm ID: " + id);
    }

    // Thêm phương thức này vào class ProductController
// ── API Lấy sản phẩm có Phân trang và Sắp xếp (Chương 5) ──
    @GetMapping("/page")
    public ResponseEntity<Page<Product>> getProductsPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(productRepository.findAll(pageable));
    }
}
