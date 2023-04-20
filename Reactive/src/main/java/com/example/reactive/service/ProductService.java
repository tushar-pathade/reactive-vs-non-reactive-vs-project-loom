package com.example.reactive.service;


import com.example.reactive.entity.Product;
import com.example.reactive.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> getProducts() {return productRepository.findAll().delaySequence(Duration.ofMillis(1000));}

    public Mono<Product> getProduct(String id){return productRepository.findById(id);}

    public Mono<Product> saveProduct(Mono<Product> productDtoMono){return productDtoMono.flatMap(productRepository::insert);}

    public Mono<Product> updateProduct(Mono<Product> productDtoMono, String id){
        return productRepository.findById(id)
                .flatMap(p->productDtoMono)
                .doOnNext(e->e.setId(id))
                .flatMap(productRepository::save);
    }

    public Mono<Void> deleteProduct(String id){return productRepository.deleteById(id);}

    public Mono<Void> deleteAll(){
        return productRepository.deleteAll();
    }

    public void test(){
        try {
            sendGET();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Flux<Product> sendGET() throws IOException {
        URL obj = new URL("http://192.168.79.187:5555/");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.addRequestProperty("keep-alive", "timeout=60000000");
        if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
            System.out.println("Failed");
        return null;
    }
}