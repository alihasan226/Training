package com.oasissnacks.oasissnacks.network.Response.cartresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartResponse {
    @SerializedName("status")
    @Expose
    private int  status;
    @SerializedName("message")
    @Expose
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
    @SerializedName("products")
    @Expose
    public List<CartProductResponse> list=null;

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

    public List<CartProductResponse> getList() {
        return list;
    }

    public void setList(List<CartProductResponse> list) {
        this.list = list;
    }
}
