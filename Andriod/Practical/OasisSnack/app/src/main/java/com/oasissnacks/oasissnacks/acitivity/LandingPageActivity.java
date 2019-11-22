package com.oasissnacks.oasissnacks.acitivity;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.CartAdapter;
import com.oasissnacks.oasissnacks.adapter.CategoryAdapter;
import com.oasissnacks.oasissnacks.adapter.CategoryData;
import com.oasissnacks.oasissnacks.adapter.ExpandableListAdapter;
import com.oasissnacks.oasissnacks.adapter.ImageAdapter;
import com.oasissnacks.oasissnacks.adapter.LandingpageRecyclerAdapter;
import com.oasissnacks.oasissnacks.adapter.ReviewAdapter;
import com.oasissnacks.oasissnacks.adapter.SubscribeImageAdapter;
import com.oasissnacks.oasissnacks.adapter.imageData;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.interfce.OnCartItemClickListner;
import com.oasissnacks.oasissnacks.interfce.onViewPagerItemClickListner;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.AddToCartResponse;
import com.oasissnacks.oasissnacks.network.Response.category.CategoryResponse;
import com.oasissnacks.oasissnacks.network.Response.homeresponse.HomeResponse;
import com.oasissnacks.oasissnacks.network.Response.product.ProductDetails;
import com.oasissnacks.oasissnacks.network.Response.request.CartRequest;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;
import com.oasissnacks.oasissnacks.utils.Preferences;
import com.sothree.slidinguppanel.ScrollableViewHelper;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class LandingPageActivity<temp> extends RegisterAbstractActivity implements View.OnClickListener {

    ExpandableListAdapter listAdapter;              //Expandable List Adapter
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.expandableListView)
    ExpandableListView expListView;
    ArrayList list_child;
    HashMap<String, List<String>> list_group;
    private CategoryAdapter expandableListAdapter;


    //category expandable list
   /* @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout slidingUpPanelLayout;*/
    @BindView(R.id.ivCart)
    ImageView ivCart;
   /* @BindView(R.id.category_expandablelist)
    ExpandableListView expandableListView;*///Expandable List instance

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ActionBarDrawerToggle toggle;
    AppUser appUser;
    MyProgressDialog progressDialog;


    LinearLayoutManager linearLayoutManager;
    ImageAdapter adapter, adaptersecond;
    List<imageData> image;
    List<imageData> subscribe;
    ArrayList list_image;

    ArrayList list_text;
    String[] price = {"What's New", "Snacks", "Beverages", "Wellness", "Groceries", "Variety Pack", "Mix & Match"};
    public final Integer[] imageid = {R.drawable.oasissnack_category, R.drawable.oasissnack_whatnew, R.drawable.oasissnack_snacks, R.drawable.bubly_sparking_water_details, R.drawable.oasissnack_wellness, R.drawable.oasissnack_groceries, R.drawable.oasissnack_varietypack, R.drawable.oasissnack_mixmatch, R.drawable.hot_deal};
    @BindView(R.id.landingpage_recyclerview)
    RecyclerView recyclerView;


    public ScrollableViewHelper mScrollableView;


    boolean temp = true;
    int temp1 = 0;

    @BindView(R.id.tvCartValue)
    TextView tvCartValue;
   /* @BindView(R.id.rvProductSecond)
    RecyclerView rvProductSecond;*/

    @BindView(R.id.et_searchbar)
    EditText et_searchbar;
    @BindView(R.id.ivLeftOne)
    ImageView ivLeftOne;
    @BindView(R.id.ivRightOne)
    ImageView ivRightOne;

    @BindView(R.id.viewpager_image)
    ViewPager viewPager;
    @BindView(R.id.circleIndicatorOne)
    CircleIndicator circleIndicatorOne;
    @BindView(R.id.second_viewpager)
    ViewPager secondViewPager;
    @BindView(R.id.webview1)
    WebView webview1;
    @BindView(R.id.webview2)
    WebView webView2;


    @BindView(R.id.circleIndicator)
    CircleIndicator circleIndicator;
    @BindView(R.id.rvReview)
    RecyclerView rvREview;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivRight)

    ImageView ivRight;
    @BindView(R.id.tvProfile)
    TextView tvProfile;
    @BindView(R.id.tvWishList)
    TextView tvWishList;
    @BindView(R.id.tvMyOrder)
    TextView tvMyOrder;
    @BindView(R.id.tvHome)
    TextView tvHome;
    @BindView(R.id.tvContactUs)
    TextView tvContactUS;
    @BindView(R.id.tvAboutUS)
    TextView tvAboutUs;
    @BindView(R.id.tvTermsCondition)
    TextView tvTermsCondition;
    @BindView(R.id.tvProductTitle)
    TextView tvProductTittle;
    @BindView(R.id.tvPrivacyPolicy)
    TextView tvPrivacyPolicy;
    @BindView(R.id.tvShppingpolicy)
    TextView tvShoppingPolicy;
    ArrayList list_data, list_price, list_image1;
    public HashMap<String, Integer> map = new HashMap<>();
    String[] data = {"Bubly Flavored Sparkling Water, Blackberry, 12 oz Cans (Pack of 12)\n"
            , "Bubly Flavored Sparkling Water, Peach, 12 oz Cans (Pack of 12)\n"
            , "Bubly Flavored Sparkling Water, Orange, 12 oz Cans (Pack of 12)\n"};
    String[] price1 = {"$19.99", "$19.99", "$19.99"};
    public Map AddToCartRequest = new HashMap();
    public final Integer[] imageid1 = {R.drawable.ic_tea, R.drawable.ic_halschips, R.drawable.ic_water};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ImageView nav = (ImageView) toolbar.findViewById(R.id.nav);
        appUser = LocalRepositories.getAppUser(getApplicationContext());


        if (Preferences.getInstance(this).getCounter() == 0) {

            tvCartValue.setVisibility(View.INVISIBLE);
        } else {
            tvCartValue.setText("" + Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }


        list_data = new ArrayList<>(Arrays.asList(data));
        /*slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);*/
        list_price = new ArrayList<>(Arrays.asList(price1));
        list_image1 = new ArrayList<>(Arrays.asList(imageid1));
        tvProfile.setOnClickListener(this);
        tvWishList.setOnClickListener(this);
        tvMyOrder.setOnClickListener(this);
        tvHome.setOnClickListener(this);
        tvContactUS.setOnClickListener(this);
        tvAboutUs.setOnClickListener(this);
        tvTermsCondition.setOnClickListener(this);
        tvPrivacyPolicy.setOnClickListener(this);
        tvShoppingPolicy.setOnClickListener(this);

      /*  rvProductSecond.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        rvProductSecond.setAdapter(new CartAdapter(this,list_image1,list_data,list_price,"sfjhdg"));*/
        image = new ArrayList<>();
        subscribe = new ArrayList<>();
        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);
        ivLeftOne.setOnClickListener(this);
        ivRightOne.setOnClickListener(this);
        subscribe.add(new imageData(R.drawable.subscribe1));
        image.add(new imageData(R.drawable.ic_oil));
        image.add(new imageData(R.drawable.ic_bottal));
        image.add(new imageData(R.drawable.ic_powedrink));
        image.add(new imageData(R.drawable.ic_image));
        image.add(new imageData(R.drawable.ic_ber));


        ivCart.setOnClickListener(view -> {
            if (Preferences.getInstance(this).getCounter() == 0) {
                Toast.makeText(this, "Please add items to cart.", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(LandingPageActivity.this, CartActivity.class));
            }
        });
        nav.setOnClickListener(view -> {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        });


        toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        View headerview = navigationView.getHeaderView(0);
       /* TextView user_name = (TextView) headerview.findViewById(R.id.user_name);
        if(Preferences.getInstance(getApplicationContext()).getLogin()) {
            user_name.setText("Hello " + appUser.first_name + " " + appUser.last_name);
        }else{
            user_name.setText("Hello Guest User");
        }*/

        TextView btn_signin = (TextView) navigationView.findViewById(R.id.btn_signin);
        if (Preferences.getInstance(getApplicationContext()).getLogin()) {
            btn_signin.setText("Logout");
            tvProfile.setVisibility(View.VISIBLE);
            tvWishList.setVisibility(View.VISIBLE);
            tvMyOrder.setVisibility(View.VISIBLE);
        } else {
            tvProfile.setVisibility(View.GONE);
            tvWishList.setVisibility(View.GONE);
            tvMyOrder.setVisibility(View.GONE);
            btn_signin.setText("Sign In");
        }

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_signin.getText().toString().equals("Logout")) {
                    new AlertDialog.Builder(LandingPageActivity.this)
                            .setTitle("Logout")
                            .setMessage("Are you sure you want to logout ?")
                            .setPositiveButton("Ok", (dialogInterface, i) -> {
                                Preferences.getInstance(getApplicationContext()).setLogin(false);
                                Preferences.getInstance(getApplicationContext()).setAuthToken("");
                                appUser.auth_token = "";
                                btn_signin.setText("Sign In");
                                tvProfile.setVisibility(View.GONE);
                                tvWishList.setVisibility(View.GONE);
                                tvMyOrder.setVisibility(View.GONE);
                                LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                               /* Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    finishAffinity();
                                }*/

                            })
                            .setNegativeButton("Cancel", null)
                            .show();

                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

        });


        expListView.setChildDivider(getResources().getDrawable(R.color.white));//Change the Color of the Child Divider

        //Click event occur when group child is occurs.
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int parentPosition, int childPosition, long l) {


                String child_name = listDataChild.get(listDataHeader.get(parentPosition)).get(childPosition); //To get the name of child
                Intent intent = new Intent(LandingPageActivity.this, ProductListActivity.class);
                intent.putExtra("actionbar_name", child_name);
                startActivity(intent);
                return false;
            }
        });

        //Click event occur when group child is zero.
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                int childCount = expandableListView.getExpandableListAdapter().getChildrenCount(i);
                if (childCount < 1) {
                    String parent_name = listDataHeader.get(i);//to get the name of parent
                    Intent intent = new Intent(LandingPageActivity.this, ProductListActivity.class);
                    intent.putExtra("actionbar_name", parent_name);
                    startActivity(intent);
                }
                return false;
            }
        });

