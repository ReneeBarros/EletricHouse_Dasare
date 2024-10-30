package com.example.nativeeletrichouse.data.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestCalculateAmbiente(
    val ambiente: String,
    val nomeAmbiente:String,
    val comprimento: Double,
    val insidenciaRaioSolarV1: Boolean,
    val largura: Double,
    val potenciaLuminaria: Double,
    val quantEletrodomestico: Int,
    val quantPessoasAmbiente: Int,
    val tensao: Int
)