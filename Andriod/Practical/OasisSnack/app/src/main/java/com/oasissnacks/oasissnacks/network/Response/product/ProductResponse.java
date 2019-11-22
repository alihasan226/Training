package com.oasissnacks.oasissnacks.network.Response.product;

import java.util.ArrayList;

public class ProductResponse {


    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }


    private ArrayList<Products> products;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
