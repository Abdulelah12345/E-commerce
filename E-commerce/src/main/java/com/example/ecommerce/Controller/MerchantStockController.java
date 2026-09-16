package com.example.ecommerce.Controller;

import com.example.ecommerce.Api.ApiResponse;
import com.example.ecommerce.Model.MerchantStock;
import com.example.ecommerce.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity<?> getProduct(){
        ArrayList<MerchantStock> merchantStocks=merchantStockService.get();

        if(merchantStocks.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No Merchant Stock to be shown"));
        }
        return ResponseEntity.status(200).body(merchantStocks);

    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.add(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Added Successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id,@RequestBody @Valid MerchantStock merchantStock,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updated=merchantStockService.update(id, merchantStock);

        if(!updated){
            return ResponseEntity.status(400).body(new ApiResponse("No Merchant Stock under this ID"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock With this ID has been Updated"));



    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        boolean deleted = merchantStockService.delete(id);

        if(!deleted){
            return ResponseEntity.status(400).body(new ApiResponse("No Merchant Stock under this ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock With this ID has been Deleted"));

    }



@GetMapping("/out")
public ResponseEntity<?> outOfStock(){

        ArrayList<MerchantStock>merchantStocks=merchantStockService.outOfStock();

        if(merchantStocks.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Nothing out of stock"));
        }

        return ResponseEntity.status(200).body(merchantStocks);


}






}
