package com.oasissnacks.oasissnacks.network.Response.category;

import java.util.ArrayList;

public class Categories {
    private int id;

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<SubCategory> getSub_category() {
        return sub_category;
    }

    public void setSub_category(ArrayList<SubCategory> sub_category) {
        this.sub_category = sub_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private ArrayList<SubCategory> sub_category;
}
