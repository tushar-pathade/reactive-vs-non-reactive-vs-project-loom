package com.example.loom.controller;

import com.example.loom.entity.Product;
import com.example.loom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public List<Product> addProducts(@RequestBody List<Product> products){
        return service.saveProducts(products);
    }

    @GetMapping
    public List<Product> findAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable String id){
        return service.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable String id){
        return service.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id){
        service.deleteProduct(id);
    }

    @DeleteMapping
    public void deleteAll(){service.deleteAll();}
}