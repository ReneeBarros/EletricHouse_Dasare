package com.example.nativeeletrichouse.domain.calcularcabo.implemnets

import com.example.nativeeletrichouse.domain.calcularcabo.intefaces.CalculoCaboEletricoInterFace

class CalculoCaboEletricoImp(): CalculoCaboEletricoInterFace  {
    override fun correnteEltrica(
        tensao: Int,
        potencia: Double,
        corrente: Double,
        fatoPotencia: Double
    ): Double {
        val amp = corrente
        if(amp != 0.0){
            return amp
        }
        return (potencia / (tensao*fatoPotencia))
    }

    override fun modeloInstalacaoDosCabos(modelo: String): String {
       return modelo.subSequence(0,1).toString()
    }

    override fun condutoresCarregadoNoCircuito(condutoresCarregado: String): Int {
        return condutoresCarregado[condutoresCarregado.length-1].code
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
        var condutorCarregado = qtdeCondutorCarregado
        var calculatedCurrent =0.0
        var indicadorTabela =""

        if (tabelaIndice =="A1"&& condutorCarregado==2){
            indicadorTabela = "A1_2"
            calculatedCurrent = when {
                correnteCorrigida in 0.0..14.5 -> 14.5
                correnteCorrigida in 14.6..19.5 -> 19.5
                correnteCorrigida in 19.6..26.0 -> 26.0
                correnteCorrigida in 26.1..34.0 -> 34.0
                correnteCorrigida in 34.1..46.0 -> 46.0
                correnteCorrigida in 46.1..61.0 -> 61.0
                correnteCorrigida in 61.1..80.0 -> 80.0
                else -> {correnteCorrigida}
            }

        }
        else if (tabelaIndice =="A1"&& condutorCarregado==3){
            indicadorTabela = "A1_3"
            calculatedCurrent = when {
                correnteCorrigida in 0.0..13.5 -> 13.5
                correnteCorrigida in 13.6..18.0 -> 18.0
                correnteCorrigida in 18.1..24.0 -> 24.0
                correnteCorrigida in 24.1..31.0 -> 31.0
                correnteCorrigida in 31.1..42.0 -> 42.0
                correnteCorrigida in 42.1..56.0 -> 56.0
                correnteCorrigida in 56.1..73.0 -> 73.0
                else -> {correnteCorrigida}
            }
        }

        if (tabelaIndice =="B1"&& condutorCarregado==2){
            indicadorTabela = "B1_2"
            calculatedCurrent = when {
                correnteCorrigida in 0.0..17.5 -> 17.5
                correnteCorrigida in 17.6..24.0 -> 24.0
                correnteCorrigida in 24.1..32.0 -> 32.0
                correnteCorrigida in 32.1..41.0 -> 41.0
                correnteCorrigida in 41.1..57.0 -> 57.0
                correnteCorrigida in 57.1..76.0 -> 76.0
                correnteCorrigida in 76.1..101.0 -> 101.0
                else -> {correnteCorrigida}
            }

        }
        else if (tabelaIndice =="B1"&& condutorCarregado==3){
            indicadorTabela = "B1_3"

            calculatedCurrent = when {
                correnteCorrigida in 0.0..15.5 -> 15.5
                correnteCorrigida in 13.6..18.0 -> 18.0
                correnteCorrigida in 18.1..24.0 -> 24.0
                correnteCorrigida in 24.1..31.0 -> 31.0
                correnteCorrigida in 31.1..42.0 -> 42.0
                correnteCorrigida in 42.1..56.0 -> 56.0
                correnteCorrigida in 56.1..73.0 -> 73.0
                else -> {correnteCorrigida}
            }
        }

        val A1_2 = mapOf(14.5 to 1.5, 19.5 to 2.5, 26.0 to 4.0, 34.0 to 6.0, 46.0 to 10.0, 61.0 to 16.0, 80.0 to 25.0)
        val A1_3 = mapOf(13.5 to 1.5, 18.0 to 2.5, 24.0 to 4.0, 31.0 to 6.0, 42.0 to 10.0, 56.0 to 16.0, 73.0 to 25.0)
        //val A2_2 = mapOf(14.0 to 1.5, 18.5 to 2.5, 25.0 to 4, 32.0 to 6.0, 43.0 to 10, 57.0 to 16, 75.0 to 25)
        //val A2_3 = mapOf(13.0 to 1.5, 17.5 to 2.5, 23.0 to 4, 29.0 to 6.0, 39.0 to 10, 52.0 to 16, 68.0 to 25)
        val B1_2 = mapOf(17.5 to 1.5, 24.0 to 2.5, 32.0 to 4.0, 41.0 to 6.0, 57.0 to 10.0, 76.0 to 16.0, 101.0 to 25.0)
        val B1_3 = mapOf(15.5 to 1.5, 21.0 to 2.5, 28.0 to 4.0, 36.0 to 6.0, 50.0 to 10.0, 68.0 to 16.0, 89.0 to 25.0)

        val baseA = mapOf("A1_2" to A1_2, "A1_3" to A1_3)
        val baseB = mapOf("B1_2" to A1_2, "B1_3" to A1_3)

        var getCabo: Double = 0.0

        if (indicadorTabela == "A1_2"){
            getCabo = baseA[tabelaIndice]?.get(calculatedCurrent)?:0.0
        }
        return getCabo
    }

}