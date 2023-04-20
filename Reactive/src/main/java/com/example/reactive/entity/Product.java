package com.example.reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private int quantity;
    private double price;
}