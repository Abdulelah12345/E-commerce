package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Merchant;
import com.example.ecommerce.Model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor

public class MerchantService {
    ArrayList<Merchant> merchants=new ArrayList<>();

    private final MerchantStockService merchantStockService;

    public ArrayList<Merchant> get(){

        return merchants;
    }

    public void add(Merchant merchant){

        merchants.add(merchant);

    }

    public boolean update(String id , Merchant merchant){
        for (int i=0;i<merchants.size();i++){
            if(merchants.get(i).getId().equals(id)){
                merchants.set(i,merchant);
                return true;
            }
        }

        return false;
    }


    public boolean delete(String id){
        for (int i=0;i<merchants.size();i++){
            if(merchants.get(i).getId().equals(id)){
                merchants.remove(i);
                return true;
            }
        }

        return false;
    }



    public boolean stock(String productid, String merchantid, int amount){

        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {

            if(merchantStockService.merchantStocks.get(i).getProductid().equals(productid)&& merchantStockService.merchantStocks.get(i).getMerchantid().equals(merchantid)&&amount>0){

                merchantStockService.merchantStocks.get(i).setStock(merchantStockService.merchantStocks.get(i).getStock()+amount);

                return true;


            }

        }
        return false;




    }





}
