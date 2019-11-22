package com.oasissnacks.oasissnacks.acitivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.FilterAdapterNew;
import com.oasissnacks.oasissnacks.adapter.FilterTitleAdapter;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.interfce.OnFilterClickListner;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.filter.Brand;
import com.oasissnacks.oasissnacks.network.Response.filter.FilterResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends RegisterAbstractActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    View Toolbar;
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;
    @BindView(R.id.rvItem)
    RecyclerView rvItem;
    @BindView(R.id.btnApply)
    Button btnApply;

    public MyProgressDialog progressDialog;
    public AppUser appUser = new AppUser();
    public int COUNTER = 0;
    public List<Brand> brandArrayList = new ArrayList<>();

    private ArrayList<String> list = new ArrayList<>();
    public JSONObject jsonObject = new JSONObject();
    public JSONArray jsonArray = new JSONArray();
    private ArrayList<String> templist = new ArrayList<>();
    public ArrayList<String> itemList;
    HashMap<String, Integer> map = new HashMap();
    public JSONObject jsonPriceObject = new JSONObject();


    TextView tvHeading, tvEndText;
    public ArrayList<Integer> categoriesList = new ArrayList<>();
    public Context context;
    List<JSONObject> object1 = new ArrayList<>();
    List<JSONArray> JsonArrayLIst = new ArrayList<>();
    public FilterResponse filterResponse = new FilterResponse();
    ImageView ivCart;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        map=Preferences.getInstance(this).readFromSP();
        setToolBar();
        context = this;
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        filterApi();
        btnApply.setOnClickListener(this);
    }

    @Subscribe
    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg, "Error");
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_filter;
    }

    private void filterApi() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTION_FILTER);
        } else {
            Helper.alert(this, "No Internet Connection", "Oasis Snacks");
        }
    }

    public void back(View view) {
        finish();
    }

    private void setItemRecyler(List<String> list, String name) {
        rvItem.setLayoutManager(new LinearLayoutManager(FilterActivity.super.getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        FilterAdapterNew adapter = new FilterAdapterNew(list, getApplicationContext(), name, new OnFilterClickListner() {
            @Override
            public void onFilterProductClick(View view, int position) {
                itemList = new ArrayList<>();
                if (list.get(position).endsWith("true")) {
                    String sr = list.get(position).replace(";true", "");
                    if (map.containsKey(sr)) {
                        map.remove(sr);
                    } else {
                        map.put(sr, COUNTER);
                        COUNTER++;
                    }
                } else {
                    String sr = list.get(position).replace(";false", "");
                    if (map.containsKey(sr)) {
                        map.remove(sr);
                    } else {
                        map.put(sr, COUNTER);
                        COUNTER++;
                    }
                }


            }
        });
        rvItem.setAdapter(adapter);
    }

    private void setREcyler(List<String> list) {
        rvCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        FilterTitleAdapter adapterNew = new FilterTitleAdapter(list, getApplicationContext(), "First", new OnFilterClickListner() {
            @Override
            public void onFilterProductClick(View view, int position) {
                x = position;
                setItemRecyler(view, position);


            }
        });
        rvCategory.setAdapter(adapterNew);
    }

    public void setItemRecyler(View view, int position) {
        if (filterResponse.getFilters().get(position).getBrand() != null) {
            itemList = new ArrayList<>();
            for (int i = 0; i < filterResponse.getFilters().get(position).getBrand().size(); i++) {
                String sr = filterResponse.getFilters().get(position).getBrand().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getBrand().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType());
                if (map.size() > 0 && map.containsKey(sr)) {


                    itemList.add(filterResponse.getFilters().get(position).getBrand().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getBrand().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType() + ";true"));

                } else {
                    itemList.add(filterResponse.getFilters().get(position).getBrand().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getBrand().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType() + ";false"));

                }
            }
            setItemRecyler(itemList, filterResponse.getFilters().get(position).getType());


        } else if (filterResponse.getFilters().get(position).getPriceRange() != null) {
            itemList = new ArrayList<>();
            itemList.add(filterResponse.getFilters().get(position).getPriceRange().getMin() + ";" + filterResponse.getFilters().get(position).getPriceRange().getMax() + ";" + filterResponse.getFilters().get(position).getType());
            setItemRecyler(itemList, filterResponse.getFilters().get(position).getType());

        } else if (filterResponse.getFilters().get(position).getDrinkFlavor() != null) {
            itemList = new ArrayList<>();
            for (int i = 0; i < filterResponse.getFilters().get(position).getDrinkFlavor().size(); i++) {
                String sr = filterResponse.getFilters().get(position).getDrinkFlavor().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getDrinkFlavor().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType());
                if (map.size() > 0 && map.containsKey(sr)) {


                    itemList.add(filterResponse.getFilters().get(position).getDrinkFlavor().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getDrinkFlavor().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType() + ";true"));

                } else {
                    itemList.add(filterResponse.getFilters().get(position).getDrinkFlavor().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getDrinkFlavor().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType() + ";false"));

                }
            }
            setItemRecyler(itemList, filterResponse.getFilters().get(position).getType());


        } else if (filterResponse.getFilters().get(position).getSnackFlavor() != null) {
            itemList = new ArrayList<>();
            for (int i = 0; i < filterResponse.getFilters().get(position).getSnackFlavor().size(); i++) {
                String sr = filterResponse.getFilters().get(position).getSnackFlavor().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getSnackFlavor().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType());
                if (map.size() > 0 && map.containsKey(sr)) {


                    itemList.add(filterResponse.getFilters().get(position).getSnackFlavor().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getSnackFlavor().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType() + ";true"));

                } else {
                    itemList.add(filterResponse.getFilters().get(position).getSnackFlavor().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getSnackFlavor().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType() + ";false"));

                }
            }
            setItemRecyler(itemList, filterResponse.getFilters().get(position).getType());

        } else if (filterResponse.getFilters().get(position).getSpecialDiet() != null) {
            itemList = new ArrayList<>();
            for (int i = 0; i < filterResponse.getFilters().get(position).getSpecialDiet().size(); i++) {
                String sr = filterResponse.getFilters().get(position).getSpecialDiet().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getSpecialDiet().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType());
                if (map.size() > 0 && map.containsKey(sr)) {


                    itemList.add(filterResponse.getFilters().get(position).getSpecialDiet().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getSpecialDiet().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType() + ";true"));

                } else {
                    itemList.add(filterResponse.getFilters().get(position).getSpecialDiet().get(i).getValue() + ";" + String.valueOf(filterResponse.getFilters().get(position).getSpecialDiet().get(i).getOptionId() + ";" + filterResponse.getFilters().get(position).getType() + ";false"));

                }
            }
            setItemRecyler(itemList, filterResponse.getFilters().get(position).getType());

        } else if (filterResponse.getFilters().get(position).getCategories() != null) {
            itemList = new ArrayList<>();
            for (int i = 0; i < filterResponse.getFilters().get(position).getCategories().size(); i++) {
                String sr = filterResponse.getFilters().get(position).getCategories().get(i).getName() + ";" + String.valueOf(filterResponse.getFilters().get(position).getCategories().get(i).getId() + ";" + filterResponse.getFilters().get(position).getType());
                if (map.size() > 0 && map.containsKey(sr)) {


                    itemList.add(filterResponse.getFilters().get(position).getCategories().get(i).getName() + ";" + String.valueOf(filterResponse.getFilters().get(position).getCategories().get(i).getId() + ";" + filterResponse.getFilters().get(position).getType() + ";true"));

                } else {
                    itemList.add(filterResponse.getFilters().get(position).getCategories().get(i).getName() + ";" + String.valueOf(filterResponse.getFilters().get(position).getCategories().get(i).getId() + ";" + filterResponse.getFilters().get(position).getType() + ";false"));

                }
            }
            setItemRecyler(itemList, filterResponse.getFilters().get(position).getType());

        }

    }


    private void setToolBar() {
        tvHeading = Toolbar.findViewById(R.id.tvHeading);
        tvHeading.setText("Filters");
        tvEndText = Toolbar.findViewById(R.id.tvEndText);
        tvEndText.setVisibility(View.VISIBLE);
        tvEndText.setText("Clear Filters");
        tvEndText.setOnClickListener(this);
        ivCart = Toolbar.findViewById(R.id.ivCart);
        ivCart.setVisibility(View.INVISIBLE);
    }

    @Subscribe
    public void getFilter(FilterResponse response) {
        progressDialog.dismiss();

        filterResponse = response;


        for (int i = 0; i < response.getFilters().size(); i++) {

            list.add(response.getFilters().get(i).getType());
        }
        setREcyler(list);
        ArrayList<String> itemList = new ArrayList<>();

        itemList.add(filterResponse.getFilters().get(0).getPriceRange().getMin() + ";" + filterResponse.getFilters().get(0).getPriceRange().getMax() + ";" + filterResponse.getFilters().get(0).getType());

        setItemRecyler(itemList, "Price Range");


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnApply:
                for (Map.Entry mapElement : map.entrySet()) {
                    String key = (String) mapElement.getKey();
                    if (key.split(";")[2].equalsIgnoreCase("categories")) {
                        categoriesList.add(Integer.parseInt(key.split(";")[1]));
                        appUser.FilerElements.put("categories", categoriesList);
                        LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                    } else {
                        Brand brand = new Brand();
                        brand.setOptionId(key.split(";")[1]);
                        if (key.split(";")[2].equalsIgnoreCase("Drink Flavor")){
                            brand.setAttributeCode("flavor");
                        }else if(key.split(";")[2].equalsIgnoreCase("Special Diet")){
                            brand.setAttributeCode("special_diet");
                        }else if(key.split(";")[2].equalsIgnoreCase("Brand")){
                            brand.setAttributeCode("brand");
                        }else if(key.split(";")[2].equalsIgnoreCase("Snack Flavor")){
                            brand.setAttributeCode("snacks_flavor");
                        }else {

                            brand.setAttributeCode(key.split(";")[2]);
                        }
                        brandArrayList.add(brand);
                        appUser.FilerElements.put("attributes", brandArrayList);
                        appUser.filter.put("filters", appUser.FilerElements);
                        LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                    }
                }
                appUser.filter.clear();
                appUser.filter.put("category_id", Preferences.getInstance(this).getCatID());
                appUser.filter.put("page", appUser.page_no);
                HashMap<String, String> range = new HashMap();
                range.put("min", Preferences.getInstance(this).getPriceMInvAlue());
                range.put("max", Preferences.getInstance(this).getPriceMaxvalue());
                appUser.FilerElements.put("Price Range", range);
                appUser.filter.put("filters", appUser.FilerElements);
                Preferences.getInstance(this).insertToSP(map);
                LocalRepositories.saveAppUser(this, appUser);
                finish();
                break;
            case R.id.tvEndText:
                map.clear();
                map = new HashMap<>();
                setItemRecyler(view, x);
                break;

            default:
                break;
        }
    }
}
