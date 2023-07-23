package com.productmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
    public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private Double quantity;
    private String imageUrl;

    private Timestamp createdAt;

    private Timestamp lastUpdated;


    public void beforeSave() {
        this.lastUpdated = new Timestamp(System.currentTimeMillis());

    }
}

