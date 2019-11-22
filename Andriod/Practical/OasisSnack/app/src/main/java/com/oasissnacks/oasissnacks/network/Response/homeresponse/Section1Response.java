package com.oasissnacks.oasissnacks.network.Response.homeresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Section1Response {
    @SerializedName("main_banner")
    @Expose
    private List<MainBannerResponse> mainBanner = null;

    public List<MainBannerResponse> getMainBanner() {
        return mainBanner;
    }

    public void setMainBanner(List<MainBannerResponse> mainBanner) {
        this.mainBanner = mainBanner;
    }
}
