package com.example.productapp.controller;
import com.example.productapp.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/products")
public class ProductController {
    private final List<Product> products = new ArrayList<>();
    public ProductController() {
        products.add(new Product(1, "Laptop", 15000000));
        products.add(new Product(2, "Mouse", 250000));
    }
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", products);
        return "products";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }
    @PostMapping("/add")
    public String addProduct(
            @Valid @ModelAttribute Product product,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "add-product";
        }
        product.setId(products.size() + 1);
        products.add(product);
        return "redirect:/products";
    }
    @GetMapping("/{id}")
    public String productDetail(@PathVariable int id, Model model) {
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("product", product);
        return "product-detail";
    }
}