package com.oasissnacks.oasissnacks.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartItemData {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> estimate_shipping_and_tax = new ArrayList<String>();
        estimate_shipping_and_tax.add("Disclaimer");

        expandableListDetail.put("Estimate Shipping and Tax", estimate_shipping_and_tax);

        return expandableListDetail;
    }
}
