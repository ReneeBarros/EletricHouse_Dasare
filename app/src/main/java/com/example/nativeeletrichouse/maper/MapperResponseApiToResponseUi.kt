package com.example.nativeeletrichouse.maper

import androidx.compose.ui.text.toUpperCase
import com.example.nativeeletrichouse.data.reponse.ResponseCaculateAmbiente
import kotlinx.serialization.Serializable
import java.util.Locale

@Serializable
data class MapperResponseApiToResponseUi(
    val IDRS: Double,
    val nomeAmbiente: String,
    val ambiente: String,
    val amperagemCircuitoAc: Double,
    val amperagemCircuitoIlum: Double,
    val amperagemTomada: Double,
    val area: String,
    val btuAdicionalInsidenciaRaioSolar: Int,
    val btuAdicionalPorEletronico: Int,
    val btuAdicionalPorPessoa: Int,
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
    val totalLuminaria: Double,
    val caboIluminacao: Double,
    val caboTomada: Double,
    val caboArCond: Double,

    ){

    var listaAmbienteResponse = listOf(
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
        "${this.caboIluminacao}",
        "${this.quantTomada}",
        "${this.potenciaTotalTomada}",
        "${this.amperagemTomada}",
        "${this.caboTomada}",
        "${this.quantPessoasAmbiente}",
        "${this.quantEletrodomestico}",
        "${this.btuPorM2}",
        "${this.btuAdicionalPorPessoa}",
        "${this.btuAdicionalPorEletronico}",
        "${this.btuAdicionalInsidenciaRaioSolar}",
        "${this.btusTotal}",
        "${this.potenciaEletriaAc}",
        "${this.amperagemCircuitoAc}",
        "${this.IDRS}",
        "${this.caboArCond}"
        )
}

fun MapperResponseApiToResponseUi(responseFromApi: List<ResponseCaculateAmbiente>): MutableList<MapperResponseApiToResponseUi> {

    val listMapperResponseApiToResponseUi: MutableList<MapperResponseApiToResponseUi> =
        mutableListOf()

    responseFromApi.forEachIndexed { index, responseApi ->

        if (
            responseApi.ambiente.uppercase(Locale.ROOT) =="BANHEIRO" ||
            responseApi.ambiente.uppercase(Locale.ROOT) =="ESCADA/DISPENSA/GARAGEM"){
            listMapperResponseApiToResponseUi.add(
                MapperResponseApiToResponseUi(
                    tensao = responseApi.tensao,
                    btuAdicionalPorPessoa = 0,
                    amperagemCircuitoIlum = responseApi.amperagemCircuitoIlum,
                    btuAdicionalInsidenciaRaioSolar = 0,
                    amperagemCircuitoAc = 0.0,
                    potenciaTotalTomada = responseApi.potenciaTotalTomada,
                    nomeAmbiente = responseApi.nomeAmbiente,
                    quantEletrodomestico = 0,
                    quantPessoasAmbiente = 0,
                    potenciaEletriaAc = 0.0,
                    potenciaTotalIlum = responseApi.potenciaTotalIlum,
                    potenciaLuminaria = responseApi.potenciaLuminaria,
                    totalLuminaria = responseApi.totalLuminaria,
                    lumensAmbiente = responseApi.lumensAmbiente,
                    ambiente = responseApi.ambiente,
                    IDRS = 0.0,
                    area = responseApi.area,
                    quantTomada = responseApi.quantTomada,
                    amperagemTomada = responseApi.amperagemTomada,
                    lumensTotal = responseApi.lumensTotal,
                    comprimento = responseApi.comprimento,
                    btusTotal = 0,
                    lumensLuminaria = responseApi.lumensLuminaria,
                    btuPorM2 = 0,
                    largura = responseApi.largura,
                    btuAdicionalPorEletronico = 0,
                    caboIluminacao = 1.5,
                    caboArCond = 0.0,
                    caboTomada = 2.5
                )
            )

        }
        else{

            listMapperResponseApiToResponseUi.add(
                MapperResponseApiToResponseUi(
                    tensao = responseApi.tensao,
                    btuAdicionalPorPessoa = responseApi.btuAdicionalPorPessoa,
                    amperagemCircuitoIlum = responseApi.amperagemCircuitoIlum,
                    btuAdicionalInsidenciaRaioSolar = responseApi.btuAdicionalInsidenciaRaioSolar,
                    amperagemCircuitoAc = responseApi.amperagemCircuitoAc,
                    potenciaTotalTomada = responseApi.potenciaTotalTomada,
                    nomeAmbiente = responseApi.nomeAmbiente,
                    quantEletrodomestico = responseApi.quantEletrodomestico,
                    quantPessoasAmbiente = responseApi.quantPessoasAmbiente,
                    potenciaEletriaAc = responseApi.potenciaEletriaAc,
                    potenciaTotalIlum = responseApi.potenciaTotalIlum,
                    potenciaLuminaria = responseApi.potenciaLuminaria,
                    totalLuminaria = responseApi.totalLuminaria,
                    lumensAmbiente = responseApi.lumensAmbiente,
                    ambiente = responseApi.ambiente,
                    IDRS = responseApi.IDRS,
                    area = responseApi.area,
                    quantTomada = responseApi.quantTomada,
                    amperagemTomada = responseApi.amperagemTomada,
                    lumensTotal = responseApi.lumensTotal,
                    comprimento = responseApi.comprimento,
                    btusTotal = responseApi.btusTotal,
                    lumensLuminaria = responseApi.lumensLuminaria,
                    btuPorM2 = responseApi.btuPorM2,
                    largura = responseApi.largura,
                    btuAdicionalPorEletronico = responseApi.btuAdicionalPorEletronico,
                    caboIluminacao = 1.5,
                    caboArCond = 4.0,
                    caboTomada = 2.5
                )
            )
        }

    }
return listMapperResponseApiToResponseUi
}

