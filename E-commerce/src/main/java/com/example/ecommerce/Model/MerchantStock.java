package com.example.ecommerce.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {


    @NotEmpty(message = "ID Cannot be Empty")
    private String id;


    @NotEmpty(message = "product ID cannot be empty")
    private String productid;


    @NotEmpty(message = "Merchant ID Cannot be empty")
    private String merchantid;


    @NotNull(message = "Stock Cannot be empty")
    @Min(value = 10)
    private int stock;


}
