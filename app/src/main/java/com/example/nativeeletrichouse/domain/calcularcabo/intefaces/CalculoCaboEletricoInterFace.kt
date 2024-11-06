package com.example.nativeeletrichouse.domain.calcularcabo.intefaces

interface CalculoCaboEletricoInterFace {

    /*
    * para iluminacao 1,5mm²  nbr 5410
    * para tomada 2,5mm² bitola de cabo minimo seguindo a nbr 5410
    *
    * para calcular precisamos responder essas perguntas
    * 1 qual a corrente do circuito?
    * 2- como os cabos serão instalados?
    * quantidade de cabos por circuito?
    * qual tipo de isolaçãp terá o cabo?
    * quantos cabos irão passar dentro do mesmo eletroduto?
    *
    *
    *
    *
    *
    * */

    fun correnteEltrica(tensao:Int, potencia:Double,corrente:Double =0.0,fatoPotencia:Double):Double
    fun modeloInstalacaoDosCabos(modelo:String):String
    fun condutoresCarregadoNoCircuito(condutoresCarregado:String):Int
    fun tipoIsolacaoCabo(value:String)
    fun quantCircuitosNoEletroduto(value:String):Double?
    fun encontrarCaboCalculado(tabelaIndice: String, correnteCorrigida:Double, qtdeCondutorCarregado:Int):Double
    fun correnteDoCaboSuportado(indiceAgrupamento: Double): Double

}
