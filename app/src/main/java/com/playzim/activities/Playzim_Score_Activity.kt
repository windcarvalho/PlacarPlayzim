package com.playzim.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.playzim.activities.placar.R
import com.playzim.placarbt.*


class Playzim_Score_Activity : AppCompatActivity(), Animation.AnimationListener {
   var game: GameBT? = null
    lateinit var giraBola: Animation
    lateinit var scaleStatus: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playzim_score)
        game= getIntent().getExtras()?.getSerializable("GAME") as GameBT?
        initAnimation()
        initGameConf()
        startGameCount()

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("placar", "Salvando o jogo")
        outState.putSerializable("GAME", game)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("placar", "Recuperando o jogo")
        game = savedInstanceState.get("GAME") as GameBT?
    }

    @SuppressLint("ResourceType")
    fun initAnimation() {
        // load the animation
        giraBola = AnimationUtils.loadAnimation(
            applicationContext,
            R.animator.gira_imageview
        )
        giraBola.setAnimationListener(this)
        scaleStatus = AnimationUtils.loadAnimation(
            applicationContext,
            R.animator.aumenta_textview
        )
        scaleStatus.setAnimationListener(this)
    }

    fun startGameCount() {
        var simpleChronometer:Chronometer = findViewById <Chronometer> (R.id.tvTimer); // initiate a chronometer
        simpleChronometer.start(); // start a chronometer
    }
    fun stopGameCount() {
        var simpleChronometer:Chronometer = findViewById <Chronometer> (R.id.tvTimer); // initiate a chronometer
        simpleChronometer.stop(); // start a chronometer
    }
    fun onClickUndo(v: View){
        game?.undo()
        update_score_screen()
    }
   fun initGameConf(){
       if (game==null) {
           game = GameBT("Amistoso","Time A", "Time B", 3, 4, 7, true, 10, TeamSide.TEAM_A)
       }

       val description = findViewById<TextView>(R.id.tvDescGame)
       description.text=game?.descricao
       // Team Names
       val tvTeamA = findViewById<TextView>(R.id.tvTeamA)
       tvTeamA.text=game?.teamA
       val tvTeamB = findViewById<TextView>(R.id.tvTeamB)
       tvTeamB.text=game?.teamB

       // Who starts?
       val tbStatus = findViewById<TextView>(R.id.tbStatus)
       val imgTeamA:ImageView = findViewById<ImageView>(R.id.imageViewTeamA)
       val imgTeamB:ImageView = findViewById<ImageView>(R.id.imageViewTeamB)
       if (game?.currentServer ==TeamSide.TEAM_A) {
//           tbStatus.text = "Saque: " + game?.teamA
           imgTeamA.visibility=ImageView.VISIBLE
           imgTeamB.visibility=ImageView.INVISIBLE
           imgTeamA.startAnimation(giraBola)
       }else{
//           tbStatus.text = "Saque: " + game?.teamB
           imgTeamB.visibility=ImageView.VISIBLE
           imgTeamA.visibility=ImageView.INVISIBLE
           imgTeamB.startAnimation(giraBola)
       }

       if (game?.maxSets == 1){
           val tvSetLabelA = findViewById<TextView>(R.id.tvSetLabelA)
           val tvSetLabelB = findViewById<TextView>(R.id.tvSetLabelB)
           val tvSetA = findViewById<TextView>(R.id.tvSetA)
           val tvSetB = findViewById<TextView>(R.id.tvSetB)
           tvSetA.visibility=View.INVISIBLE
           tvSetB.visibility=View.INVISIBLE
           tvSetLabelB.visibility=View.INVISIBLE
           tvSetLabelA.visibility=View.INVISIBLE
       }
   }
    fun onClickBtTeamA(v: View){
        game?.score(TeamSide.TEAM_A)
        update_score_screen()
    }
    fun onClickBtTeamB(v: View){
        game?.score(TeamSide.TEAM_B)
        update_score_screen()
    }

    private fun update_score_screen() {

        //update points score
        val btTeamA = findViewById<TextView>(R.id.btTeamA)
        var pontTeam=game?.points?.get(0)
        if (pontTeam!! < 10) {
            btTeamA.text = "0"+pontTeam
        }else{
            btTeamA.text=""+pontTeam
        }

        //if ((btTeamA.text as String).equals("0",true)){
          //  btTeamA.text="00"
        //}

        val btTeamB = findViewById<TextView>(R.id.btTeamB)
        pontTeam=game?.points?.get(1)
        if (pontTeam!! < 10) {
            btTeamB.text = "0"+pontTeam
        }else{
            btTeamB.text=""+pontTeam
        }


        //update games
        val tvGamesTeamA = findViewById<TextView>(R.id.tvGameTeamA)
        tvGamesTeamA.text= "0"+game?.games?.get(0).toString()


        val tvGamesTeamB = findViewById<TextView>(R.id.tvGameTeamB)
        tvGamesTeamB.text= "0"+game?.games?.get(1).toString()

        //update game set
        val tvSetsTeamA = findViewById<TextView>(R.id.tvSetA)
        tvSetsTeamA.text= "0"+game?.sets?.get(0).toString()

        val tvSetsTeamB = findViewById<TextView>(R.id.tvSetB)
        tvSetsTeamB.text= "0"+game?.sets?.get(1).toString()

        //update game status
        val tbStatus = findViewById<TextView>(R.id.tbStatus)
        if (game?.isGameHasFinished() == true){
            tbStatus.text = "  Jogo Finalizado  "
            tbStatus.startAnimation(scaleStatus)
            stopGameCount() //stoping the Chronometer
        }else{
            val imgTeamA:ImageView = findViewById<ImageView>(R.id.imageViewTeamA)
            val imgTeamB:ImageView = findViewById<ImageView>(R.id.imageViewTeamB)
            if (game?.currentServer ==TeamSide.TEAM_A) {
                imgTeamA.visibility=ImageView.VISIBLE
                imgTeamB.visibility=ImageView.INVISIBLE
                imgTeamB.clearAnimation()
                imgTeamA.startAnimation(giraBola)
            }else{
                imgTeamB.visibility=ImageView.VISIBLE
                imgTeamA.visibility=ImageView.INVISIBLE
                imgTeamA.clearAnimation()
                imgTeamB.startAnimation(giraBola)
            }
            checkChangeSides()
        }
    }

    fun checkChangeSides(){
        val tbStatus = findViewById<TextView>(R.id.tbStatus)
        var scoreManager = game?.scoreManager
        tbStatus.text = ""
        if (scoreManager is NormalScore){
            if ((((game!!.games[0] + game!!.games[1]) % 2) == 1) && ((game!!.points[0] + game!!.points[1]) == 0)){
                tbStatus.text = "  Trocar de lados  "
                tbStatus.startAnimation(scaleStatus)
            }
        } else if (((game!!.points[0] + game!!.points[1])  != 0) && (((game!!.points[0] + game!!.points[1])  == 1) || (((game!!.points[0] + game!!.points[1]) %4) ==0))){
            tbStatus.text = "  Trocar de lados  "
            tbStatus.startAnimation(scaleStatus)
        }
    }

    override fun onAnimationStart(animation: Animation?) {
        if (animation ==giraBola) {
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