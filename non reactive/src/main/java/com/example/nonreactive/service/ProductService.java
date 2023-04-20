package com.example.nonreactive.service;

import com.example.nonreactive.entity.Product;
import com.example.nonreactive.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public void test(){
        try {
            sendGET();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Product> sendGET() throws IOException {
        URL obj = new URL("http://192.168.79.187:5555/");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.addRequestProperty("keep-alive", "timeout=60000000");
        if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
            System.out.println("Failed");
        return null;
    }
}
