package com.oasissnacks.oasissnacks.network.Response.homeresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewResponse {
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("reviewTitle")
    @Expose
    private String reviewTitle;
    @SerializedName("reviewDetail")
    @Expose
    private String reviewDetail;
    @SerializedName("reviewNickname")
    @Expose
    private String reviewNickname;
    @SerializedName("reviewDate")
    @Expose
    private String reviewDate;
    @SerializedName("reviewId")
    @Expose
    private String reviewId;
    @SerializedName("reviewProductId")
    @Expose
    private String reviewProductId;
    @SerializedName("productImg")
    @Expose
    private String productImg;
    @SerializedName("rating")
    @Expose
    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(String reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    public String getReviewNickname() {
        return reviewNickname;
    }

    public void setReviewNickname(String reviewNickname) {
        this.reviewNickname = reviewNickname;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewProductId() {
        return reviewProductId;
    }

    public void setReviewProductId(String reviewProductId) {
        this.reviewProductId = reviewProductId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
