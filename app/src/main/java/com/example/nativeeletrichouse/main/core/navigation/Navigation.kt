package com.example.nativeeletrichouse.main.core.navigation

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

/*
@Composable
fun Navigation(){



}
*/


sealed class HomeGraph{

    @Serializable
    object Home

    @Serializable
    object mainUi

    @Serializable
    object placaSolar

    @Serializable
    object calcularCabo

    @Serializable
    data class dataUi(
        val ilum:Boolean?=null,
        val tom:Boolean?=null,
        val ac:Boolean?=null,
        val home:Boolean?=null,
        val ambiente:String?="",
        val nomeAmbiente:String?="",
        val calcular:String = "ambiente"

    )


    @Serializable
    data class ShowResultData(
        val id:Int?=0,
        val largura:Float?=0.0f,
        val comprimento:Float?=0.0f,
        val tensao:Int?=0,
        val area:Float?=0.0f,
        val ambiente:String="",
        val lumensAmbiente:Int?=0,
        val lumensLuminaria:Int?=0,
        val potenciaLuminaria:Float?=0.0f,
        val totalLuminaria:Float?=0.0f,
        val lumensTotal:Float?=0.0f,
        val potenciaTotal:Float?=0.0f,
        val amperagemIluminacao:Float?=0.0f,
        val quantToamda:Int?=0,
        val potenciaTotalTomada: Float?=0.0f,
        val amperagemTomada:Float?=0.0f,
        val quantPessoasAmbiente:Int?=0,
        val quantEletrodomestico:Int?=0,
        val btuPorM2:Int?=0,
        val btuAdicionalPorPessoa:Int?=0,
        val btuAdicionalPorEletronico:Int?=0,
        val btuAdicionalInsidenciaRaioSolar:Int?=0,
        val btusTotal:Int?=0,
        val IDRS:Float?=0.0f,
        val potenciaEletria:String?="",
        val amperagemCircuitoAc:String?="",
        val calcular: String="ambiente"

        )

}