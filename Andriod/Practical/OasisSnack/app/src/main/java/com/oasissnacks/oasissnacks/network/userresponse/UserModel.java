package com.oasissnacks.oasissnacks.network.userresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_in")
    @Expose
    private String createdIn;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("website_id")
    @Expose
    private Integer websiteId;
    @SerializedName("addresses")
    @Expose
    private List<Address> addresses = null;
    @SerializedName("disable_auto_group_change")
    @Expose
    private Integer disableAutoGroupChange;
    @SerializedName("custom_attributes")
    @Expose
    private List<CustomAtribute> customAttributes = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(String createdIn) {
        this.createdIn = createdIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Integer websiteId) {
        this.websiteId = websiteId;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Integer getDisableAutoGroupChange() {
        return disableAutoGroupChange;
    }

    public void setDisableAutoGroupChange(Integer disableAutoGroupChange) {
        this.disableAutoGroupChange = disableAutoGroupChange;
    }

    public List<CustomAtribute> getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(List<CustomAtribute> customAttributes) {
        this.customAttributes = customAttributes;
    }
}
