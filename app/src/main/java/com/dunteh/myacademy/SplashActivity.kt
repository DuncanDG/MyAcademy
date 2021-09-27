package com.dunteh.myacademy

import android.content.Intent
import android.os.Build
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

// This is a splash screen and the beginning of the app

class SplashActivity : AppCompatActivity() {

    //This is the loading time of the splash screen
    private val SPLASH_TIME_OUT: Long = 2000 //2secs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        //changing the color of the status bar
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.purple_500)
        }

        //Handler - allows you to send and process message and runnable objects associated with a thread's messageQueue
        //messages are not added directly to a messagequeue but rather through a handler

        Handler().postDelayed({
            //This method will be executed once the timer is over

            //start your next activity
            startActivity(Intent(this, MainActivity::class.java))

            //close this activity
            finish()
        }, SPLASH_TIME_OUT)

       }
    }