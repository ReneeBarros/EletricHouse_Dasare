package com.example.nativeeletrichouse.presation.home


data class HomeUiState(
    val title: String = "Home",
    val iluminacao:Boolean =false,
    val tomada:Boolean =false,
    val arCondicionado:Boolean =false,
    val calculo:Boolean =false,
    val ambiente:String="Escolher Ambiente",
    val nomeObra:String = ""

    )