package com.oasissnacks.oasissnacks.network.Response.statelist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateResponse {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("state_id")
    @Expose
    private String stateId;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }
}
