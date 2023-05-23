package me.birch.five.services;

import lombok.RequiredArgsConstructor;
import me.birch.five.entities.Product;
import me.birch.five.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
