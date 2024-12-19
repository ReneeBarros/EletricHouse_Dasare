package com.example.nativeeletrichouse.domain

import kotlinx.serialization.Serializable

@Serializable
data class Equipment(
    val equipmentName: String,
    val equipmentPower: Float,
    val hoursDailyUse: Float,
    val quantityEquipment: Int
)