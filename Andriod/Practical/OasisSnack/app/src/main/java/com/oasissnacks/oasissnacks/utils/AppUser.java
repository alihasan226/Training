package com.oasissnacks.oasissnacks.utils;

import com.oasissnacks.oasissnacks.network.Response.checkoutresponse.CheckoutResponse;
import com.oasissnacks.oasissnacks.network.Response.product.ProductList;
import com.oasissnacks.oasissnacks.network.Response.request.CartRequest;
import com.oasissnacks.oasissnacks.network.Response.user.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suraj on 2/15/2019.
 */

public class AppUser {
    public String auth_token;
    public String cartId="";
    public String first_name;
    public String last_name;
    public String id;
    public String email;
    public String password;
    public String data;
    public String price;
    public String userId;
    public Integer image;
    public  String decription="";
    public String disclamir="";
    public int counter=0;
    public int product=0;
    public List<Address> addressList=new ArrayList<>();
    public List<ProductList> lists=null;
    public CartRequest request=new CartRequest();
    public Map signup=new HashMap();
    public Map login=new HashMap();
    public String cat_id;
    public String page_no;
    public String product_id;
    public Map filter=new HashMap();
    public HashMap<String,Integer> history=new HashMap<>();
    public Map FilerElements=new HashMap();
    public Map CARTELEMENTS=new HashMap();
    public Map forgotPassword=new HashMap();
    public Map EDITPROFILE=new HashMap();
    public List<String> stringList=new ArrayList<>();
    public Map ESTIMATESHIPPINGCOST=new HashMap();
    public Map SELECTEDCOUNTRY=new HashMap();
    public Map ADDREVIEW=new HashMap();
    public Map ADDRESS=new HashMap();
    public Map APPLYCOUPON=new HashMap();
    public Map AMAZON=new HashMap();
   public CheckoutResponse checkoutResponse=new CheckoutResponse();

}
