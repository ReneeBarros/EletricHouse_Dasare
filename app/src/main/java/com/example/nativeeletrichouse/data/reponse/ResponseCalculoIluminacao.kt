package com.example.nativeeletrichouse.data.reponse

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ResponseCalculoIluminacao(
    val ambiente: String,
    val amperagemCircuito: Double,
    val area: String,
    val comprimento: Double,
    val largura: Double,
    val lumensAmbiente: Int,
    val lumensLuminaria: Int,
    val lumensTotal: Double,
    val potenciaLuminaria: Double,
    @JsonNames("potenciaTotalIlumDto")
    val potenciaTotal: Double,
    val tensao: Int,
    val totalLuminaria: Double
)