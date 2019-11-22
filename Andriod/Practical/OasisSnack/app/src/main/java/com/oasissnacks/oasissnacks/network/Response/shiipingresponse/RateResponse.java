package com.oasissnacks.oasissnacks.network.Response.shiipingresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RateResponse {

    @SerializedName("carrier_title")
    @Expose
    private String carrierTitle;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("carrier_code")
    @Expose
    private String carrierCode;
    @SerializedName("price")
    @Expose
    private Double price;

    public String getCarrierTitle() {
        return carrierTitle;
    }

    public void setCarrierTitle(String carrierTitle) {
        this.carrierTitle = carrierTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
