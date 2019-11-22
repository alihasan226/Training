package com.oasissnacks.oasissnacks.network.Response.product;

public class ProductDetailsResponse {
    private int status;

    public ProductDetails getProducts() {
        return products;
    }

    public void setProducts(ProductDetails products) {
        this.products = products;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private ProductDetails products;
}
