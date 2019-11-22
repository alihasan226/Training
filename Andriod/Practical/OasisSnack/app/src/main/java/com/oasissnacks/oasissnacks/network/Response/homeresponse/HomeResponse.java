package com.oasissnacks.oasissnacks.network.Response.homeresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeResponse {
    @SerializedName("body")
    @Expose
    private HomeBodyResponse body;
    @SerializedName("status")
    @Expose
    private Integer status;

    public HomeBodyResponse getBody() {
        return body;
    }

    public void setBody(HomeBodyResponse body) {
        this.body = body;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
