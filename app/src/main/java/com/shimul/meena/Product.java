package com.shimul.meena;

public class Product {
    private String product_name;
    private String product_desc;
    private String product_image;
    private String weight_class;
    private String weight;
    private String price;
    private String dicount;


    public Product(String product_name, String product_desc, String product_image, String weight_class, String weight, String price, String discount) {
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_image = product_image;
        this.weight_class = weight_class;
        this.weight = weight;
        this.price = price;
        this.dicount = discount;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getWeight_class() {
        return weight_class;
    }

    public String getWeight() {
        return weight;
    }

    public String getPrice() {
        return price;
    }

    public String getDicount(){
        return dicount;
    }
}
