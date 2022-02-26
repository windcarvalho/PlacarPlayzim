package com.playzim.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.TextView
import com.playzim.activities.placar.R
import com.playzim.placarbt.GameBT
import com.playzim.placarbt.*


class Playzim_Score_Activity : AppCompatActivity() {
   var game: GameBT? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playzim_score)
        initGameConf()
        startGameCount()
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
       game = GameBT("Trinta/Paulo","Marcio/Pordeus",3,4,7,true,10,TeamSide.TEAM_A)

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
           tbStatus.text = "Saque: " + game?.teamA
           imgTeamA.visibility=ImageView.VISIBLE
           imgTeamB.visibility=ImageView.INVISIBLE
       }else{
           tbStatus.text = "Saque: " + game?.teamB
           imgTeamB.visibility=ImageView.VISIBLE
           imgTeamA.visibility=ImageView.INVISIBLE
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
        btTeamA.text= game?.points?.get(0).toString()
        if ((btTeamA.text as String).equals("0",true)){
            btTeamA.text="00"
        }

        val btTeamB = findViewById<TextView>(R.id.btTeamB)
        btTeamB.text= game?.points?.get(1).toString()
        if ((btTeamB.text as String).equals("0",true)){
            btTeamB.text="00"
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
            tbStatus.text = "Jogo Finalizado"
            stopGameCount() //stoping the Chronometer
        }else{
            val imgTeamA:ImageView = findViewById<ImageView>(R.id.imageViewTeamA)
            val imgTeamB:ImageView = findViewById<ImageView>(R.id.imageViewTeamB)
            if (game?.currentServer ==TeamSide.TEAM_A) {
                tbStatus.text = "Saque: " + game?.teamA
                imgTeamA.visibility=ImageView.VISIBLE
                imgTeamB.visibility=ImageView.INVISIBLE
            }else{
                tbStatus.text = "Saque: " + game?.teamB
                imgTeamB.visibility=ImageView.VISIBLE
                imgTeamA.visibility=ImageView.INVISIBLE
            }

        }



    }
}