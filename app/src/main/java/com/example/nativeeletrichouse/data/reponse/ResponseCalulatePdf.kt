package com.example.nativeeletrichouse.data.reponse

import kotlinx.serialization.Serializable

@Serializable
data class ResponseCalulatePdf(
    val IDRS: Double,
    val nomeAmbiente:String,
    val ambiente: String,
    val amperagemCircuitoAc: Double,
    val amperagemCircuitoIlum: Double,
    val amperagemTomada: Double,
    val area: String,
    val btuPorM2: Int,
    val btusTotal: Int,
    val comprimento: Double,
    val largura: Double,
    val lumensAmbiente: Int,
    val lumensLuminaria: Int,
    val lumensTotal: Double,
    val potenciaEletriaAc: Double,
    val potenciaLuminaria: Double,
    val potenciaTotalIlum: Double,
    val potenciaTotalTomada: Double,
    val quantEletrodomestico: Int,
    val quantPessoasAmbiente: Int,
    val quantTomada: Int,
    val tensao: Int,
    val totalLuminaria: Double

){
    val listaAmbienteResponse = listOf(
        this.ambiente,
        this.nomeAmbiente,
        "${this.largura}",
        "${this.comprimento}",
        this.area,
        "${this.tensao}",
        "${this.lumensAmbiente}",
        "${this.lumensLuminaria}",
        "${this.potenciaLuminaria}",
        "${this.totalLuminaria}",
        "${this.lumensTotal}",
        "${this.potenciaTotalIlum}",
        "${this.amperagemCircuitoIlum}",
        "${this.quantTomada}",
        "${this.potenciaTotalTomada}",
        "${this.amperagemTomada}",
        "${this.quantPessoasAmbiente}",
        "${this.quantEletrodomestico}",
        "${this.btuPorM2}",
        "${this.btusTotal}",
        "${this.potenciaEletriaAc}",
        "${this.amperagemCircuitoAc}",
        "${this.IDRS}"
    )

}
