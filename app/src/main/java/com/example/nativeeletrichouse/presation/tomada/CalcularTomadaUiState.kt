package com.example.nativeeletrichouse.presation.tomada


data class CalcularTomadaUiState(
    val title: String = "Calcular Tomada",
    val width:String= "",
    val length:String= "",
    val perimetro:Double = 0.0,
    val voltage:Int = 0,
    val roomType:String ="",
    val roomName:String =""
)