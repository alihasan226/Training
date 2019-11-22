package com.oasissnacks.oasissnacks.utils;

public class EventLogin {
    private final String token;

    public EventLogin(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}