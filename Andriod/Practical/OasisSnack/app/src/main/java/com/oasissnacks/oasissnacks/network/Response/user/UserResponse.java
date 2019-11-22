package com.oasissnacks.oasissnacks.network.Response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.AddToCartResponse;

public class UserResponse {

    private int status;
    private String message;
    @SerializedName("items_count")
    @Expose
    private Integer itemsCount;
    @SerializedName("items_qty")
    @Expose
    private Integer itemsQty;
    @SerializedName("subtotal")
    @Expose
    private Double subtotal;
    @SerializedName("grand_total")
    @Expose
    private Double grandTotal;
    @SerializedName("cart")
    @Expose
    private AddToCartResponse cart;

    public AddToCartResponse getCart() {
        return cart;
    }

    public void setCart(AddToCartResponse cart) {
        this.cart = cart;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public Integer getItemsQty() {
        return itemsQty;
    }

    public void setItemsQty(Integer itemsQty) {
        this.itemsQty = itemsQty;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;


}
