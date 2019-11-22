package com.oasissnacks.oasissnacks.network;

import android.app.DownloadManager;

import com.oasissnacks.oasissnacks.network.Response.cartresponse.AddToCartResponse;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.CartResponse;
import com.oasissnacks.oasissnacks.network.Response.checkoutresponse.CheckoutResponse;
import com.oasissnacks.oasissnacks.network.Response.countrylist.GetCountryResponse;
import com.oasissnacks.oasissnacks.network.Response.filter.FilterResponse;
import com.oasissnacks.oasissnacks.network.Response.homeresponse.HomeResponse;
import com.oasissnacks.oasissnacks.network.Response.product.ProductDetailsResponse;
import com.oasissnacks.oasissnacks.network.Response.product.ProductResponse;
import com.oasissnacks.oasissnacks.network.Response.request.CartRequest;
import com.oasissnacks.oasissnacks.network.Response.shiipingresponse.EstimateRateResponse;
import com.oasissnacks.oasissnacks.network.Response.statcresponse.StaticPageResponse;
import com.oasissnacks.oasissnacks.network.Response.statelist.GetStateListResponse;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.network.Response.category.CategoryResponse;
import com.oasissnacks.oasissnacks.network.Response.wishlistresponse.WishListResponse;
import com.oasissnacks.oasissnacks.network.userresponse.GetUserResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @POST("users/sign_in")
    Call<UserResponse> login(@Body Map map);

    @POST("users")
    Call<UserResponse> signup(@Body Map payload);

    @POST("users/amazon")
    Call<UserResponse> amazonLogin(@Body Map map);

    @GET("categories")
    Call<CategoryResponse> get_category();

    /*@GET("categories/products")
    Call<ProductResponse> get_category_product(@Query("category_id") String category_id, @Query("page") String page);*/
    @POST("categories/products")
    Call<ProductResponse> get_category_product(@Body Map map);

    @GET("product")
    Call<ProductDetailsResponse> product_details(@Query("product_id") String product_id);

    @GET("get_filters")
    Call<FilterResponse> getFilters(@Query("category_id") String category_id);

    @GET("home")
    Call<HomeResponse> getHomeAPi();

    @GET("get_cart")
    Call<CartResponse> getCartProucts(@Query("cart_id") String cartID);

    @POST("products/add_to_cart")
    Call<AddToCartResponse> addCart(@Body CartRequest request);

    @GET("delete_cart_product")
    Call<UserResponse> deleteCart(@Query("cart_id") String cartId, @Query("product_id") String productId);
    @PATCH("edit_cart")
    Call<CartResponse> editCart(@Body CartRequest request);
    @POST("users/forgot_password")
    Call<UserResponse> forgotPasswordApi(@Body Map map);
    @GET("get_user")
    Call<GetUserResponse> getUserAPi();
    @POST("edit_profile")
    Call<UserResponse> saveUSerProfile(@Body Map map);
    @POST("get_shipping_method")
    Call<EstimateRateResponse> getEstimateShippingCost(@Body Map request);
    @POST("add_shipping_address")
    Call<UserResponse> addShippingAddress(@Body CheckoutResponse checkoutResponse);
    @GET("api/v1/index.php/cms_page")
    Call<StaticPageResponse> getStaticContent(@Query("page") String pagename);
    @GET("getallrelated")
    Call<ProductResponse> get_all_relatedproduct(@Query("product_id") String product_id, @Query("page") String page);
    @POST("apply_coupon")
    Call<UserResponse> getCouponStatus(@Body CartRequest request);
    @GET("add_to_wishlist")
    Call<UserResponse> addWishList(@Query("product_id") String productId);
    @DELETE("remove_from_wishlist")
    Call<UserResponse> removeWishlist(@Query("product_id") String productId);
    @GET("wishlist")
    Call<WishListResponse> getWishList();
    @POST("post_review")
    Call<UserResponse> addReview(@Body Map map);
    @POST("apply_coupon")
    Call<UserResponse> applyCoupon(@Body Map map);
    @POST("get_user_address_list")
    Call<GetUserResponse>getSavedAddressList(@Body Map map);
    @POST("estimate_shipping_rates")
    Call<UserResponse>getEstimateShippingRates(@Body Map map);
    @POST("get_payment_method")
    Call<UserResponse>getPaymentMethod(@Body Map map );
    @GET("orders")
    Call<UserResponse>getOrders();
    @POST("get_order_detail")
    Call<UserResponse> getMyOrderDetails(@Query("order_id") String orderId);
    @GET("get_countries")
    Call<GetCountryResponse>getCountryListApi();
    @POST("get_countries")
    Call<GetStateListResponse>getStateList(@Body Map map);


}
