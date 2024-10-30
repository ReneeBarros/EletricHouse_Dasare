package com.example.nativeeletrichouse.data.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestCalcularArCond(
    val comprimento: Double,
    val insidenciaRaioSolar: Boolean,
    val largura: Double,
    val quantEletrodomestico: Int,
    val quantPessoasAmbiente: Int,
    val tensao: Int
)