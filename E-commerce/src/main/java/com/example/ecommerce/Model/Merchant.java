package com.example.ecommerce.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {


    @NotEmpty(message = "ID Cannot be Empty")
    private String id;


    @NotEmpty(message = "Name Cannot be empty")
    @Size(min = 3)
    private String name;

}
