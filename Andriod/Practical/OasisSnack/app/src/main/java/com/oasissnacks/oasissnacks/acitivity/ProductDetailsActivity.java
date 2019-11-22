package com.oasissnacks.oasissnacks.acitivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.CustomExpandableListAdapter;
import com.oasissnacks.oasissnacks.adapter.ExpandableListData;
import com.oasissnacks.oasissnacks.adapter.ImageAdapter;
import com.oasissnacks.oasissnacks.adapter.ImagePagerAdapter;
import com.oasissnacks.oasissnacks.adapter.RelatedProductAdapter;
import com.oasissnacks.oasissnacks.adapter.ReviewProductDetailAdapter;
import com.oasissnacks.oasissnacks.adapter.imageData;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.AddToCartResponse;
import com.oasissnacks.oasissnacks.network.Response.product.ProductDetailsResponse;
import com.oasissnacks.oasissnacks.network.Response.product.ProductList;
import com.oasissnacks.oasissnacks.network.Response.request.CartRequest;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.CustomTextView;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;
import com.oasissnacks.oasissnacks.utils.Preferences;

import androidx.appcompat.app.ActionBar;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class ProductDetailsActivity extends RegisterAbstractActivity implements View.OnClickListener {

    private ActionBar actionBar;

    @BindView(R.id.viewpager_image)
    ViewPager viewPager;
    private AppUser appUser = new AppUser();
    private LocalRepositories localRepositories;
    private static int currentPage = 0;
    private ArrayList<String> IMAGEARRAY = new ArrayList<String>();
    /*@BindView(R.id.dots)
     LinearLayout linearLayout;*/

    @BindView(R.id.tv_viewmore)
    TextView mViewMore;
    @BindView(R.id.layout_qty)
    LinearLayout layout_qty;
    @BindView(R.id.toolbar)
    View toolbar;
    @BindView(R.id.tv_name)
    TextView mName;
    @BindView(R.id.price_per_count)
    TextView mPricePerCount;
    @BindView(R.id.tvProductPrice)
    TextView mProductPrice;
    @BindView(R.id.desc)
    WebView mDescription;
    @BindView(R.id.sku)
    TextView mSku;
    @BindView(R.id.circleIndicator)
    CircleIndicator circleIndicator;
    @BindView(R.id.btnAddToCart)
    Button btnAddToCart;

    @BindView(R.id.btnBuy)
    Button btnBuy;
    @BindView(R.id.rvReviewProductDetails)
    RecyclerView rvREviewPRoductDetails;
    @BindView(R.id.tvStock)
    TextView tvStock;
    @BindView(R.id.ivWisListGray)
    ImageView ivWishListGray;
    @BindView(R.id.tvWriteAReview)
    CustomTextView tvWriteReview;

    private int dotscounts = 0;
    public ProductList productList = new ProductList();
    private ImageView[] dots;
    private int custom_position = 0;
   /*
   @BindView(R.id.btnAddTocart)
    CustomTextView btnaddtocart;
*/
   public String[] item_quantity = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "23", "23", "24", "25", "26", "27", "28", "29", "30"};
    List<String> list_group;
    HashMap<String, List<String>> list_child;
    CustomExpandableListAdapter expandableListAdapter;
    public ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();

    ImageAdapter adapter;
    List<imageData> image;
    public Map AddToCartRequest = new HashMap();
    TextView tvHeading, tvCartValue;
    ArrayList item_price, item_name, item_image;
    String[] product_name = {"What's New", "Snacks", "Beverages", "Wellness", "Groceries", "Variety Pack", "Mix & Match"};
    public final Integer[] imageid = {R.drawable.oasissnack_whatnew, R.drawable.oasissnack_snacks, R.drawable.bubly_sparking_water_details, R.drawable.oasissnack_wellness, R.drawable.oasissnack_groceries, R.drawable.oasissnack_varietypack, R.drawable.oasissnack_mixmatch};
    String[] price = {"$19.99", "$29.99", "$15.99", "$19.99", "$19.99", "$29.99", "$15.99"};
    @BindView(R.id.recycler_view_options)
    RecyclerView recyclerView;
    @BindView(R.id.rlAllDetails)
    RelativeLayout rlAllDetails;
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.QuantitySpinnerDetail)
    CustomTextView spinner;

    private ImageView ivCart;
    public boolean isSelected;
    MyProgressDialog progressDialog;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        ivWishListGray.setOnClickListener(this);
        layout_qty.setOnClickListener(this);
        tvHeading = toolbar.findViewById(R.id.tvHeading);
        tvCartValue = toolbar.findViewById(R.id.tvCartValue);
        rlAllDetails.setOnClickListener(this);
        tvWriteReview.setOnClickListener(this);
        ivCart = toolbar.findViewById(R.id.ivCart);
        tvHeading.setText("");
        appUser = LocalRepositories.getAppUser(getApplicationContext());


        if (Preferences.getInstance(this).getCounter() == 0) {

            tvCartValue.setVisibility(View.INVISIBLE);
        } else {
            tvCartValue.setText("" + Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }
        btnAddToCart.setOnClickListener(this);
        ivCart.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTION_GET_PRODUCT_DETAILS);
        } else {
            Helper.alert(ProductDetailsActivity.this, "No Internet Connection", "Oasis Snacks");
        }
        circleIndicator.createIndicators(3, 0);
        circleIndicator.setViewPager(viewPager);        // btnaddtocart.setOnClickListener(this);
        item_image = new ArrayList<>(Arrays.asList(imageid));
        item_price = new ArrayList<>(Arrays.asList(price));
        item_name = new ArrayList<>(Arrays.asList(product_name));
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    private void init() {

        viewPager.setAdapter(new ImagePagerAdapter(ProductDetailsActivity.this, IMAGEARRAY));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.circleIndicator);
        indicator.setViewPager(viewPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {

                viewPager.setCurrentItem(currentPage++, true);
            }
        };
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_product_details;
    }


    public void back(View vIew) {
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivCart:
                if (Preferences.getInstance(this).getCounter() == 0) {
                    Toast.makeText(this, "Please add items to cart.", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(ProductDetailsActivity.this, CartActivity.class));
                }
                break;
            case R.id.btnAddToCart:
                if (btnAddToCart.getText().toString().equalsIgnoreCase("Go To Cart")) {
                    startActivity(new Intent(ProductDetailsActivity.this, CartActivity.class));
                } else {

                    itemAddCart();
                }
                break;
            case R.id.btnBuy:
                startActivity(new Intent(ProductDetailsActivity.this, CartActivity.class));
                break;
            case R.id.rlAllDetails:
                startActivity(new Intent(ProductDetailsActivity.this, AllDetailsActivity.class));
                break;
            case R.id.ivWisListGray:
                if (Preferences.getInstance(this).getLogin()) {
                    if (ivWishListGray.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_heart_red).getConstantState()) {
                        apiRemoveWishList();
                        isSelected = false;
                    } else {
                        isSelected = true;
                        apiAddWishList();
                    }

                    Preferences.getInstance(this).setProductID(productDetailsResponse.getProducts().getId());

                } else {
                    startActivity(new Intent(ProductDetailsActivity.this, LoginActivity.class));
                }
                break;
            case R.id.tvWriteAReview:
                if (Preferences.getInstance(this).getLogin()) {
                    Bundle bundle=new Bundle();
                    bundle.putString("product_name",mName.getText().toString());
                    Intent intent=new Intent(ProductDetailsActivity.this, AddReviewActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else startActivity(new Intent(ProductDetailsActivity.this, LoginActivity.class));
                break;
            case R.id.layout_qty:
                itemQuantity(view);
                break;
            default:
                break;
        }
    }


    private void itemQuantity(View view)
    {
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_quantity, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        Dialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        ImageView imageCancel = (ImageView) dialogView.findViewById(R.id.imageCancel);
        imageCancel.setOnClickListener(view1 -> alertDialog.dismiss());

        ListView listQntyItem = (ListView) dialogView.findViewById(R.id.listQntyItem);
        listQntyItem.setAdapter(new ArrayAdapter<String>(this, R.layout.layout_qntyitem, item_quantity));
        listQntyItem.setOnItemClickListener((adapterView, view12, i, l) -> {
            String value = item_quantity[i];
            spinner.setText(value);
            //listner.onCartClick(view, position, value);
            alertDialog.dismiss();

        });
        alertDialog.show();
        alertDialog.getWindow().setLayout(600, 950);
    }

    private void apiAddWishList() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ADDWISHLIST);
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show();
        }
    }

    private void apiRemoveWishList() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.REMOVEWISHLIST);
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void getWishListStatus(UserResponse response) {
        progressDialog.dismiss();
        if (response.getStatus() == 200) {
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            if (isSelected) {
                ivWishListGray.setImageResource(R.drawable.ic_heart_red);
            } else {
                ivWishListGray.setImageResource(R.drawable.heart);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        appUser = LocalRepositories.getAppUser(getApplicationContext());
        btnAddToCart.setText("Add To Cart");

        if (Preferences.getInstance(this).getCounter() == 0) {

            tvCartValue.setVisibility(View.INVISIBLE);
        } else {
            tvCartValue.setText("" + Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }
    }

    private void itemAddCart() {
        addToCartApi();
        appUser.CARTELEMENTS.clear();
        CartRequest request = new CartRequest();
        request.setCartId(Preferences.getInstance(ProductDetailsActivity.this).getCartId());
        request.setProductId(productDetailsResponse.getProducts().getId());
        request.setQty(Integer.parseInt(spinner.getText().toString()));//add here spinner selected item
        request.setUserId("guest");
        appUser.request = request;
        LocalRepositories.saveAppUser(getApplicationContext(), appUser);
        LocalRepositories.saveAppUser(this, appUser);

    }

    private void addToCartApi() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();

            ApiCallService.action(getApplicationContext(), Cv.ACTION_ADDCART);
        } else {
            Helper.alert(this, "No Internet Connection", "Oasis Snacks");
        }
    }

    @Subscribe
    public void getCartApiDeails(AddToCartResponse addToCartResponse) {
        progressDialog.dismiss();
        if (addToCartResponse.getStatus() == 200) {
            btnAddToCart.setText("Go To Cart");
            LocalRepositories.getAppUser(getApplicationContext());
            Preferences.getInstance(this).setCounter(addToCartResponse.getItemsCount());
            addToCartResponse.getItemsCount();
            tvCartValue.setVisibility(View.VISIBLE);
            tvCartValue.setText(addToCartResponse.getItemsCount() + "");


            Toast.makeText(this, addToCartResponse.getMessage(), Toast.LENGTH_SHORT).show();
            appUser.cartId = addToCartResponse.getCartId();
            Preferences.getInstance(this).setCartID(addToCartResponse.getCartId());

            LocalRepositories.saveAppUser(getApplicationContext(), appUser);
        }
    }


    @Subscribe
    public void product_details(ProductDetailsResponse response) {
        progressDialog.dismiss();
        if (response.getStatus() == 200) {
            productDetailsResponse = response;
            for (int i = 0; i < response.getProducts().getImages().size(); i++) {
                IMAGEARRAY.add(response.getProducts().getImages().get(i));
            }
            init();
            if (response.getProducts().isInWishlist()) {
                ivWishListGray.setImageResource(R.drawable.ic_heart_red);
            } else {
                ivWishListGray.setImageResource(R.drawable.heart);
            }

            mName.setText(response.getProducts().getName());
            mPricePerCount.setText("$" + response.getProducts().getPrice_per_packet() + "/count");
            mProductPrice.setText("$" + response.getProducts().getPrice() + "");
            mSku.setText("SKU#: " + response.getProducts().getSku());
            recyclerView.setAdapter(new RelatedProductAdapter(this, response.getProducts().getRelated_products(), ((view, position) -> {
                appUser.product_id = response.getProducts().getRelated_products().get(position).getId();
                LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                startActivity(new Intent(this, ProductDetailsActivity.class));
            })));
            if (response.getProducts().getInStock()) {
                tvStock.setText("IN STOCK");
                tvStock.setTextColor(Color.parseColor("#0FA319"));
            } else {
                tvStock.setText("OUT OF STOCK");
                tvStock.setTextColor(Color.parseColor("#EC0E0E"));
            }


            mDescription.loadData(response.getProducts().getDetails().getShort_description(), "text/html", "UTF-8");
            list_child = ExpandableListData.getData();
            appUser.disclamir = response.getProducts().getDisclaimer();
            appUser.decription = response.getProducts().getDetails().getDescription();
            List<String> moreinformation = new ArrayList<String>();
            if (response.getProducts().getMore_info() != null) {


                for (int i = 0; i < response.getProducts().getMore_info().size(); i++) {

                    moreinformation.add(response.getProducts().getMore_info().get(i).getTitle() + "= " + response.getProducts().getMore_info().get(i).getValue());

                }
            }
            appUser.stringList = moreinformation;

            LocalRepositories.saveAppUser(getApplicationContext(), appUser);


            if (Preferences.getInstance(this).getLogin()&&response.getProducts().getReviews().size() != 0) {
                rvREviewPRoductDetails.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                rvREviewPRoductDetails.setAdapter(new ReviewProductDetailAdapter(response.getProducts().getReviews(), this));
            }

        } else {
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg, "Error");
    }


    @OnClick(R.id.tv_viewmore)
    public void buttonclickviewmore(View view) {
        startActivity(new Intent(this, ViewMoreActivity.class));
    }





/*
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddTocart:
                appUser.counter++;
                if(tvCartValue.getVisibility()==View.INVISIBLE){
                    tvCartValue.setVisibility(View.VISIBLE);
                    tvCartValue.setText(""+appUser.counter);
                    LocalRepositories.saveAppUser(this,appUser);
                }
                break;
                default:break;
        }
    }

 */
}
