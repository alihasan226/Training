package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Post {


    @SerializedName("userId")//serializedname is used because if we make some difference on declaring the variable that is mismatch the json name than that annotation manage that
    private int userId;

    @SerializedName("id")
    private Integer id;//we don't want to send it to the server because JSON created it at server

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String text;


    public Post(int userId, String title, String text) {//here we just make a method post to send the data to the APi server
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}


