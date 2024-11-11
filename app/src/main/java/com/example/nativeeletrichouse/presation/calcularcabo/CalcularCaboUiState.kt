package com.example.nativeeletrichouse.presation.calcularcabo

import com.example.nativeeletrichouse.domain.calcularcabo.ShowCaboCalculate


data class CalcularCaboUiState(
    val title: String = "CalcularCabo",
    val pontecia:String="0.0",
    val tensao:Int=0,
    val corrente:String="0.0",
    val fatoPotencia:Double=0.0,
    val modeloInstalacaoCabos:String="",
    val condutoresCarregado:String="",
    val quantDeCircuito:String="Quantos Circuito?",
    val listCabo:MutableList<ShowCaboCalculate> = mutableListOf()

)