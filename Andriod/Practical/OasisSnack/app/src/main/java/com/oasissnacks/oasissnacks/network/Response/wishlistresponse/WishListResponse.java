package com.oasissnacks.oasissnacks.network.Response.wishlistresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oasissnacks.oasissnacks.network.Response.product.Products;

import java.util.List;

public class WishListResponse {
    @SerializedName("products")
    @Expose
    private List<Products> products = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
