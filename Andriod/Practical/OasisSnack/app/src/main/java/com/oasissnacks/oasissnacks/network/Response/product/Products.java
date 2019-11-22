package com.oasissnacks.oasissnacks.network.Response.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products {
    @SerializedName("product_id")
    @Expose
    private String id;
    @SerializedName("product_name")
    @Expose
    private String name;
    @SerializedName("wishlist_id")
    @Expose
    private String wishlistId;

    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice_per_packet() {
        return price_per_packet;
    }

    public void setPrice_per_packet(double price_per_packet) {
        this.price_per_packet = price_per_packet;
    }


    private String image;
    private double price;
    private double price_per_packet;
}
