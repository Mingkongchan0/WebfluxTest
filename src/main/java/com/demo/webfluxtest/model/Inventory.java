package com.demo.webfluxtest.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document
@Data
public class Inventory {

    @Id private int id;
    private String artist;
    private String album;
    private float price;
    private int quantity;

}
