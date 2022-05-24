package com.playzim.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.getSystemService
import com.playzim.activities.placar.R
import com.playzim.placarbt.TeamSide

class MainActivity : AppCompatActivity() , Animation.AnimationListener {

    lateinit var giraBola: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAnimation()
    }

    fun onClickBtYoutube(v: View){
        val it_Youtube = Intent(android.content.Intent.ACTION_VIEW)
        it_Youtube.data= Uri.parse("https://www.youtube.com/c/PlayzimBeachTennis")
        startActivity(it_Youtube);

    }

    fun onClickBtMatch(v: View){
//        val it_match = Intent(this, Playzim_Score_Activity::class.java).apply{}
//        startActivity(it_match)
        val it_match = Intent(this, ConfigActivity::class.java).apply{}
        startActivity(it_match);

    }
    fun onClickBtPrevious(v: View){

        val it_match = Intent(this, PreviousGamesActivity::class.java).apply{}
        startActivity(it_match);

        /*val buzzer = this.getSystemService<Vibrator>()
        val pattern = longArrayOf(0, 200, 100, 300)
        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }*/

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