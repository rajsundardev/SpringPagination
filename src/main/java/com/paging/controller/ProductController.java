package com.paging.controller;

import com.paging.model.Product;
import com.paging.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    /*
    API to have a pagination, to pull the records per page
     */
    @GetMapping("getProductPage")
    public Iterable<Product> getProducts(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return productService.findByProductId(page, limit);
    }

    @GetMapping("getProducts")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("/saveAll")
    public List<Product> saveProduct(@RequestBody List<Product> product) {
        return productService.saveAll(product);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("delete")
    public String deleteProduct(@RequestBody Product product) {
        return productService.deleteProducts(product);
    }
}
