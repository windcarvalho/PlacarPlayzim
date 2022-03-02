package com.playzim.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.playzim.activities.placar.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickBtMatch(v: View){
//        val it_match = Intent(this, Playzim_Score_Activity::class.java).apply{}
//        startActivity(it_match)
        val it_match = Intent(this, ConfigActivity::class.java).apply{}
        startActivity(it_match);

    }
}