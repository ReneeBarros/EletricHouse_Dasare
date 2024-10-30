package com.example.nativeeletrichouse.repository

import com.example.nativeeletrichouse.data.db.dao.AmbienteDao
import com.example.nativeeletrichouse.data.db.entity.AmbienteEntity
import com.example.nativeeletrichouse.data.reponse.ResponseCaculateAmbiente
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


class AmbienteRepository(
    private val dao: AmbienteDao
) {
    val ambientes: Flow<List<AmbienteEntity>> = dao.findAll()
    /*val ambientes get() = dao.findAll()*/

    suspend fun saveAmbeinte(ambiente: ResponseCaculateAmbiente){
        dao.save(ambiente.toAmbienteEntity())
    }

    suspend fun deletarAmbiente(ambiente: AmbienteEntity){
        dao.delete(ambiente)
    }
}

fun ResponseCaculateAmbiente.toAmbienteEntity()=  AmbienteEntity(
    id = 0,
    ambiente = this.ambiente,
    nomeAmbiente = this.nomeAmbiente,
    largura = this.largura,
    comprimento = this.comprimento,
    tensao = this.tensao,
    area = this.area,
    lumensAmbiente = this.lumensAmbiente,
    lumensLuminaria = this.lumensLuminaria,
    lumensTotal = this.lumensTotal,
    totalLuminaria = this.totalLuminaria,
    potenciaLuminaria = this.potenciaLuminaria,
    potenciaTotalIlum = this.potenciaTotalIlum,
    amperagemCircuitoIlum = this.amperagemCircuitoIlum,
    quantTomada = this.quantTomada,
    potenciaTotalTomada = this.potenciaTotalTomada,
    amperagemTomada = this.amperagemTomada,
    quantPessoasAmbiente = this.quantPessoasAmbiente,
    quantEletrodomestico = this.quantEletrodomestico,
    btuPorM2 = this.btuPorM2,
    btuAdicionalPorPessoa = this.btuAdicionalPorPessoa,
    btuAdicionalPorEletronico = this.btuAdicionalPorEletronico,
    btuAdicionalInsidenciaRaioSolar = this.btuAdicionalInsidenciaRaioSolar,
    btusTotal = this.btusTotal,
    IDRS = this.IDRS,
    potenciaEletriaAc = this.potenciaEletriaAc,
    amperagemCircuitoAc = this.amperagemCircuitoAc

)

fun AmbienteEntity.toResponseCaculateAmbiente() = ResponseCaculateAmbiente(

    ambiente = this.ambiente,
    nomeAmbiente = this.nomeAmbiente,
    largura = this.largura,
    comprimento = this.comprimento,
    tensao = this.tensao,
    area = this.area,
    lumensAmbiente = this.lumensAmbiente,
    lumensLuminaria = this.lumensLuminaria,
    lumensTotal = this.lumensTotal,
    totalLuminaria = this.totalLuminaria,
    potenciaLuminaria = this.potenciaLuminaria,
    potenciaTotalIlum = this.potenciaTotalIlum,
    amperagemCircuitoIlum = this.amperagemCircuitoIlum,
    quantTomada = this.quantTomada,
    potenciaTotalTomada = this.potenciaTotalTomada,
    amperagemTomada = this.amperagemTomada,
    quantPessoasAmbiente = this.quantPessoasAmbiente,
    quantEletrodomestico = this.quantEletrodomestico,
    btuPorM2 = this.btuPorM2,
    btuAdicionalPorPessoa = this.btuAdicionalPorPessoa,
    btuAdicionalPorEletronico = this.btuAdicionalPorEletronico,
    btuAdicionalInsidenciaRaioSolar = this.btuAdicionalInsidenciaRaioSolar,
    btusTotal = this.btusTotal,
    IDRS = this.IDRS,
    potenciaEletriaAc = this.potenciaEletriaAc,
    amperagemCircuitoAc = this.amperagemCircuitoAc
)