package com.example.nativeeletrichouse.domain.calcularcabo

import kotlinx.serialization.Serializable

@Serializable
data class ShowCaboCalculate(
    val tensao: Int,
    val pontecia: Double,
    val corrente: Double,
    val fatoPotencia: Double,
    val modeloInstalacaoCabos: String,
    val condutoresCarregado: Int,
    val quantDeCircuito: Int,
    val caboCalculado: Double,
    val correnteSuportadoPeloCabo:Double,
    val distancia:Double=0.0,
    val quedaTensao:Double=0.0,
    val temperatura:Double=0.0,
    val seccaoCaboTrifasico:Double=0.0
)

/*@Serializable
data class ShowCaboCalculateTrifasico(
    val tensao: Int,
    val pontecia: Double,
    val corrente: Double,
    val fatoPotencia: Double,
    val modeloInstalacaoCabos: String,
    val condutoresCarregado: Int,
    val quantDeCircuito: Int,
    val caboCalculado: Double,
    val correnteSuportadoPeloCabo:Double,
    val distancia:Double,
    val quedaTensao:Double,
    val temperatura:Double
)*/
