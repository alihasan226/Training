package com.oasissnacks.oasissnacks.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pc on 10/7/2016.
 */
public class Preferences {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "retech";
    private static Preferences instance;
    private static final String CHECKED="checked";
    private static final String IS_LOGIN = "Login";
    private static final String EMAIL = "email";
    private static final String SETADDRESS = "address";
    private static final String PAGENAME = "static-contentpage";
    private static final String PRICEMINVALUE = "priceminvalue";
    private static final String PRICEMAXVALUE = "pricemaxvalue";
    private static final String CARTID = "cart_id";
    private static final String USERID = "user_id";
    private static final String PRODUCTID = "product_id";
    private static final String ORDERID = "order_id";
    private static final String CATID = "cat_id";
    private static final String TOKEN = "token";
    private static final String PASSWORD = "password";
    private static final String SOCIAL_ID = "social_id";
    private static final String SOCIAL_TYPE = "social_type";
    private static final String TOUCH_BOL = "touch_bol";
    private static final String CAMERA_DIALOG = "camera_dialog";
    private static final String SELECTEDCOUNTRY = "set_country";
    private static final String  TOTAL_PRODUCTS ="total_products";
    private static final String  AUTH_TOKEN ="auth_token";
    private static final String  COUNTER ="counter";


    private Preferences(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static Preferences getInstance(Context context) {
        if (instance == null) {
            instance = new Preferences(context);
        }
        return instance;
    }

    public void setChecked(Boolean isChecked){
        editor.putBoolean(CHECKED,isChecked);
        editor.commit();
    }

    public boolean getChecked(){
        return pref.getBoolean(CHECKED,false);
    }


    public void setLogin(Boolean isLogin) {
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }

    public Boolean getLogin() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setTouchBol(Boolean touch_bol) {
        editor.putBoolean(TOUCH_BOL, touch_bol);
        editor.commit();
    }

    public Boolean getTouchBol() {
        return pref.getBoolean(TOUCH_BOL, false);
    }

    public void setAddress(String address){
        editor.putString(SETADDRESS,address);
        editor.commit();
    }

    public String getAddress(){
        return pref.getString(SETADDRESS,"");


    }
    public void setCounty(String address){
        editor.putString(SELECTEDCOUNTRY,address);
        editor.commit();
    }

    public String getCountry(){
        return pref.getString(SELECTEDCOUNTRY,"");


    }
    public void setOrderID(String orderID){
        editor.putString(ORDERID,orderID);
        editor.commit();
    }

    public String getOrderID(){
        return pref.getString(ORDERID,"");


    }
    public void setEmail(String email){
        editor.putString(EMAIL,email);
        editor.commit();
    }

    public String getEmail(){
        return pref.getString(EMAIL,"");


    }
    public void setPAgeName(String email){
        editor.putString(PAGENAME,email);
        editor.commit();
    }

    public String getPAgename(){
        return pref.getString(PAGENAME,"");

    }
    public void setAuthToken(String authToken){
        editor.putString(AUTH_TOKEN,authToken);
        editor.commit();
    }

    public String getAuthToken(){
        return pref.getString(AUTH_TOKEN,"");

    }
    public void setCounter(int counter){
        editor.putInt(COUNTER,counter);
        editor.commit();
    }

    public int getCounter(){
        return pref.getInt(COUNTER,0);

    }
    public void setPRoducts(int email){
        editor.putInt(TOTAL_PRODUCTS,email);
        editor.commit();
    }

    public int getProducts(){
        return pref.getInt(TOTAL_PRODUCTS,0);

    }
    public void setCatID(String catID){
        editor.putString(CATID,catID);
        editor.commit();
    }
    public String getPriceMInvAlue(){
        return pref.getString(PRICEMINVALUE,"");

    }
    public void setPriceMinValue(String price){
        editor.putString(PRICEMINVALUE,price);
        editor.commit();
    }
    public String getPriceMaxvalue(){
        return pref.getString(PRICEMAXVALUE,"");

    }
    public void setPriceMAxValue(String price){
        editor.putString(PRICEMAXVALUE,price);
        editor.commit();
    }
    public String getCartId(){
        return pref.getString(CARTID,"");

    }
    public void setCartID(String id){
        editor.putString(CARTID,id);
        editor.commit();
    }
    public String getUserid(){
        return pref.getString(USERID,"");

    }
    public void setUserid(String id){
        editor.putString(USERID,id);
        editor.commit();
    }
    public String getProductID(){
        return pref.getString(PRODUCTID,"");

    }
    public void setProductID(String id){
        editor.putString(PRODUCTID,id);
        editor.commit();
    }

    public String getCatID(){
        return pref.getString(CATID,"");

    }
    public  void  insertToSP(HashMap<String, Integer>   jsonMap) {
        String jsonString = new Gson().toJson(jsonMap);
        SharedPreferences sharedPreferences = _context.getSharedPreferences("HashMap", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("map", jsonString);
        editor.apply();
    }

/*    public HashMap<String, Integer>   readFromSP(){
        SharedPreferences sharedPreferences =_context.getSharedPreferences("HashMap", MODE_PRIVATE);
        String defValue = new Gson().toJson(new HashMap<String, Integer>  ());
        String json=sharedPreferences.getString("map",defValue);
        TypeToken<HashMap<String, Integer> > token = new TypeToken<HashMap<String, Integer>>() {};
        HashMap<String, Integer>  retrievedMap=new Gson().fromJson(json,token.getType());
        return retrievedMap;
    }*/
    public HashMap<String, Integer>   readFromSP(){
        SharedPreferences sharedPreferences =_context.getSharedPreferences("HashMap", MODE_PRIVATE);
        String defValue = new Gson().toJson(new HashMap<String, Integer>  ());
        String json=sharedPreferences.getString("map",defValue);
        TypeToken<HashMap<String, Integer> > token = new TypeToken<HashMap<String, Integer>>() {};
        HashMap<String, Integer>  retrievedMap=new Gson().fromJson(json,token.getType());



        return retrievedMap;
    }


    public void setPassword(String password){
        editor.putString(PASSWORD,password);
        editor.commit();
    }

    public String getPassword(){
        return pref.getString(PASSWORD,"");

    }

    public void setSocialId(String social_id){
        editor.putString(SOCIAL_ID,social_id);
        editor.commit();
    }

    public String getSocialId(){
        return pref.getString(SOCIAL_ID,"");

    }

    public void setSocialType(String social_type){
        editor.putString(SOCIAL_TYPE,social_type);
        editor.commit();
    }

    public String getSocialType(){
        return pref.getString(SOCIAL_TYPE,"");

    }

    public void setCameraDialog(Boolean dialog) {
        editor.putBoolean(CAMERA_DIALOG, dialog);
        editor.commit();
    }

    public Boolean getCameraDialog() {
        return pref.getBoolean(CAMERA_DIALOG, false);
    }

    public void setToken(String token) {
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getToken() {
        return pref.getString(TOKEN, "");
    }


}
