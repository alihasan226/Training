package com.example.usl.Network.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_by_user_id")
    @Expose
    private Integer createdByUserId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("phone_number")
    @Expose
    private Object phoneNumber;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("limit")
    @Expose
    private Double limit;
    @SerializedName("exposure")
    @Expose
    private Object exposure;
    @SerializedName("auth_token")
    @Expose
    private String authToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public Object getExposure() {
        return exposure;
    }

    public void setExposure(Object exposure) {
        this.exposure = exposure;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
