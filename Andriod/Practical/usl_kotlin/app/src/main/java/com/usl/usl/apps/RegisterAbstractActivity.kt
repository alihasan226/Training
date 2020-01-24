package com.usl.usl.apps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import org.greenrobot.eventbus.EventBus

abstract class RegisterAbstractActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        ButterKnife.bind(this)
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    protected abstract fun layoutId(): Int

    override fun onPause() {
        EventBus.getDefault().unregister(this)
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

}