package com.oasissnacks.oasissnacks.acitivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.statcresponse.StaticPageResponse;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StaticPageActivity extends RegisterAbstractActivity {
    @BindView(R.id.webViewStatic)
    WebView webView;
    @BindView(R.id.toolbar)
    View toolbar;
    TextView tvHeading;
    RelativeLayout rlCart;
    public MyProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_page);
        ButterKnife.bind(this);
        tvHeading=toolbar.findViewById(R.id.tvHeading);
        rlCart=toolbar.findViewById(R.id.rlCart);
        rlCart.setVisibility(View.GONE);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        getContentAPi();

    }
    @Subscribe
    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg, "Error");
    }

    private void getContentAPi() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTIONSTATICPAGE);
        } else {
            Helper.alert(this, "No Internet Connection", "Oasis Snacks");
        }


    }
    public void back(View view){
        finish();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_static_page;
    }

    @Subscribe
    public void getStaticResponse(StaticPageResponse response) {
        progressDialog.dismiss();
        if (response.getStatus() == 200) {
            tvHeading.setText(response.getTitle());
            webView.loadData(response.getContent(), "text/html", "UTF-8");
        } else {
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
        }
    }
}
