package com.example.ecommerce.Controller;

import com.example.ecommerce.Api.ApiResponse;
import com.example.ecommerce.Model.MerchantStock;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getProduct(){
        ArrayList<User> users=userService.get();

        if(users.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No user to be shown"));
        }
        return ResponseEntity.status(200).body(users);

    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.add(user);
        return ResponseEntity.status(200).body(new ApiResponse("user Added Successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id,@RequestBody @Valid User user,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updated=userService.update(id, user);

        if(!updated){
            return ResponseEntity.status(400).body(new ApiResponse("No user under this ID"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("user With this ID has been Updated"));



    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        boolean deleted = userService.delete(id);

        if(!deleted){
            return ResponseEntity.status(400).body(new ApiResponse("No user under this ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("user With this ID has been Deleted"));

    }

    @PutMapping("/buy/{userid}/{productid}/{merchantid}/{num}")
    public ResponseEntity<?> buy(@PathVariable String userid, @PathVariable String productid, @PathVariable String merchantid, @PathVariable int num) {

        boolean updated = userService.buy(userid, productid, merchantid, num);

        if (!updated) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse("Purchase failed"));
        }

        return ResponseEntity.status(200)
                .body(new ApiResponse("Updated"));
    }


@GetMapping("/bestseller")
    public ResponseEntity<?> bestSeller(){
        ArrayList<MerchantStock>m1=userService.bestSeller();
        if(m1.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("nothing to show here"));
        }
        return ResponseEntity.status(200).body(m1);


}


    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<?> login(@PathVariable String username,@PathVariable String password){

        boolean user=userService.login(username, password);
        if(!user){
            return ResponseEntity.status(400).body(new ApiResponse("You Can't login with this Information"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("welcome "+username));


    }

    @GetMapping("/cheapest/{categoryid}")
    public ResponseEntity<?> cheapest(@PathVariable String categoryid){
        Product product=userService.cheapest(categoryid);
        if(product==null){
            return ResponseEntity.status(400).body(new ApiResponse("nothing to show"));
        }
        return ResponseEntity.status(200).body(product);


    }


    @GetMapping("/random/{amount}")
    public ResponseEntity<?>randomItem(@PathVariable double amount){
        Product product=userService.randomItemforBudget(amount);

        if(product==null){
            return ResponseEntity.status(400).body(new ApiResponse("No products found within this budget"));
        }

        return ResponseEntity.status(200).body(product);



    }
    @PutMapping("/gift/{senderid}/{receiverid}/{productid}/{merchantid}")
    public ResponseEntity<?> giftProduct(@PathVariable String senderid, @PathVariable String receiverid, @PathVariable String productid, @PathVariable String merchantid) {

        boolean gifted = userService.giftProduct(senderid, receiverid, productid, merchantid);

        if (!gifted) {
            return ResponseEntity.status(400).body(new ApiResponse("Gift failed"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Gift sent successfully"));
    }


    @GetMapping("/category/{categoryID}/{min}/{max}")
    public ResponseEntity<?> getByCategoryAndPriceRange(@PathVariable String categoryID, @PathVariable double min, @PathVariable double max) {

        ArrayList<Product> products = userService.getByCategoryAndPriceRange(categoryID, min, max);

        if (products.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No products found in this category and price range"));
        }

        return ResponseEntity.status(200).body(products);
    }


}
