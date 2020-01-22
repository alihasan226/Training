package com.usl.usl.activity

import android.os.Bundle
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.usl.usl.R
import com.usl.usl.apps.RegisterAbstractActivity

class LandingPageActivity : RegisterAbstractActivity() {


    @BindView(R.id.toolbar)
    lateinit var toolar: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)

    }

    override fun layoutId(): Int {
        return R.layout.activity_landing_page
    }
}