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
    val correnteSuportadoPeloCabo:Double
)
