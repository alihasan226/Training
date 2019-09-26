package com.example.retrofitexample;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("userId")//serializedname is used because if we make some difference on declaring the variable that is mismatch the json name than that annotation manage that
    private int userId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String text;


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

//this class going to convert the JSON object into java object that we can use in our program.