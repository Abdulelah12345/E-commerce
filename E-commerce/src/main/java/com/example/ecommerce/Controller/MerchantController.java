package com.example.ecommerce.Controller;


import com.example.ecommerce.Api.ApiResponse;
import com.example.ecommerce.Model.Merchant;
import com.example.ecommerce.Model.MerchantStock;
import com.example.ecommerce.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity<?> getProduct(){
        ArrayList<Merchant> merchants=merchantService.get();

        if(merchants.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No Merchant to be shown"));
        }
        return ResponseEntity.status(200).body(merchants);

    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.add(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Added Successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id,@RequestBody @Valid Merchant merchant,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updated=merchantService.update(id, merchant);

        if(!updated){
            return ResponseEntity.status(400).body(new ApiResponse("No Merchant under this ID"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Merchant With this ID has been Updated"));



    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        boolean deleted = merchantService.delete(id);

        if(!deleted){
            return ResponseEntity.status(400).body(new ApiResponse("No Merchant under this ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant With this ID has been Deleted"));

    }




    @PutMapping("/stock/{productid}/{merchantid}/{amount}")
    public ResponseEntity<?> stock(@PathVariable String productid,@PathVariable String merchantid,@PathVariable int amount){

boolean updated =merchantService.stock(productid, merchantid, amount);


if(!updated){
    return ResponseEntity.status(400).body(new ApiResponse("nothing found"));
}
        return ResponseEntity.status(200).body(new ApiResponse("Updated successfully"));



    }

}
