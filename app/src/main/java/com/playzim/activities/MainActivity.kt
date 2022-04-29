package com.playzim.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.playzim.activities.placar.R
import com.playzim.placarbt.TeamSide

class MainActivity : AppCompatActivity() , Animation.AnimationListener {

    lateinit var giraBola: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAnimation()
    }

    fun onClickBtMatch(v: View){
//        val it_match = Intent(this, Playzim_Score_Activity::class.java).apply{}
//        startActivity(it_match)
        val it_match = Intent(this, ConfigActivity::class.java).apply{}
        startActivity(it_match);

    }


    @SuppressLint("ResourceType")
    fun initAnimation() {
        // load the animation
        giraBola = AnimationUtils.loadAnimation(
            applicationContext,
            R.animator.gira_imageview
        )
        giraBola.setAnimationListener(this)
        val imgTeamA: ImageView = findViewById<ImageView>(R.id.imageInitTeamA)
        val imgTeamB: ImageView = findViewById<ImageView>(R.id.imageInitTeamB)
        imgTeamA.startAnimation(giraBola)
        imgTeamB.startAnimation(giraBola)
    }
    override fun onAnimationStart(animation: Animation?) {
        if (animation == giraBola) {
            Log.v("pdm","animacao começando")
        }
    }

    override fun onAnimationEnd(animation: Animation?) {
        Log.v("pdm","animacao terminando")
    }

    override fun onAnimationRepeat(animation: Animation?) {
        Log.v("pdm","animacao repetindo")
    }
}