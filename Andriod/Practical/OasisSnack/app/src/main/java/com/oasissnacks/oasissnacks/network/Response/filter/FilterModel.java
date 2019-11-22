package com.oasissnacks.oasissnacks.network.Response.filter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oasissnacks.oasissnacks.network.Response.category.Categories;

import java.util.List;

public class FilterModel {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("Price Range")
    @Expose
    private PriceRange priceRange;
    @SerializedName("Snack Flavor")
    @Expose
    private List<SnackFlavor> snackFlavor = null;
    @SerializedName("Special Diet")
    @Expose
    private List<Brand> specialDiet = null;
    @SerializedName("Brand")
    @Expose
    private List<Brand> brand = null;
    @SerializedName("Categories")
    @Expose
    private List<Categories> categories = null;

    @SerializedName("Drink Flavor")
    @Expose
    private List<Brand> drinkFlavor = null;

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Brand> getDrinkFlavor() {
        return drinkFlavor;
    }

    public void setDrinkFlavor(List<Brand> drinkFlavor) {
        this.drinkFlavor = drinkFlavor;
    }

    public List<Brand> getSpecialDiet() {
        return specialDiet;
    }

    public void setSpecialDiet(List<Brand> specialDiet) {
        this.specialDiet = specialDiet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PriceRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }

    public List<SnackFlavor> getSnackFlavor() {
        return snackFlavor;
    }

    public void setSnackFlavor(List<SnackFlavor> snackFlavor) {
        this.snackFlavor = snackFlavor;
    }

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }
}
