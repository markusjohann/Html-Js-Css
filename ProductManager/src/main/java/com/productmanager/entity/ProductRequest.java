package com.productmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    String name;
    Double price;
    Double quantity;
    String imageUrl;

}
