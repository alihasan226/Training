package com.oasissnacks.oasissnacks.network.Response.cartresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartProductResponse {
    @SerializedName("product_id")
    @Expose
    private int id;
    @SerializedName("product_name")
    @Expose
    private String name;
    private String price;
    private String image;
    private Boolean in_stock;
    private String quantity;
    private String price_per_packet;
    private String description;

    public boolean getStock() {
        return in_stock;
    }

    public void setStock(Boolean in_stock)
    {
        this.in_stock=in_stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
