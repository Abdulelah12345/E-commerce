package com.example.ecommerce.Controller;


import com.example.ecommerce.Api.ApiResponse;
import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<?> getProduct(){
        ArrayList<Category> categories=categoryService.get();

        if(categories.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No Category to be shown"));
        }
        return ResponseEntity.status(200).body(categories);

    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.add(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category Added Successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id,@RequestBody @Valid Category category,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updated=categoryService.update(id, category);

        if(!updated){
            return ResponseEntity.status(400).body(new ApiResponse("No Category under this ID"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Category With this ID has been Updated"));



    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        boolean deleted = categoryService.delete(id);

        if(!deleted){
            return ResponseEntity.status(400).body(new ApiResponse("No Category under this ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Category With this ID has been Deleted"));

    }





}



