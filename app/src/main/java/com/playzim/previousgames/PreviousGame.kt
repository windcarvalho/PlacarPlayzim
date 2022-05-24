package com.playzim.previousgames

import java.io.Serializable

data class PreviousGame(var nome_partida:String, var resultado:String, var has_timer:Boolean):Serializable
