package com.oasissnacks.oasissnacks.network.Response.countrylist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCountryResponse {
    @SerializedName("data")
    @Expose
    private CountryListResponse data;
    @SerializedName("status")
    @Expose
    private Integer status;

    public CountryListResponse getData() {
        return data;
    }

    public void setData(CountryListResponse data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
