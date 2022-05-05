package com.team1.teamone.util.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.team1.teamone.R
import com.team1.teamone.member.view.FindIdActivity
import com.team1.teamone.member.view.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
            finish()
        }, 3000)

    }
}