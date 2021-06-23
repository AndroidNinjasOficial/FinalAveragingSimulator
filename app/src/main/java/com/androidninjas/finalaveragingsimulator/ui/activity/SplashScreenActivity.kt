package com.androidninjas.finalaveragingsimulator.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.androidninjas.finalaveragingsimulator.R

class SplashScreenActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler.postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }, 3000)
    }
}