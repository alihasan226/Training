package com.oasissnacks.oasissnacks.network.Response.homeresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Section3Response {
    @SerializedName("title")
    @Expose
    private List<String> title = null;
    @SerializedName("products")
    @Expose
    private List<ProductResponse> products = null;

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
