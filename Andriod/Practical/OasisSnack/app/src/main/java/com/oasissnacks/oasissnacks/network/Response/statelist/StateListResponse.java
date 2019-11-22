package com.oasissnacks.oasissnacks.network.Response.statelist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateListResponse {
    @SerializedName("states")
    @Expose
    private List<StateResponse> states = null;

    public List<StateResponse> getStates() {
        return states;
    }

    public void setStates(List<StateResponse> states) {
        this.states = states;
    }
}
