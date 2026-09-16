package com.example.ecommerce.Service;

import com.example.ecommerce.Model.MerchantStock;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks=new ArrayList<>();

    public ArrayList<MerchantStock> get(){

        return merchantStocks;
    }

    public void add(MerchantStock merchantStock){

        merchantStocks.add(merchantStock);

    }

    public boolean update(String id , MerchantStock merchantStock){
        for (int i=0;i<merchantStocks.size();i++){
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }

        return false;
    }


    public boolean delete(String id){
        for (int i=0;i<merchantStocks.size();i++){
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true;
            }
        }

        return false;
    }


    public ArrayList<MerchantStock> outOfStock(){

        ArrayList<MerchantStock> mer=new ArrayList<>();
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getStock()==0){
                mer.add(merchantStocks.get(i));
            }

        }


return mer;
    }







}
