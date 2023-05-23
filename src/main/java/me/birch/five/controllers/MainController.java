package me.birch.five.controllers;

import lombok.RequiredArgsConstructor;
import me.birch.five.entities.Product;
import me.birch.five.services.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;

    @GetMapping("/main")
    public ProductsWithUser retrieveAllProducts(Authentication authentication) {
        return new ProductsWithUser(authentication.getName(), productService.getAllProducts());
    }

    public record ProductsWithUser(String username, List<Product> products) {}
}
