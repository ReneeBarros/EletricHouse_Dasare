package com.example.nativeeletrichouse.domain.calcularcabo.implemnets

import android.annotation.SuppressLint
import com.example.nativeeletrichouse.domain.calcularcabo.intefaces.CalculoCaboEletricoInterFace

class CalculoCaboEletricoImp(): CalculoCaboEletricoInterFace  {

    var correnteDoCabo = 0.0
    @SuppressLint("DefaultLocale")
    override fun correnteEltrica(
        tensao: Int,
        potencia: Double,
        corrente: Double,
        fatoPotencia: Double,

    ): Double {




        val amp = corrente
        val correnteCalculada = String.format("%.1f",(potencia / (tensao*fatoPotencia)))

        return if(amp != 0.0) amp else (correnteCalculada.replace(",",".").toDouble())


    }

    override fun modeloInstalacaoDosCabos(modelo: String): String {
       return modelo.subSequence(0,2).toString()
    }

    override fun condutoresCarregadoNoCircuito(condutoresCarregado: String): Int {
        return condutoresCarregado[condutoresCarregado.lastIndex].digitToInt()
    }

    override fun tipoIsolacaoCabo(value: String) {
        TODO("Not yet implemented")
    }

    override fun quantCircuitosNoEletroduto(value: String):Double? {
        val mapear:Map<String,Double> = mapOf("1" to 1.0,"2" to 0.80, "3" to  0.70,
            "4" to  0.65, "5" to  0.60, "6" to 0.57, "7" to 0.54, "8" to 0.52, "9 a 11" to 0.50,
            "12 a 15" to 0.45, "16 a 19" to 0.41, ">= 20" to 0.38)
        return mapear[value]
    }

    override fun encontrarCaboCalculado(tabelaIndice: String, correnteCorrigida:Double, qtdeCondutorCarregado:Int) :Double{
        var getCabo: Double = 0.3
        //val A2_2 = mapOf(14.0 to 1.5, 18.5 to 2.5, 25.0 to 4, 32.0 to 6.0, 43.0 to 10, 57.0 to 16, 75.0 to 25)
        //val A2_3 = mapOf(13.0 to 1.5, 17.5 to 2.5, 23.0 to 4, 29.0 to 6.0, 39.0 to 10, 52.0 to 16, 68.0 to 25)

        var calculatedCurrent =correnteCorrigida
        var indicadorTabela =""

        if (tabelaIndice =="A1" && qtdeCondutorCarregado==2){
            indicadorTabela = "A1_2"
            calculatedCurrent = when {
                correnteCorrigida in 0.0..14.5 -> 14.5
                correnteCorrigida in 14.6..19.5 -> 19.5
                correnteCorrigida in 19.6..26.0 -> 26.0
                correnteCorrigida in 26.1..34.0 -> 34.0
                correnteCorrigida in  34.1..46.0 -> 46.0
                correnteCorrigida in 46.1..61.0 -> 61.0
                correnteCorrigida in 61.1..80.0 -> 80.0
                correnteCorrigida in 80.1..99.0 -> 99.0
                correnteCorrigida in 99.1..119.0 -> 119.0
                correnteCorrigida in 119.1..151.0 -> 151.0
                else -> {correnteCorrigida}
            }

            val A1_2 = mapOf(
                14.5 to 1.5, 19.5 to 2.5, 26.0 to 4.0, 34.0 to 6.0,
                46.0 to 10.0, 61.0 to 16.0, 80.0 to 25.0, 99.0 to 35.0,
                119.0 to 50.0, 151.0 to 70.0
            )
            getCabo = A1_2[calculatedCurrent]?:0.1
            correnteDoCabo = calculatedCurrent


        }
        else if (tabelaIndice =="A1"&& qtdeCondutorCarregado==3){
            indicadorTabela = "A1_3"
            calculatedCurrent = when {
                correnteCorrigida in 0.0..13.5 -> 13.5
                correnteCorrigida in 13.6..18.0 -> 18.0
                correnteCorrigida in 18.1..24.0 -> 24.0
                correnteCorrigida in 24.1..31.0 -> 31.0
                correnteCorrigida in 31.1..42.0 -> 42.0
                correnteCorrigida in 42.1..56.0 -> 56.0
                correnteCorrigida in 56.1..73.0 -> 73.0
                correnteCorrigida in 73.1..89.0 -> 89.0
                correnteCorrigida in 89.1..108.0 -> 108.0
                correnteCorrigida in 108.1..136.0 -> 136.0
                else -> {correnteCorrigida}
            }
            val A1_3 = mapOf(
                13.5 to 1.5, 18.0 to 2.5, 24.0 to 4.0, 31.0 to 6.0,
                42.0 to 10.0, 56.0 to 16.0, 73.0 to 25.0, 89.0 to 35.0,
                108.0 to 50.0, 136.0 to 70.0
            )
            getCabo = A1_3[calculatedCurrent]?:0.2

            correnteDoCabo = calculatedCurrent


        }
        else if (tabelaIndice =="B1"&& qtdeCondutorCarregado==2){
            calculatedCurrent = when {
                correnteCorrigida in 0.0..17.5 -> 17.5
                correnteCorrigida in 17.6..24.0 -> 24.0
                correnteCorrigida in 24.1..32.0 -> 32.0
                correnteCorrigida in 32.1..41.0 -> 41.0
                correnteCorrigida in 41.1..57.0 -> 57.0
                correnteCorrigida in 57.1..76.0 -> 76.0
                correnteCorrigida in 76.1..101.0 -> 101.0
                correnteCorrigida in 101.1..125.0 -> 125.0
                correnteCorrigida in 125.1..151.0 -> 151.0
                correnteCorrigida in 151.1..192.0 -> 192.0
                else -> {correnteCorrigida}
            }
            val B1_2 = mapOf(
                17.5 to 1.5, 24.0 to 2.5, 32.0 to 4.0, 41.0 to 6.0,
                57.0 to 10.0, 76.0 to 16.0, 101.0 to 25.0, 125.0 to 35.0,
                151.0 to 50.0, 192.0 to 70.0
            )
            getCabo = B1_2[calculatedCurrent]?:0.31
            correnteDoCabo = calculatedCurrent



        }
        else if (tabelaIndice =="B1"&& qtdeCondutorCarregado==3){

            calculatedCurrent = when {
                correnteCorrigida in 0.0..15.5 -> 15.5
                correnteCorrigida in 15.6..21.0 -> 21.0
                correnteCorrigida in 21.1..28.0 -> 28.0
                correnteCorrigida in 28.1..36.0 -> 36.0
                correnteCorrigida in 36.1..50.0 -> 50.0
                correnteCorrigida in 50.1..68.0 -> 68.0
                correnteCorrigida in 68.1..89.0 -> 89.0
                correnteCorrigida in 89.1..110.0 -> 110.0
                correnteCorrigida in 110.1..134.0 -> 134.0
                correnteCorrigida in 134.1..171.0 -> 171.0
                else -> {correnteCorrigida}
            }
            val B1_3 = mapOf(15.5 to 1.5, 21.0 to 2.5, 28.0 to 4.0, 36.0 to 6.0,
                50.0 to 10.0, 68.0 to 16.0, 89.0 to 25.0, 110.0 to 35.0, 134.0 to 50.0,
                171.0 to 70.0
            )
            getCabo = B1_3[calculatedCurrent]?:0.4
            correnteDoCabo = calculatedCurrent
        }

        return getCabo
    }

