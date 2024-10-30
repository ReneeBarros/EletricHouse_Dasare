package com.example.nativeeletrichouse.data.reponse

import kotlinx.serialization.Serializable

@Serializable
data class ResponseCalculoArCond(
    val IDRS: Double,
    val amperagemCircuitoAc: String,
    val btuAdicionalInsidenciaRaioSolar: Int,
    val btuAdicionalPorEletronico: Int,
    val btuAdicionalPorPessoa: Int,
    val btuPorM2: Int,
    val btusTotal: Int,
    val potenciaEletria: String,
    val quantEletrodomestico: Int,
    val quantPessoasAmbiente: Int
)