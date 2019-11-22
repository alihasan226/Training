package com.oasissnacks.oasissnacks.network.Response.countrylist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryListResponse {
    @SerializedName("countries")
    @Expose
    private List<CountryResponse> countries = null;

    public List<CountryResponse> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryResponse> countries) {
        this.countries = countries;
    }
}
