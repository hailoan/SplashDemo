package com.lx.myapplication.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.NonNull
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.lx.myapplication.R
import com.lx.myapplication.utils.Cache
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private val TIME_DELAY: Long = 1000
    private lateinit var cache: Cache
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
                        or WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
                        or WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        cache = Cache(baseContext)
        handleFirstStartApp()
    }

    private fun handleFirstStartApp() {
        if (cache.getBoolean(cache.IS_FIRST_START_APP.first)) {
            showIntroduction()
            Log.e("D", "is first start app")
        } else {
            showSplash()
            Log.e("D", "is't first start app")
        }
    }

    private fun showSplash() {
        img_splash.visibility = View.VISIBLE
        img_introdu.visibility = View.GONE
        tv_skip.visibility = View.GONE
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                lauchActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }, TIME_DELAY)
    }

    private fun showIntroduction() {
        img_splash.visibility = View.GONE
        img_introdu.visibility = View.VISIBLE
        tv_skip.visibility = View.VISIBLE
        tv_skip.setOnClickListener {
            cache.putBoolean(cache.IS_FIRST_START_APP.first, false)
            lauchActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
    }

    fun AppCompatActivity.lauchActivity(@NonNull intent: Intent) {
        startActivity(intent)
        finish()
    }


}
