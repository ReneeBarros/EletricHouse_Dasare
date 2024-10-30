package com.example.nativeeletrichouse.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class AmbienteEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val nomeAmbiente:String,
    val ambiente: String,
    val amperagemCircuitoAc: Double,
    val amperagemCircuitoIlum: Double,
    val amperagemTomada: Double,
    val area: String,
    val btuAdicionalInsidenciaRaioSolar: Int,
    val btuAdicionalPorEletronico: Int,
    val btuAdicionalPorPessoa: Int,
    val btuPorM2: Int,
    val btusTotal: Int,
    val comprimento: Double,
    val largura: Double,
    val lumensAmbiente: Int,
    val lumensLuminaria: Int,
    val lumensTotal: Double,
    val IDRS: Double,
    val potenciaEletriaAc: Double,
    val potenciaLuminaria: Double,
    val potenciaTotalIlum: Double,
    val potenciaTotalTomada: Double,
    val quantEletrodomestico: Int,
    val quantPessoasAmbiente: Int,
    val quantTomada: Int,
    val tensao: Int,
    val totalLuminaria: Double
)
