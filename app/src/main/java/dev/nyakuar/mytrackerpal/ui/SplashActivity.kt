package dev.nyakuar.mytrackerpal.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPrefs:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.SplashActivity)
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

        var accesToken =sharedPrefs.getString("ACCESS_TOKEN","").toString()
        if (accesToken.isBlank()){
            val intent =Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent =Intent(this, HomeActivity::class.java)
            startActivity(intent)

        }
       finish()
    }
}
