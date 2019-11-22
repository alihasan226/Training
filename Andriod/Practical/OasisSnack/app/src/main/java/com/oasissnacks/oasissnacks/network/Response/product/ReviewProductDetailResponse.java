package com.oasissnacks.oasissnacks.network.Response.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewProductDetailResponse {



    @SerializedName("review_id")
    @Expose
    private String reviewId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("reviewTitle")
    @Expose
    private String reviewTitle;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
