package com.oasissnacks.oasissnacks.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryData {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


        List<String> snack = new ArrayList<String>();

        List<String> beverages = new ArrayList<String>();

        List<String> groceries = new ArrayList<String>();

        List<String> wellness = new ArrayList<String>();

        List<String> varietypack = new ArrayList<String>();

        List<String> mixmatch = new ArrayList<String>();

        expandableListDetail.put("Snacks", snack);
        expandableListDetail.put("Beverages", beverages);
        expandableListDetail.put("Groceries", groceries);
        expandableListDetail.put("Wellness", wellness);
        expandableListDetail.put("Variety Pack", varietypack);
        expandableListDetail.put("Mix & Match", mixmatch);

        return expandableListDetail;


    }
}
