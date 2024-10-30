package com.example.nativeeletrichouse.data.reponse

import kotlinx.serialization.Serializable

@Serializable
data class ResponseCalculoTomada(
    val ambiente: String,
    val amperagemTomada: Double,
    val potenciaTotal: Double,
    val quantTomada: Int
)