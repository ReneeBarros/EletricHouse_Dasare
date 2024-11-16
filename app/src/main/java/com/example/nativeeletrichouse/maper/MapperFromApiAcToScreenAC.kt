package com.example.nativeeletrichouse.maper

import com.example.nativeeletrichouse.data.reponse.ResponseCalculoArCond
import com.example.nativeeletrichouse.dto.DtoResponseEletricHouse
import com.example.nativeeletrichouse.presation.arcondicionado.CalcularArCondicionadoUiState

fun responsefromApiAcToResultScreenAC(
    uiState: CalcularArCondicionadoUiState,
    fromApi: ResponseCalculoArCond
): DtoResponseEletricHouse.DtoArCond{
    val acToResultScreen = DtoResponseEletricHouse.DtoArCond(
        IDRS=fromApi.IDRS.toFloat(),
        amperagemCircuitoAc=fromApi.amperagemCircuitoAc,
        btuAdicionalInsidenciaRaioSolar=fromApi.btuAdicionalInsidenciaRaioSolar,
        btuAdicionalPorEletronico=fromApi.btuAdicionalPorEletronico,
        btuAdicionalPorPessoa=fromApi.btuAdicionalPorPessoa,
        btuPorM2=fromApi.btuPorM2,
        btusTotal=fromApi.btusTotal,
        potenciaEletria=fromApi.potenciaEletria,
        quantEletrodomestico=fromApi.quantEletrodomestico,
        quantPessoasAmbiente=fromApi.quantPessoasAmbiente,
        largura=uiState.width.replace(",", ".").toFloat(),
        comprimento=uiState.length.replace(",", ".").toFloat(),
        ambiente=uiState.roomType,
        nomeAmbiente=uiState.roomName,
        caboArCond=4.0f,
        tensao=uiState.voltage,
        area = uiState.area.toFloat()

    )
    return acToResultScreen
}