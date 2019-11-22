package com.oasissnacks.oasissnacks.network.Response.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails {
    @SerializedName("product_id")
    @Expose
    private String id;
    @SerializedName("product_name")
    @Expose
    private String name;
    private double price;
    private List<MoreInfo> more_info = null;
    @SerializedName("is_in_wishlist")
    @Expose
    public boolean isInWishlist;

    public boolean isInWishlist() {
        return isInWishlist;
    }

    public void setInWishlist(boolean inWishlist) {
        isInWishlist = inWishlist;
    }

    private List<ReviewProductDetailResponse> reviews = null;

    public List<MoreInfo> getMore_info() {
        return more_info;
    }

    public void setMore_info(List<MoreInfo> more_info) {
        this.more_info = more_info;
    }

    public List<ReviewProductDetailResponse> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewProductDetailResponse> reviews) {
        this.reviews = reviews;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPieces_per_packet() {
        return pieces_per_packet;
    }

    public void setPieces_per_packet(double pieces_per_packet) {
        this.pieces_per_packet = pieces_per_packet;
    }

    public double getPrice_per_packet() {
        return price_per_packet;
    }

    public void setPrice_per_packet(double price_per_packet) {
        this.price_per_packet = price_per_packet;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }


    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public ArrayList<RelatedProducts> getRelated_products() {
        return related_products;
    }

    public void setRelated_products(ArrayList<RelatedProducts> related_products) {
        this.related_products = related_products;
    }

    private double pieces_per_packet;
    private double price_per_packet;
    private Details details;

    private String disclaimer;
    private ArrayList<String> images;
    private String sku;
    private ArrayList<RelatedProducts> related_products;
    @SerializedName("in_stock")
    @Expose
    private boolean inStock;

    public boolean getInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
