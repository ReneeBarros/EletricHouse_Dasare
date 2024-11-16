package com.example.nativeeletrichouse.presation.arcondicionado


data class CalcularArCondicionadoUiState(
    val title: String = "CalcularArCondicionado",
    val width:String= "",
    val length:String= "",
    val area:Double = 0.0,
    val voltage:Int = 0,
    val numberOfPeopleInRoom:String = "",
    val numberOfAppliances:String ="",
    val solarIncidence:Boolean = false,
    val roomType:String ="",
    val roomName:String =""
)