package com.playzim.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.android.material.textfield.TextInputEditText
import com.playzim.activities.placar.R
import com.playzim.placarbt.GameBT
import com.playzim.placarbt.TeamSide

class ConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
    }

    fun onClickBtMatch(v: View) {
//        val it_match = Intent(this, Playzim_Score_Activity::class.java).apply{}
//        startActivity(it_match)

        var game: GameBT? = null

        var descricao = findViewById<TextInputEditText>(R.id.descricao).text.toString()
//        var descricao = "Amistoso"
        var time1 = findViewById<TextInputEditText>(R.id.time1).text.toString()
        var time2 = findViewById<TextInputEditText>(R.id.time2).text.toString()

        var primeiroSaqueRG: RadioGroup = findViewById<RadioGroup>(R.id.primeiroSaque)
        val primeiroSaqueId: Int = primeiroSaqueRG.checkedRadioButtonId
        var primeiroSaque = TeamSide.TEAM_A
        if (primeiroSaqueId != -1) {
            var selectedPrimeiroSaque: RadioButton = findViewById(primeiroSaqueId)
            var primeiroSaqueString = selectedPrimeiroSaque.text.toString()
            if (primeiroSaqueString == "Time2") {
                primeiroSaque = TeamSide.TEAM_B
            }
        }


        var maxSetsRG: RadioGroup = findViewById<RadioGroup>(R.id.qtdSets)
        val selectedMaxSetsId: Int = maxSetsRG.checkedRadioButtonId
        var maxSets: Int = 1
        if (selectedMaxSetsId != -1) {
            var selectedMaxSets: RadioButton = findViewById(selectedMaxSetsId)
            var maxSetString = selectedMaxSets.text.toString()
            if (maxSetString != "") {
                maxSets = maxSetString.toInt()
            } else {
                maxSets = findViewById<TextInputEditText>(R.id.qtdSetsPersonalizado).text.toString()
                    .toInt()
            }
        }

        var maxGamesRG: RadioGroup = findViewById<RadioGroup>(R.id.qtdGames)
        val selectedMaxGamesId: Int = maxGamesRG.checkedRadioButtonId
        var maxGames: Int = 1
        if (selectedMaxGamesId != -1) {
            var selectedMaxGames: RadioButton = findViewById(selectedMaxGamesId)
            var maxGamesString = selectedMaxGames.text.toString()
            if (maxGamesString != "") {
                maxGames = maxGamesString.toInt()
            } else {
                maxGames =
                    findViewById<TextInputEditText>(R.id.qtdGamesPersonalizado).text.toString()
                        .toInt()
            }
        }

        var maxPontosTieRG: RadioGroup = findViewById<RadioGroup>(R.id.ptsTieBrake)
        val selectedMaxPontosTieId: Int = maxPontosTieRG.checkedRadioButtonId
        var maxPontosTie: Int = 1
        if (selectedMaxPontosTieId != -1) {
            var selectedMaxPontosTie: RadioButton = findViewById(selectedMaxPontosTieId)
            var maxPontosTieString = selectedMaxPontosTie.text.toString()
            if (maxPontosTieString != "") {
                maxPontosTie = maxPontosTieString.toInt()
            } else {
                maxPontosTie =
                    findViewById<TextInputEditText>(R.id.qtdPtsTiePersonalizado).text.toString()
                        .toInt()
            }
        }

        val temSuperTie = findViewById<CheckBox>(R.id.supertie)
        var supertie = false
        if (temSuperTie.isChecked) {
            supertie = true
        }

        var maxPontosSuperTieRG: RadioGroup = findViewById<RadioGroup>(R.id.pontosSuperTie)
        val selectedMaxPontosSuperTieId: Int = maxPontosSuperTieRG.checkedRadioButtonId
        var maxPontosSuperTie: Int = 1
        if (selectedMaxPontosSuperTieId != -1) {
            var selectedMaxPontosSuperTie: RadioButton = findViewById(selectedMaxPontosSuperTieId)
            var maxPontosSuperTieString = selectedMaxPontosSuperTie.text.toString()
            if (maxPontosSuperTieString != "") {
                maxPontosSuperTie = maxPontosSuperTieString.toInt()
            } else {
                maxPontosSuperTie =
                    findViewById<TextInputEditText>(R.id.qtdPtsSuperTiePersonalizado).text.toString()
                        .toInt()
            }
        }


        game = GameBT(
            descricao,
            time1,
            time2,
            maxSets,
            maxGames,
            maxPontosTie,
            supertie,
            maxPontosSuperTie,
            primeiroSaque
        )
        val it_match = Intent(this, Playzim_Score_Activity::class.java).apply {
            putExtra("GAME", game);
        }
        startActivity(it_match);

    }
}