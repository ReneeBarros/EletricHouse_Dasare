package com.example.nativeeletrichouse.data.reponse

import com.example.nativeeletrichouse.dto.DtoResponseEletricHouse
import com.example.nativeeletrichouse.presation.arcondicionado.CalcularArCondicionadoUiState
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCalculoArCond(
    val IDRS: Double = 0.0,
    val amperagemCircuitoAc: String="",
    val btuAdicionalInsidenciaRaioSolar:Int =0,
    val btuAdicionalPorEletronico: Int =0,
    val btuAdicionalPorPessoa: Int =0,
    val btuPorM2: Int =0,
    val btusTotal: Int =0,
    val potenciaEletria: String ="",
    val quantEletrodomestico: Int =0,
    val quantPessoasAmbiente: Int =0
){

/*    {
        "quantPessoasAmbiente": 0,
        "quantEletrodomestico": 0,
        "btuPorM2": 0,
        "btuAdicionalPorPessoa": 0,
        "btuAdicionalPorEletronico": 0,
        "btuAdicionalInsidenciaRaioSolar": 0,
        "btusTotal": 0,
        "IDRS": 0,
        "potenciaEletria": "string",
        "amperagemCircuitoAc": "string"
    }*/



}