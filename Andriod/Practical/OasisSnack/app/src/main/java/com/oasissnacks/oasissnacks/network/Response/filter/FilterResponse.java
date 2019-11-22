package com.oasissnacks.oasissnacks.network.Response.filter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

public class FilterResponse  {

    @SerializedName("filters")
    @Expose
    private List<FilterModel> filters = null;

    public List<FilterModel> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterModel> filters) {
        this.filters = filters;
    }
}
