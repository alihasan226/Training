package com.oasissnacks.oasissnacks.network;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.google.gson.JsonObject;
import com.oasissnacks.oasissnacks.app.ThisApp;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.AddToCartResponse;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.CartResponse;
import com.oasissnacks.oasissnacks.network.Response.countrylist.GetCountryResponse;
import com.oasissnacks.oasissnacks.network.Response.filter.FilterResponse;
import com.oasissnacks.oasissnacks.network.Response.homeresponse.HomeResponse;
import com.oasissnacks.oasissnacks.network.Response.product.ProductDetailsResponse;
import com.oasissnacks.oasissnacks.network.Response.product.ProductResponse;
import com.oasissnacks.oasissnacks.network.Response.shiipingresponse.EstimateRateResponse;
import com.oasissnacks.oasissnacks.network.Response.statcresponse.StaticPageResponse;
import com.oasissnacks.oasissnacks.network.Response.statelist.GetStateListResponse;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.network.Response.category.CategoryResponse;
import com.oasissnacks.oasissnacks.network.Response.wishlistresponse.WishListResponse;
import com.oasissnacks.oasissnacks.network.userresponse.GetUserResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiCallService extends IntentService {
    Api api;
    AppUser appUser;

    public ApiCallService() {
        super(Cv.SERVICE_NAME);

    }

    public static void action(Context ctx, String action) {
        Intent intent = new Intent(ctx, ApiCallService.class);
        intent.setAction(action);
        ctx.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        api = ThisApp.getApi(this);
        appUser = LocalRepositories.getAppUser(getApplicationContext());
        if (Cv.ACTION_LOGIN.equals(action)) {
            api.login(appUser.login).enqueue(new ApiCallBack<>(new UserResponse()));
        } else if (Cv.ACTION_SIGNUP.equals(action)) {
            api.signup(appUser.signup).enqueue(new ApiCallBack<>(new UserResponse()));
        } else if (Cv.ACTION_GET_CATEGORY.equals(action)) {
            api.get_category().enqueue(new ApiCallBack<>(new CategoryResponse()));
        } else if (Cv.ACTION_GET_CATEGORY_PRODUCTS.equals(action)) {
            appUser.filter.put("category_id", Preferences.getInstance(this).getCatID());
            appUser.filter.put("page", appUser.page_no);

            api.get_category_product(appUser.filter).enqueue(new ApiCallBack<>(new ProductResponse()));


        } else if (Cv.ACTION_GET_PRODUCT_DETAILS.equals(action)) {
            api.product_details(appUser.product_id).enqueue(new ApiCallBack<>(new ProductDetailsResponse()));
        } else if (Cv.ACTION_FILTER.equals(action)) {
            api.getFilters(Preferences.getInstance(this).getCatID()).enqueue(new ApiCallBack<>(new FilterResponse()));
        } else if (Cv.ACTION_GETCART.equalsIgnoreCase(action)) {
            api.getCartProucts(Preferences.getInstance(this).getCartId()).enqueue(new ApiCallBack<>(new CartResponse()));
        } else if (Cv.ACTIONDELETECART.equals(action)) {
            api.deleteCart(Preferences.getInstance(this).getCartId(), Preferences.getInstance(this).getProductID()).enqueue(new ApiCallBack<>(new UserResponse()));
        } else if (Cv.ACTION_EDITCART.equals(action)) {
            api.editCart(appUser.request).enqueue(new ApiCallBack<>(new CartResponse()));
        } else if (Cv.ACTION_ADDCART.equals(action)) {
            api.addCart(appUser.request).enqueue(new ApiCallBack<>(new AddToCartResponse()));
        } else if (Cv.ACTION_GET_HOME.equals(action)) {
            api.getHomeAPi().enqueue(new ApiCallBack<>(new HomeResponse()));
        } else if (Cv.ACTIONFORGOTPASSWORD.equals(action)) {
            api.forgotPasswordApi(appUser.forgotPassword).enqueue(new ApiCallBack<>(new UserResponse()));
        } else if (Cv.ACTIONGETDETAILS.equals(action)) {
            api.getUserAPi().enqueue(new ApiCallBack<>(new GetUserResponse()));
        } else if (Cv.ACTIONSAVEPROFILE.equals(action)) {
            api.saveUSerProfile(appUser.EDITPROFILE).enqueue(new ApiCallBack<>(new UserResponse()));
        } else if (Cv.ACTIONGETSHIPPINGMETHOD.equals(action)) {
            Map map=new HashMap();
            map.put("cart_id",Preferences.getInstance(this).getCartId());
            if (Preferences.getInstance(this).getLogin()){

                map.put("user_id",Preferences.getInstance(this).getUserid());
            }else {
                map.put("user_id","guest");

            }
            map.put("id","1473");
            api.getEstimateShippingCost(map).enqueue(new ApiCallBack<>(new EstimateRateResponse()));
        }else if (Cv.ACTIONADDCHECKOUTADDRESS.equals(action)){
            api.addShippingAddress(appUser.checkoutResponse).enqueue(new ApiCallBack<>(new UserResponse()));
        }else if(Cv.ACTIONSTATICPAGE.equals(action)){
            api.getStaticContent(Preferences.getInstance(this).getPAgename()).enqueue(new ApiCallBack<>(new StaticPageResponse()));
        } else if(Cv.ACTION_GET_All_RELATEDPRODUCT.equals(action)){
            api.get_all_relatedproduct(Preferences.getInstance(this).getProductID(),appUser.page_no).enqueue(new ApiCallBack<>(new ProductResponse()));
        }else if(Cv.COUPONCODE.equals(action)){
            api.getCouponStatus(appUser.request).enqueue(new ApiCallBack<>(new UserResponse()));
        }else if (Cv.ADDWISHLIST.equals(action)){
            api.addWishList(Preferences.getInstance(this).getProductID()).enqueue(new ApiCallBack<>(new UserResponse()));
        }else if(Cv.GETWISHLIST.equals(action)){
            api.getWishList().enqueue(new ApiCallBack<>(new WishListResponse()));
        }else if (Cv.REMOVEWISHLIST.equals(action)){
            api.removeWishlist(Preferences.getInstance(this).getProductID()).enqueue(new ApiCallBack<>(new UserResponse()));
        }else if (Cv.POSTREVIEW.equals(action))
        {
            api.addReview(appUser.ADDREVIEW).enqueue(new ApiCallBack<>(new UserResponse()));
        }else if(Cv.APPLYCOUPON.equals(action)){
            api.applyCoupon(appUser.APPLYCOUPON).enqueue(new ApiCallBack<>(new UserResponse()));
        }else if(Cv.AMAZONLOGIN.equals(action)){
            api.amazonLogin(appUser.AMAZON).enqueue(new ApiCallBack<>(new UserResponse()));
        }else if(Cv.GETUSERADDRESSLIST.equals(action)){
            Map map=new HashMap();
            map.put("cart_id",Preferences.getInstance(this).getCartId());
            if(Preferences.getInstance(this).getLogin()){
                map.put("user_id",Preferences.getInstance(this).getUserid());
            }else {
                map.put("user_id","guest");
            }
            api.getSavedAddressList(map).enqueue(new ApiCallBack<>(new GetUserResponse()));
        }else if(Cv.ESTIMATESHIPPINGADDRESS.equals(action)){
            api.getEstimateShippingRates(appUser.ESTIMATESHIPPINGCOST).enqueue(new ApiCallBack<>(new UserResponse()));
        }else if (Cv.GETPAYMENTMETHOD.equals(action)){
            Map map=new HashMap();
            map.put("cart_id",Preferences.getInstance(this).getCartId());
            api.getPaymentMethod(map).enqueue(new ApiCallBack<>(new UserResponse()));
        }else if(Cv.GETMYORDERS.equals(action)){
            api.getOrders().enqueue(new ApiCallBack<>(new UserResponse()));
        }else if (Cv.GETMYORDERDETAILS.equals(action)){
            api.getMyOrderDetails(Preferences.getInstance(this).getOrderID()).enqueue(new ApiCallBack<>(new UserResponse()));
        }else if (Cv.GETCOUNTRYLIST.equals(action)){
            api.getCountryListApi().enqueue(new ApiCallBack<>(new GetCountryResponse()));
        }else if (Cv.GETSTATELIST.equals(action)){
            Map map=new HashMap();
            map.put("country_code",Preferences.getInstance(this).getCountry());
            api.getStateList(map).enqueue(new ApiCallBack<>(new GetStateListResponse()));
        }
    }


}
