package com.oasissnacks.oasissnacks.network.Response.shiipingresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EstimateRateResponse {
    public String message;

    @SerializedName("rates")
    @Expose
    private List<RateResponse> rates = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RateResponse> getRates() {
        return rates;
    }

    public void setRates(List<RateResponse> rates) {
        this.rates = rates;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
