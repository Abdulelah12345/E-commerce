package com.example.ecommerce.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {


    @NotEmpty(message = "ID cannot be empty")
    private String id;


    @NotEmpty(message = "Name cannot be Empty")
    @Size(min = 3)
    private String name;


    @NotNull(message = "Price cannot be empty")
    @Positive(message = "Price must be Positive")
    private double price;


    @NotEmpty(message = "category ID cannot be empty")
    private String categoryID;






}
