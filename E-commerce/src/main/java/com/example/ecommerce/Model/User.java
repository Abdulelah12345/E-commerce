package com.example.ecommerce.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "ID Cannot be empty")
    private String id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5,message = "Cannot be less than 5 long")
     private String username;

    @NotEmpty(message = "Password Cannot be empty")
    @Size(min = 6,message = "Password Cannot be less than 6")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @Email(message = "Enter a Valid Email")
    @NotEmpty(message = "Email Cannot be empty")
    private String email;


    @NotEmpty(message = "Role Cannot be empty")
    @Pattern(regexp = "Admin|Customer")
    private String role;


    @NotNull(message = "Balance cannot be empty")
    @Positive(message = "Balance Should be Positive ")
    private double balance;


}