    override fun correnteDoCaboSuportado(indiceAgrupamento: Double): Double{

        return (correnteDoCabo * indiceAgrupamento)
    }

    override fun calculoCircuitoTrifasico(
        tensao: Int,
        potencia: Double,
        corrente: Double,
        fatoPotencia: Double,
        distancia:Double,
        quedaTensao:Double
    ):Double{
        val  raizDeTres = 1.73
        // corrente tifasica
        val corr = (potencia /(raizDeTres*tensao*fatoPotencia))
        var seccaoCabo:Double

        if(corrente != 0.0) {
            seccaoCabo= ((corrente * (distancia * raizDeTres))/(58 * (quedaTensao*tensao)))
        }
        else {
            seccaoCabo = ((corr * (distancia * raizDeTres)) / (58 * (quedaTensao*tensao)))
        }

        return seccaoCabo
    }

}


@SuppressLint("DefaultLocale")
fun main(){
    var calcular = CalculoCaboEletricoImp()

    var corrente = calcular.correnteEltrica(tensao = 220, potencia = 12524.0, corrente = 0.0, fatoPotencia = 0.80)
    println("Corrente:" + corrente)
    var modelInstalacao = calcular.modeloInstalacaoDosCabos("B1 modelo")
    println("Modelo: " + modelInstalacao)
    var condutorCarreg = calcular.condutoresCarregadoNoCircuito("Serao 2")
    println("Condutor Carregado: " + condutorCarreg)
    var quantcircuito = calcular.quantCircuitosNoEletroduto("2")

    println("Qunatidade de Circuitos: 3 " + quantcircuito)

    var buscarCabo = calcular.encontrarCaboCalculado(modelInstalacao, correnteCorrigida = String.format("%.2f",(corrente/quantcircuito!!)).replace(",",".").toDouble(),condutorCarreg)
    println("Esse Cabo: "+buscarCabo + " Corrente Corrigida: "+String.format("%.2f",(corrente/quantcircuito!! )).replace(",",".").toDouble())
}