package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ProductService {

    ArrayList<Product> products=new ArrayList<>();


    public ArrayList<Product> get(){

        return products;
    }

    public void add(Product product){

        products.add(product);

    }

    public boolean update(String id , Product product){
        for (int i=0;i<products.size();i++){
            if(products.get(i).getId().equals(id)){
                products.set(i,product);
                return true;
            }
        }

        return false;
    }


    public boolean delete(String id){
        for (int i=0;i<products.size();i++){
            if(products.get(i).getId().equals(id)){
             products.remove(i);
                return true;
            }
        }

        return false;
    }

    public boolean discount(String id, double discount){

        for (int i = 0; i < products.size(); i++) {

            if (products.get(i).getId().equals(id)) {

                double discount1 = products.get(i).getPrice() * (discount / 100);

                products.get(i).setPrice(products.get(i).getPrice() - discount1);

                return true;
            }
        }

        return false;
    }


    public boolean reprice(String id,double price){


        for (int i=0;i< products.size();i++){
            if(products.get(i).getId().equals(id)){
                products.get(i).setPrice(price);
                return true;

            }
        }
        return false;
    }



    public ArrayList<Product> getByCategoryAndPriceRange(String categoryid,double min,double max){
        ArrayList<Product> products1=new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            if (product.getCategoryID().equals(categoryid) && product.getPrice() >= min && product.getPrice() <= max) {

                products1.add(product);
            }
        }

return products1;

    }



}





















