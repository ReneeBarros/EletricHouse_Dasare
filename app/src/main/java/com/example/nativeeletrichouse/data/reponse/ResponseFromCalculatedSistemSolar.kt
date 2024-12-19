package com.example.nativeeletrichouse.data.reponse

import kotlinx.serialization.Serializable

@Serializable
data class ResponseFromCalculatedSistemSolar(
    val city:String,
    val GeralenergyRequired: Float,
    val MaxPowerInversor: Float,
    val MimPowerInvesor: Float,
    val PowerOfPainelModule: Int,
    val anguloIdealCorrecaoIrradiacaoSolar: Float,
    val batteryBankCapacityReal: Int,
    val capacityBateryAH: Int,
    val currentDiariaPorArranjo: Float,
    val numberOfBatteriesInsires: Int,
    val numberOfBatteriesParallel: Int,
    val numberSolarPanelsParallel: Int,
    val numberTotalOfBattty: Int,
    val powerPicoOfModuleCorrigida: Float,
    val quantMoludoSerie: Int,
    val quantityTotalOfModule: Int,
    val totalLoad: Float,
    val worstDaySolarPanelCurrent: Float
)