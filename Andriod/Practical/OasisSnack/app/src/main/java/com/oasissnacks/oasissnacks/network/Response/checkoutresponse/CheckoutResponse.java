package com.oasissnacks.oasissnacks.network.Response.checkoutresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckoutResponse {

    @SerializedName("currency_id")
    @Expose
    private String currencyId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("cart_id")
    @Expose
    private Integer quoteId;
    @SerializedName("save_in_address_book")
    @Expose
    private Integer saveInAddressBook;
    @SerializedName("shipping_address")
    @Expose
    private BillingAddressResponse shippingAddress;
    @SerializedName("billing_address")
    @Expose
    private BillingAddressResponse billingAddress;

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public Integer getSaveInAddressBook() {
        return saveInAddressBook;
    }

    public void setSaveInAddressBook(Integer saveInAddressBook) {
        this.saveInAddressBook = saveInAddressBook;
    }

    public BillingAddressResponse getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(BillingAddressResponse shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BillingAddressResponse getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddressResponse billingAddress) {
        this.billingAddress = billingAddress;
    }
}
