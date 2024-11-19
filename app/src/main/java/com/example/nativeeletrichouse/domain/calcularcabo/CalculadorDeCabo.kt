package com.example.nativeeletrichouse.domain.calcularcabo

import com.example.nativeeletrichouse.domain.calcularcabo.intefaces.CalculoCaboEletricoInterFace
import java.util.Locale

class CalculadorDeCabo{

    fun calculadarCabos(
        calculoCabo: CalculoCaboEletricoInterFace,
        tensaoEnt: Int,
        potenciaEnt: Double,
        correnteEnt: Double = 0.0,
        fatoPotenciaEnt: Double,
        modeloInstalacaoCabos:String,
        condutoresCarregado:String,
        quantDeCircuito:String,
        distancia:Double = 1.0,
        temperatura:Double =30.0,
        quedaTensao:Double =0.05

    ):ShowCaboCalculate{

        val caboTrifasico = calculoCabo.calculoCircuitoTrifasico(
            tensao = tensaoEnt,
            corrente = correnteEnt,
            potencia = potenciaEnt,
            fatoPotencia = fatoPotenciaEnt,
            quedaTensao = quedaTensao,
            distancia = distancia
        )

        val corrente = calculoCabo.correnteEltrica(
            tensao = tensaoEnt,
            corrente = correnteEnt,
            potencia = potenciaEnt,
            fatoPotencia = fatoPotenciaEnt,
        )
        val modeloInstalacao = calculoCabo.modeloInstalacaoDosCabos(modeloInstalacaoCabos)
        val condutorCarregado = calculoCabo.condutoresCarregadoNoCircuito(condutoresCarregado)
        val quantCircuito = calculoCabo.quantCircuitosNoEletroduto(quantDeCircuito)
        var caboEncontrado = calculoCabo.encontrarCaboCalculado(
            tabelaIndice = modeloInstalacao,
            correnteCorrigida = (corrente / quantCircuito!!),
            qtdeCondutorCarregado =condutorCarregado )
        val correnteDoCabo = calculoCabo.correnteDoCaboSuportado(quantCircuito)

        if (tensaoEnt == 380){


            return ShowCaboCalculate(
                tensao = tensaoEnt,
                pontecia = potenciaEnt,
                corrente = corrente,
                fatoPotencia = fatoPotenciaEnt,
                modeloInstalacaoCabos = modeloInstalacao,
                condutoresCarregado = condutorCarregado,
                quantDeCircuito = quantDeCircuito.lastOrNull()!!.digitToInt(),
                caboCalculado = 0.0,
                correnteSuportadoPeloCabo = String.format(locale = Locale.US,"%.2f", correnteDoCabo).toDouble(),
                temperatura = temperatura,
                quedaTensao = quedaTensao,
                distancia = distancia,
                seccaoCaboTrifasico =String.format(locale = Locale.US,"%.2f", caboTrifasico).toDouble()
            )
        }
        else{

            return ShowCaboCalculate(
                tensao = tensaoEnt,
                pontecia = potenciaEnt,
                corrente = corrente,
                fatoPotencia = fatoPotenciaEnt,
                modeloInstalacaoCabos = modeloInstalacao,
                condutoresCarregado = condutorCarregado,
                quantDeCircuito = quantDeCircuito.lastOrNull()!!.digitToInt(),
                caboCalculado = caboEncontrado,
                correnteSuportadoPeloCabo = correnteDoCabo,
                temperatura = temperatura,
                quedaTensao = 0.0,
                distancia = 0.0,
                seccaoCaboTrifasico = 0.0
            )
        }
    }
}