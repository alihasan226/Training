package com.oasissnacks.oasissnacks.network.Response.statelist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStateListResponse {
    @SerializedName("data")
    @Expose
    private StateListResponse data;
    @SerializedName("status")
    @Expose
    private Integer status;

    public StateListResponse getData() {
        return data;
    }

    public void setData(StateListResponse data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
