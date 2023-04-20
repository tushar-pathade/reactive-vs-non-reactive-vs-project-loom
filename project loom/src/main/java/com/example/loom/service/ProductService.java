package com.example.loom.service;

import com.example.loom.entity.Product;
import com.example.loom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> saveProducts(List<Product> products){
        return repository.saveAll(products);
    }

    public List<Product> getProducts(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return repository.findAll();}

    public Product getProductById(String id){return repository.findById(id).orElse(null);}

    public void deleteProduct(String id){ repository.deleteById(id);}

    public Product updateProduct(Product product){return repository.save(product);}

    public void deleteAll(){repository.deleteAll();}
}
