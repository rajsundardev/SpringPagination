package com.paging.controller;

import com.paging.model.Product;
import com.paging.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {

        // Either use @ExtendWith or Mockito.openMocks(this)
       //MockitoAnnotations.openMocks(this);
        productController = new ProductController();
        productController.productService = productService;
    }

    @Test
    public void getProductsTest_valid() {
        //Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setProductName("Laptop1");
        product1.setPrice(1000);

        Product product2 = new Product();
        product2.setId(1L);
        product2.setProductName("Laptop2");
        product2.setPrice(1000);

        List<Product> listOfProducts = new ArrayList<>();
        listOfProducts.add(product1);
        listOfProducts.add(product2);

        //Act
        when(productService.getAllProducts()).thenReturn(listOfProducts);

        List<Product> foundProducts = productService.getAllProducts();

        Assertions.assertEquals(listOfProducts, foundProducts);

        //verify
        verify(productService, times(1)).getAllProducts();

    }

    @Test
    public void getProducts_Invalid_Null() {
        List<Product> products = new ArrayList<>();

        when(productService.getAllProducts()).thenReturn(null);
        List<Product> foundProducts = productService.getAllProducts();

        Assertions.assertNull((Object) null, (Supplier<String>) foundProducts);
        //verifyNoInteractions(productService);
    }
}
