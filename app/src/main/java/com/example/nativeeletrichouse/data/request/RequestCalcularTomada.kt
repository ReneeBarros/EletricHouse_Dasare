package com.example.nativeeletrichouse.data.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestCalcularTomada(
    val ambiente: String,
    val comprimento: Double,
    val largura: Double,
    val tensao: Int
)