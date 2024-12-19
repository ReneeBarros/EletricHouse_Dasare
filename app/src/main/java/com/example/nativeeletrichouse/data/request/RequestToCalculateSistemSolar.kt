package com.example.nativeeletrichouse.data.request

import com.example.nativeeletrichouse.domain.Equipment
import kotlinx.serialization.Serializable

@Serializable
data class RequestToCalculateSistemSolar(
    val PowerOfPainelModule: Int,
    val autonomyDay: Double,
    val capacityBateryAH: Int,
    val city: String,
    val dischargeDepthOfBatteryBank: Double,
    val equipments: List<Equipment>,
    val metodoInstalacao: String,
    val temperaturaAmbiente: Double,
    val voltagyBattery: Int,
    val voltagyBatteryBank: Int
)