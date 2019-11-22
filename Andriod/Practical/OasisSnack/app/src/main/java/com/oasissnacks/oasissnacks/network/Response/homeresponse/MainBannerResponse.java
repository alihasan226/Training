package com.oasissnacks.oasissnacks.network.Response.homeresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainBannerResponse {
    @SerializedName("slider_id")
    @Expose
    private String sliderId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("show_title")
    @Expose
    private String showTitle;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("show_description")
    @Expose
    private String showDescription;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("alt_text")
    @Expose
    private String altText;
    @SerializedName("button_text")
    @Expose
    private String buttonText;

    public String getSliderId() {
        return sliderId;
    }

    public void setSliderId(String sliderId) {
        this.sliderId = sliderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getShowDescription() {
        return showDescription;
    }

    public void setShowDescription(String showDescription) {
        this.showDescription = showDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }
}
