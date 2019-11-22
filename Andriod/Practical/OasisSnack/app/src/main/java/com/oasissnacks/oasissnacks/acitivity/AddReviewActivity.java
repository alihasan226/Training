package com.oasissnacks.oasissnacks.acitivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.CustomEditText;
import com.oasissnacks.oasissnacks.utils.CustomTextView;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddReviewActivity extends RegisterAbstractActivity implements View.OnClickListener {
    @BindView(R.id.btnSubmitReview)
    CustomTextView btnSubmitReview;
    @BindView(R.id.toolbar)
    View ToolBar;
    @BindView(R.id.etNickName)
    CustomEditText etNickName;
    @BindView(R.id.etSummary)
    EditText etSummary;
    @BindView(R.id.etReview)
    EditText etReview;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tvProductName)
    CustomTextView tvProductName;
    public AppUser appUser=new AppUser();
    TextView tvHeading,tvCartValue;
    public MyProgressDialog progressDialog;
    ImageView ivCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_review);
        ButterKnife.bind(this);
        Bundle bundle=getIntent().getExtras();
        tvProductName.setText(bundle.getString("product_name"));
        ratingBar=findViewById(R.id.ratingBar);
        setToolBar();
    }
    private void setToolBar() {
        tvHeading = ToolBar.findViewById(R.id.tvHeading);
        tvHeading.setText("Add Review");
        tvCartValue = ToolBar.findViewById(R.id.tvCartValue);
        tvCartValue.setVisibility(View.INVISIBLE);
        ivCart= ToolBar.findViewById(R.id.ivCart);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        ivCart.setVisibility(View.INVISIBLE);
        btnSubmitReview.setOnClickListener(this);
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_submit_review;
    }
    public void back(View view){
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSubmitReview:
                if (checkValiation()){
                    appUser.ADDREVIEW.put("product_id",Preferences.getInstance(this).getProductID());
                    appUser.ADDREVIEW.put("nick_name",etNickName.getText().toString());
                    appUser.ADDREVIEW.put("title",etReview.getText().toString());
                    appUser.ADDREVIEW.put("detail",etSummary.getText().toString());
                    appUser.ADDREVIEW.put("rating",ratingBar.getNumStars());
                    LocalRepositories.saveAppUser(getApplicationContext(),appUser);
                    apiSubmitReview();
                }
                break;
                default:break;
        }

    }

    private void apiSubmitReview() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.POSTREVIEW);

        } else {
            Helper.alert(getApplicationContext(), "No Internet Connection", "Oasis Snacks");
        }
    }
    @Subscribe
    public void getREviewDetailsApi(UserResponse response){
        progressDialog.dismiss();
        if (response.getStatus()==200){
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean checkValiation()
    {
        if(TextUtils.isEmpty(etNickName.getText())){
            etNickName.setError("Required Field");
            etNickName.requestFocus();
            return false;
        }
        else if(TextUtils.isDigitsOnly(etNickName.getText())){
            etNickName.setError("Invalid Nick Name");
            etNickName.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(etSummary.getText())){
            etSummary.setError("Required Field");
            etSummary.requestFocus();
            return false;
        }
        else if(TextUtils.isDigitsOnly(etSummary.getText())){
            etSummary.setError("Invalid Summary");
            etSummary.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(etReview.getText())){
            etReview.setError("Required Field");
            etReview.requestFocus();
            return false;
        }
        else if(TextUtils.isDigitsOnly(etReview.getText())){
            etReview.setError("Invalid Review");
            etReview.requestFocus();
            return false;
        }
        else if(ratingBar.getRating()==0.0){
            Toast.makeText(this,"Please Give your Rating",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
