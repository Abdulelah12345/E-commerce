package com.example.ecommerce.Controller;


import com.example.ecommerce.Api.ApiResponse;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<?> getProduct(){
        ArrayList<Product> products=productService.get();

        if(products.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No product to be shown"));
        }
        return ResponseEntity.status(200).body(products);

    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.add(product);
        return ResponseEntity.status(200).body(new ApiResponse("Product Added Successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id,@RequestBody @Valid Product product,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updated=productService.update(id, product);

        if(!updated){
            return ResponseEntity.status(400).body(new ApiResponse("No product under this ID"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Product With this ID has been Updated"));



    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        boolean deleted = productService.delete(id);

        if(!deleted){
            return ResponseEntity.status(400).body(new ApiResponse("No product under this ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Product With this ID has been Deleted"));

    }

    @PutMapping("/discount/{id}/{discount}")
    public ResponseEntity<?> discount(@PathVariable String id,@PathVariable double discount){
       boolean updated= productService.discount(id, discount);
       if(!updated){
           return ResponseEntity.status(400).body(new ApiResponse("No product with this ID"));
       }
        return ResponseEntity.status(200).body(new ApiResponse("The discount applied"));

    }

    @PutMapping("/reprice/{id}/{price}")
    public ResponseEntity<?> reprice(@PathVariable String id,@PathVariable double price){
      boolean x=productService.reprice(id,price);

        if(!x){
            return ResponseEntity.status(400).body(new ApiResponse("No product with this ID"));

        }
        return ResponseEntity.status(200).body(new ApiResponse("You have changed the price successfully"));


    }




}
