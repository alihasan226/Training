package com.usl.usl.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.usl.usl.R
import com.usl.usl.utils.AppUser
import com.usl.usl.utils.LocalRepositories
import java.util.*

class SplashActivity : Activity() {

    var Delay: Long = 2000
    var appUser: AppUser? = AppUser()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        appUser = LocalRepositories().getAppUser(this)
        if (appUser == null) {
            appUser = AppUser()
            LocalRepositories().saveAppUser(this, appUser)
        }
        appUser = LocalRepositories().getAppUser(this)
        val RunSplash = Timer()
        val ShowSplash: TimerTask = object : TimerTask() {
            override fun run() {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        RunSplash.schedule(ShowSplash, Delay)
    }

}