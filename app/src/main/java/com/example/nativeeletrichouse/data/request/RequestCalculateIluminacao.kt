package com.example.nativeeletrichouse.data.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestCalculateIluminacao(
    val ambiente: String,
    val comprimento: Double,
    val largura: Double,
    val potenciaLumianria: Double,
    val tensao: Int
)