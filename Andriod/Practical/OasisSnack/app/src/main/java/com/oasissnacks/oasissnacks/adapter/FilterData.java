package com.oasissnacks.oasissnacks.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterData {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> price = new ArrayList<String>();
        price.add("Add");

        List<String> drink_flavour = new ArrayList<String>();
        drink_flavour.add("N/A (1)");
        drink_flavour.add("Apple (1)");
        drink_flavour.add("Coconut (1)");
        drink_flavour.add("Pineapple (1)");
        drink_flavour.add("Water (1)");
        drink_flavour.add("Watermelon (1)");


        List<String> snack_flavour = new ArrayList<String>();
        snack_flavour.add("Barbecue (1)");
        snack_flavour.add("Black Bean (1)");
        snack_flavour.add("Sea Salt (1)");

        List<String> brand = new ArrayList<String>();
        brand.add("Sign Up");

        List<String> special_diet = new ArrayList<String>();
        special_diet.add("Beanfields (1)");

        List<String> subscribe_and_save = new ArrayList<String>();
        subscribe_and_save.add("No (29)");
        subscribe_and_save.add("Optional (6)");


        expandableListDetail.put("Price", price);
        expandableListDetail.put("Drink Flavour", drink_flavour);
        expandableListDetail.put("Snack Flavour", snack_flavour);
        expandableListDetail.put("Brand", brand);
        expandableListDetail.put("Special Diet", special_diet);
        expandableListDetail.put("Subscribe And Save", subscribe_and_save);

        return expandableListDetail;
    }
}
