package com.paging.service;

import com.paging.model.Product;
import com.paging.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    Sort sort = Sort.by(Sort.Order.asc("productName"));

    public Iterable<Product> findByProductId(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, sort);
        return productRepository.findAll(pageable);
    }

    public List<Product> getAllProducts() {
        if (productRepository.findAll() == null) {
            return null;
        }
        return productRepository.findAll();
    }

    public ResponseEntity<String> save(Product product) {
        if (product == null) {
            return null;
        }
        product.setReleaseDate(Instant.now().toEpochMilli());
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
    }

    public List<Product> saveAll(List<Product> products) {
        products.forEach(s -> s.setReleaseDate(Instant.now().toEpochMilli()));
        return productRepository.saveAll(products);
    }

    public String deleteProducts(Product product) {
        if (product == null) {
            return "null";
        }
        productRepository.deleteById(product.getId());
        return "Deleted successfully!";
    }
}
