package com.example.nativeeletrichouse.domain.calcularcabo

import com.example.nativeeletrichouse.domain.calcularcabo.intefaces.CalculoCaboEletricoInterFace

class CalculadorDeCabo{

    fun calculadarCabos(
        calculoCabo: CalculoCaboEletricoInterFace,
        tensaoEnt: Int,
        potenciaEnt: Double,
        correnteEnt: Double = 0.0,
        fatoPotenciaEnt: Double,
        modeloInstalacaoCabos:String,
        condutoresCarregado:String,
        quantDeCircuito:String):Double{

        val corrente = calculoCabo.correnteEltrica(
            tensao = tensaoEnt,
            corrente = correnteEnt,
            potencia = potenciaEnt,
            fatoPotencia = fatoPotenciaEnt
        )
        val modeloInstalacao = calculoCabo.modeloInstalacaoDosCabos(modeloInstalacaoCabos)
        val condutorCarregado = calculoCabo.condutoresCarregadoNoCircuito(condutoresCarregado)
        val quantCircuito = calculoCabo.quantCircuitosNoEletroduto(quantDeCircuito)
        var caboEncontrado = calculoCabo.encontrarCaboCalculado(
            tabelaIndice = modeloInstalacao,
            correnteCorrigida = (corrente * quantCircuito!!),
            qtdeCondutorCarregado =condutorCarregado )

        return caboEncontrado

    }






}