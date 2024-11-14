package com.example.nativeeletrichouse.data.reponse

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ResponseCalculoIluminacao(
    val ambiente: String="",
    val nomeAmbiente: String ="",
    val amperagemCircuito: Float=0.0f,
    val area: String="0.0",
    val comprimento: Float=0.0f,
    val largura: Float=0.0f,
    val lumensAmbiente: Int=0,
    val lumensLuminaria: Int=0,
    val lumensTotal: Float=0.0f,
    val potenciaLuminaria: Float=0.0f,
    @JsonNames("potenciaTotalIlumDto")
    val potenciaTotal: Float=0.0f,
    val tensao: Int=0,
    val totalLuminaria: Float=0.0f
)