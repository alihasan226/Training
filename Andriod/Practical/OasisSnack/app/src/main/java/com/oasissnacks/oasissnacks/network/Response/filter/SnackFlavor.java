package com.oasissnacks.oasissnacks.network.Response.filter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SnackFlavor {
    @SerializedName("option_id")
    @Expose
    private String optionId;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("attribute_id")
    @Expose
    private String attributeId;
    @SerializedName("attribute_code")
    @Expose
    private String attributeCode;

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeCode() {
        return attributeCode;
    }

    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }
}
