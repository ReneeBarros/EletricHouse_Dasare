package com.example.nativeeletrichouse.data.reponse

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class ResponseCalculoTomada(
    val ambiente: String,
    val amperagemTomada: Double,
    @JsonNames("potenciaTotalTomDTO")
    val potenciaTotal: Double,
    val quantTomada: Int
)