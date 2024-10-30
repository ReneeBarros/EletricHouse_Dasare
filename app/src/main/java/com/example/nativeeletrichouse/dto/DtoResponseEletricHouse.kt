package com.example.nativeeletrichouse.dto

import kotlinx.serialization.Serializable


sealed class DtoResponseEletricHouse{

    @Serializable
    data class DtoIluminacao(
        val id:Int?=0,
        val largura:Double?=0.0,
        val comprimento:Double?=0.0,
        val tensao:Int?=0,
        val area:String?="",
        val ambiente:String="",
        val lumensAmbiente:Int?=0,
        val lumensLuminaria:Int?=0,
        val potenciaLuminaria:Double=0.0,
        val totalLuminaria:Double = 0.0,
        val lumensTotal:Double =0.0,
        val potenciaTotal:Double =0.0,
        val amperagemIluminacao:Double =0.0
    ){}

    data class DtoTomada(
        val id:Int?=0,
        val largura:Double?=0.0,
        val comprimento:Double?=0.0,
        val tensao:Int?=0,
        val area:Float? =0.0f,
        val ambiente:String="",
        val quantToamda:Int?=0,
        val potenciaTotalTomada: Double=0.0,
        val amperagemTomada:Double=0.0
    )

    data class DtoArCond(
        val id:Int?=0,
        val largura:Double?=0.0,
        val comprimento:Double?=0.0,
        val tensao:Int?=0,
        val area:Float? =0.0f,
        val ambiente:String="",
        val quantPessoasAmbiente:Int?=0,
        val quantEletrodomestico:Int?=0,
        val btuPorM2:Int?=0,
        val btuAdicionalPorPessoa:Int?=0,
        val btuAdicionalPorEletronico:Int?=0,
        val btuAdicionalInsidenciaRaioSolar:Int?=0,
        val btusTotal:Int?=0,
        val IDRS:Double?=0.0,
        val potenciaEletria:String?="",
        val amperagemCircuitoAc:String?="",
    )

    data class EletricHouseComplete(

        val id:Int?=0,
        val largura: Float? =0.0f,
        val comprimento: Float? =0.0f,
        val tensao:Int?=0,
        val area:Float? =0.0f,
        val ambiente:String="",
        val lumensAmbiente:Int?=0,
        val lumensLuminaria:Int?=0,
        val potenciaLuminaria:Float? =0.0f,
        val totalLuminaria:Float? =0.0f,
        val lumensTotal:Float? =0.0f,
        val potenciaTotalLampadas:Float? =0.0f,
        val amperagemIluminacao:Float? =0.0f,
        val quantToamda:Int?=0,
        val potenciaTotalTomada: Float? =0.0f,
        val amperagemTomada:Float? =0.0f,
        val quantPessoasAmbiente:Int?=0,
        val quantEletrodomestico:Int?=0,
        val btuPorM2:Int?=0,
        val btuAdicionalPorPessoa:Int?=0,
        val btuAdicionalPorEletronico:Int?=0,
        val btuAdicionalInsidenciaRaioSolar:Int?=0,
        val btusTotal:Int?=0,
        val IDRS:Float? =0.0f,
        val potenciaEletria:String?="",
        val amperagemCircuitoAc:String?="",
    )

}


