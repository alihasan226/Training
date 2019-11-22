package com.oasissnacks.oasissnacks.network.Response.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedProducts {
    @SerializedName("product_id")
    @Expose
    private String id;
    @SerializedName("product_name")
    @Expose
    private String name;
    private double price;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPieces_per_packet() {
        return pieces_per_packet;
    }

    public void setPieces_per_packet(int pieces_per_packet) {
        this.pieces_per_packet = pieces_per_packet;
    }

    public double getPrice_per_packet() {
        return price_per_packet;
    }

    public void setPrice_per_packet(double price_per_packet) {
        this.price_per_packet = price_per_packet;
    }

    private int pieces_per_packet;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private double price_per_packet;

    private String image;
}
