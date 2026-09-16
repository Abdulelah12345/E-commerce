package com.example.ecommerce.Service;
import com.example.ecommerce.Model.MerchantStock;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    ArrayList<User> users=new ArrayList<>();
    private final MerchantStockService merchantStockService;
    private final ProductService productService;

    public ArrayList<User> get(){

        return users;
    }

    public void add(User user){

        users.add(user);

    }

    public boolean update(String id , User user){
        for (int i=0;i<users.size();i++){
            if(users.get(i).getId().equals(id)){
                users.set(i,user);
                return true;
            }
        }

        return false;
    }


    public boolean delete(String id){
        for (int i=0;i<users.size();i++){
            if(users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        }

        return false;
    }


//need to be updated
public boolean buy(String id, String productid, String merchantid, int num) {

    for (int i = 0; i < users.size(); i++) {

        if (users.get(i).getId().equals(id)) {

            for (int j = 0; j < productService.products.size(); j++) {

                if (productService.products.get(j).getId().equals(productid)) {

                    double totalPrice = num * productService.products.get(j).getPrice();

                    if (users.get(i).getBalance() < totalPrice) {
                        return false;
                    }

                    for (int k = 0; k < merchantStockService.merchantStocks.size(); k++) {

                        MerchantStock stock = merchantStockService.merchantStocks.get(k);

                        if (stock.getProductid().equals(productid)
                                && stock.getMerchantid().equals(merchantid)) {

                            if (stock.getStock() < num) {
                                return false;
                            }

                            stock.setStock(stock.getStock() - num);

                            users.get(i).setBalance(
                                    users.get(i).getBalance() - totalPrice
                            );

                            return true;
                        }
                    }
                }
            }
        }
    }

    return false;
}



    public ArrayList<MerchantStock> bestSeller(){
        ArrayList<MerchantStock> merchantStocks=new ArrayList<>();
        for(int i=0 ;i<merchantStockService.merchantStocks.size();i++){

            if(merchantStockService.merchantStocks.get(i).getStock()<=3){
                merchantStocks.add(merchantStockService.merchantStocks.get(i));
            }


        }


        return merchantStocks;
    }





    public boolean login(String username,String password){

        for (int i =0 ; i<users.size();i++){
            if(users.get(i).getUsername().equals(username)&& users.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;



    }



public Product cheapest(){
        Product cheapest=productService.products.get(0);
        for(int i=0 ;i<productService.products.size();i++){
            if(productService.products.get(i).getPrice()<cheapest.getPrice()){
                cheapest=productService.products.get(i);
            }
        }

        return cheapest;
}

public Product randomItemforBudget(double amount){

        ArrayList<Product> affordable=new ArrayList<>();
        for(int i=0;i<productService.products.size();i++){
            if(productService.products.get(i).getPrice()<=amount){
                affordable.add(productService.products.get(i));
            }
        }
    Random random=new Random();
        int randomindex=random.nextInt(affordable.size());
        return affordable.get(randomindex);


}



    public boolean giftProduct(String senderid, String receiverid, String productid, String merchantid) {

        boolean sender = false;
        boolean receiver = false;

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getId().equals(senderid)) {
                sender = true;
            }

            if (users.get(i).getId().equals(receiverid)) {
                receiver = true;
            }
        }

        if (!sender || !receiver) {
            return false;
        }

        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {

            if (merchantStockService.merchantStocks.get(i).getProductid().equals(productid) && merchantStockService.merchantStocks.get(i).getMerchantid().equals(merchantid) && merchantStockService.merchantStocks.get(i).getStock() > 0) {

                merchantStockService.merchantStocks.get(i).setStock(merchantStockService.merchantStocks.get(i).getStock() - 1);

                return true;
            }
        }

        return false;
    }


    public ArrayList<Product> getByCategoryAndPriceRange(String categoryid,double min,double max){
        ArrayList<Product> products1=new ArrayList<>();
        for (int i = 0; i < productService.products.size(); i++) {


            if ( productService.products.get(i).getCategoryID().equals(categoryid) &&  productService.products.get(i).getPrice() >= min && productService.products.get(i).getPrice() <= max) {

                products1.add(productService.products.get(i));
            }
        }

        return products1;

    }


}


