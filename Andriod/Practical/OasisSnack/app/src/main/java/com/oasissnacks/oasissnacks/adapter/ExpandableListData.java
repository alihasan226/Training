package com.oasissnacks.oasissnacks.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListData {


        public static HashMap<String, List<String>> getData() {
            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

            List<String> details = new ArrayList<String>();
            details.add("Disclaimer");


            List<String> moreinformation = new ArrayList<String>();
            moreinformation.add("Brand: Bubly");


            List<String> disclaimer = new ArrayList<String>();
            disclaimer.add("Legal Disclaimer: While we work our best to ensure that product information is correct, on occasion manufacturers may alter their ingredient lists, product name, or packaging. Actual product packaging and materials may contain more and/or different information than that shown on our Web site. We recommend that you do not solely rely on the information presented and that you always read labels, warnings, and directions before using or consuming a product. For additional information about a product, please contact the manufacturer. Content on this site is for reference purposes and is not intended to substitute for advice given by a physician, pharmacist, or other licensed health-care professional. You should not use this information as self-diagnosis or for treating a health problem or disease. Contact your health-care provider immediately if you suspect that you have a medical problem. Information and statements regarding dietary supplements have not been evaluated by the Food and Drug Administration and are not intended to diagnose, treat, cure, or prevent any disease or health condition. Oasissnacks.com assumes no liability for inaccuracies or misstatements about products.");


            List<String> review=new ArrayList<String>();
            review.add("Sign Up");

            expandableListDetail.put("Details", details);
            expandableListDetail.put("More Information", moreinformation);
            expandableListDetail.put("Disclaimer", disclaimer);
            expandableListDetail.put("Reviews",review);


            return expandableListDetail;


        }


    }