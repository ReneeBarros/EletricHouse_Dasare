package com.example.nativeeletrichouse.presation.iluminacao


data class CalcularIluminacaoUiState(
    val title: String = "CalcularIluminacao",
    val width:String= "",
    val length:String= "",
    val area:Double = 0.0,
    val voltage:Int = 0,
    val lampPower:String= "",
    val roomType:String ="",
    val roomName:String =""
)