package com.shimul.meena;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{

    @SerializedName("catalog_products")
    private List<Product> products = null;


    public List<Product> getProducts(){
        return products;
    }
}