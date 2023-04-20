package com.example.reactive.controller;

import com.example.reactive.entity.Product;
import com.example.reactive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<Product> products(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }

    @PostMapping
    public Mono<Product> saveProduct(@RequestBody Mono<Product> products){
        return productService.saveProduct(products);
    }

    @PutMapping("/update/{id}")
    public Mono<Product> updateProduct(@RequestBody Mono<Product> product, @PathVariable String id){
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){ return productService.deleteProduct(id);}

    @DeleteMapping
    public Mono<Void> deleteAll(){return productService.deleteAll();}

    @GetMapping("/test")
    public void test(){productService.test();}
}