/*
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                int childCount=expandableListView.getExpandableListAdapter().getChildrenCount(i);
                if(childCount<1)
                {
                    String parent_name=listDataHeader.get(i);//to get the name of parent
                    Intent intent=new Intent(LandingPageActivity.this,ProductDetailsActivity.class);
                    intent.putExtra("actionbar_name",parent_name);
                    startActivity(intent);
                }
                return false;
            }
        });



 */

        //category expandable list
        list_group = CategoryData.getData();
        list_child = new ArrayList<>(list_group.keySet());
        //expandableListAdapter = (android.widget.ExpandableListAdapter) new CategoryAdapter(LandingPageActivity.this, list_child, list_group);
        //expandableListView.setAdapter(expandableListAdapter);
        /*slidingUpPanelLayout.setScrollableView(expandableListView);*/


        appUser = LocalRepositories.getAppUser(this);

        LocalRepositories.saveAppUser(getApplicationContext(), appUser);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);


        //Horizontal recycler view
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        recyclerView.setLayoutFrozen(true);
        list_image = new ArrayList<>(Arrays.asList(imageid));
        list_text = new ArrayList<>(Arrays.asList(price));


    }


    @Override
    protected int layoutId() {
        return R.layout.activity_landing_page;
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu,menu);
        return true;
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        /*
        case R.id.icon_shoppingcart:
        addtocart();
        return true;
        */
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        map = Preferences.getInstance(this).readFromSP();
        map.clear();
        Preferences.getInstance(this).insertToSP(map);
        appUser = LocalRepositories.getAppUser(getApplicationContext());


        if (Preferences.getInstance(this).getCounter() == 0) {

            tvCartValue.setVisibility(View.INVISIBLE);
        } else {
            tvCartValue.setText("" + Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }


        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTION_GET_CATEGORY);
            ApiCallService.action(getApplicationContext(), Cv.ACTION_GET_HOME);
        } else {
            Helper.alert(LandingPageActivity.this, "No Internet Connection", "Oasis Snacks");
        }
    }

    @Subscribe
    public void getHOmeDetails(HomeResponse response) {
        if (response.getStatus() == 200) {
            webview1.loadData(response.getBody().getSection2(), "text/html", "UTF-8");
            webView2.loadData(response.getBody().getSection4(), "text/html", "UTF-8");
            tvProductTittle.setText(response.getBody().getSection3().getTitle().get(0));
            adaptersecond = new ImageAdapter(response.getBody().getSection3().getProducts(), this, new onViewPagerItemClickListner() {
                @Override
                public void onProuctClick(View view, int position) {
                    appUser.product_id = response.getBody().getSection3().getProducts().get(position).getProductId();
                    LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                    startActivity(new Intent(LandingPageActivity.this, ProductDetailsActivity.class));
                }

                @Override
                public void onProductButtonClick(View view, int position) {
                    addToCartApi();
                    appUser.CARTELEMENTS.clear();

                    CartRequest request = new CartRequest();
                    request.setCartId(Preferences.getInstance(LandingPageActivity.this).getCartId());
                    request.setProductId(response.getBody().getSection3().getProducts().get(position).getProductId());
                    request.setQty(1);
                    request.setUserId("guest");
                    appUser.request = request;

                    LocalRepositories.saveAppUser(getApplicationContext(), appUser);


                }
            });
            secondViewPager.setAdapter(adaptersecond);
            circleIndicator.createIndicators(response.getBody().getSection3().getProducts().size(), 0);
            circleIndicator.setViewPager(secondViewPager);
            rvREview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            rvREview.setAdapter(new ReviewAdapter(response.getBody().getSection5().getReviews(), this));
            rvREview.getLayoutManager().scrollToPosition(0);
            SubscribeImageAdapter madapter = new SubscribeImageAdapter(response.getBody().getSection1().getMainBanner(), this, "");
            viewPager.setAdapter(madapter);
            circleIndicatorOne.createIndicators(response.getBody().getSection1().getMainBanner().size(), 0);
            circleIndicatorOne.setViewPager(viewPager);


        }
    }

    private void addToCartApi() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTION_ADDCART);
        } else {
            Helper.alert(LandingPageActivity.this, "No Internet Connection", "Oasis Snacks");
        }


    }

    @Subscribe
    public void getCartApiDeails(AddToCartResponse addToCartResponse) {
        progressDialog.dismiss();
        if (addToCartResponse.getStatus() == 200) {
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
    public void getcategory(CategoryResponse response) {
        progressDialog.dismiss();
        list_text = new ArrayList();
        list_text.add("All Category;00");
        if (response.getStatus() == 200) {
            for (int i = 0; i < response.getCategories().size(); i++) {
                list_text.add(response.getCategories().get(i).getName() + ";" + response.getCategories().get(i).getId());
            }
            recyclerView.setAdapter(new LandingpageRecyclerAdapter(LandingPageActivity.this, list_image, list_text));

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                secondViewPager.setCurrentItem(secondViewPager.getCurrentItem() - 1);
                break;
            case R.id.ivRight:
                secondViewPager.setCurrentItem(secondViewPager.getCurrentItem() + 1);
                break;
            case R.id.ivLeftOne:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
            case R.id.ivRightOne:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
            case R.id.tvProfile:
                startActivity(new Intent(LandingPageActivity.this, ProfileActivity.class));
                break;
            case R.id.tvHome:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.tvContactUs:
                Preferences.getInstance(this).setPAgeName("faqs");
                startActivity(new Intent(LandingPageActivity.this, StaticPageActivity.class));
                break;
            case R.id.tvAboutUS:
                Preferences.getInstance(this).setPAgeName("about_us");
                startActivity(new Intent(LandingPageActivity.this, StaticPageActivity.class));
                break;
            case R.id.tvTermsCondition:
                Preferences.getInstance(this).setPAgeName("brand_fulfillment");
                startActivity(new Intent(LandingPageActivity.this, StaticPageActivity.class));
                break;
            case R.id.tvPrivacyPolicy:
                Preferences.getInstance(this).setPAgeName("privacy_policy");
                startActivity(new Intent(LandingPageActivity.this, StaticPageActivity.class));
                break;
            case R.id.tvShppingpolicy:
                Preferences.getInstance(this).setPAgeName("shipping_return_policy");
                startActivity(new Intent(LandingPageActivity.this, StaticPageActivity.class));
                break;
            case R.id.tvWishList:
                startActivity(new Intent(LandingPageActivity.this, MyWishListActivity.class));
                break;
            case R.id.tvMyOrder:
                startActivity(new Intent(LandingPageActivity.this, MyOrdersActivity.class));
                break;
            default:
                break;
        }
    }

    @Subscribe
    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg, "Error");
    }
}



