package com.example.nativeeletrichouse.dto

import com.example.nativeeletrichouse.data.reponse.ResponseCaculateAmbiente
import com.example.nativeeletrichouse.data.request.RequestCalculateAmbiente
import com.example.nativeeletrichouse.main.core.navigation.HomeGraph

fun dtoTransference(
    listresponse:List<ResponseCaculateAmbiente>
): List<HomeGraph.ShowResultData>{
    val listaDeDadosResultado: MutableList<HomeGraph.ShowResultData> = mutableListOf()
    listresponse.forEach(){item->
        val reponseTotal =  HomeGraph.ShowResultData(
                            ambiente = item.ambiente,
                            largura = item.largura.toFloat(),
                            comprimento = item.comprimento.toFloat(),
                            area = item.area.replace(",", ".").toFloat(),
                            lumensAmbiente = item.lumensAmbiente,
                            lumensLuminaria = item.lumensLuminaria,
                            lumensTotal = item.lumensTotal.toFloat(),
                            potenciaLuminaria = item.potenciaLuminaria.toFloat(),
                            totalLuminaria = item.totalLuminaria.toFloat(),
                            potenciaTotal = item.potenciaTotalIlum.toFloat(),
                            amperagemIluminacao = item.amperagemCircuitoIlum.toFloat(),
                            quantToamda = item.quantTomada,
                            potenciaTotalTomada = item.potenciaTotalTomada.toFloat(),
                            amperagemTomada = item.amperagemTomada.toFloat(),
                            tensao = item.tensao,
                            quantPessoasAmbiente = item.quantPessoasAmbiente,
                            quantEletrodomestico = item.quantEletrodomestico,
                            btuAdicionalPorPessoa = item.btuAdicionalPorPessoa,
                            btuAdicionalPorEletronico = item.btuAdicionalPorEletronico,
                            btuPorM2 = item.btuPorM2,
                            btusTotal = item.btusTotal,
                            btuAdicionalInsidenciaRaioSolar = item.btuAdicionalInsidenciaRaioSolar,
                            IDRS = item.IDRS.toFloat(),
                            potenciaEletria = item.potenciaEletriaAc.toString(),
                            amperagemCircuitoAc = item.amperagemCircuitoAc.toString(),
                            calcular = "ambiente"
        )
       listaDeDadosResultado.add(reponseTotal)
    }
    return listaDeDadosResultado
}