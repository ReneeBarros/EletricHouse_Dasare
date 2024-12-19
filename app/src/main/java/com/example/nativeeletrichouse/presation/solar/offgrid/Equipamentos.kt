package com.example.nativeeletrichouse.presation.solar.offgrid

data class Equipamentos(
    val equipamentos: String,
    val potencia: Double,
    val hrsDeUsoDiario: Double
){
    fun equip (equi: Equipamentos): Equipamentos{
       return Equipamentos(
           equipamentos = equi.equipamentos,
           potencia = equi.potencia,
           hrsDeUsoDiario = equi.hrsDeUsoDiario
       )
    }

}

