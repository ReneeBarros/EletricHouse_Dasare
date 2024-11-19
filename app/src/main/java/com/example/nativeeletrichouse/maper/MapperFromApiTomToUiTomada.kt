package com.example.nativeeletrichouse.maper

import com.example.nativeeletrichouse.data.reponse.ResponseCalculoArCond
import com.example.nativeeletrichouse.data.reponse.ResponseCalculoTomada
import com.example.nativeeletrichouse.dto.DtoResponseEletricHouse
import com.example.nativeeletrichouse.presation.arcondicionado.CalcularArCondicionadoUiState
import com.example.nativeeletrichouse.presation.tomada.CalcularTomadaUiState

fun mapperResponsefromApiTomToResultUiTomada(
    uiState: CalcularTomadaUiState,
    fromApi: ResponseCalculoTomada
): DtoResponseEletricHouse.DtoTomada{

    val perimetro = ((uiState.length.replace(",", ".").toFloat()) + (uiState.width.replace(",", ".").toFloat()))*2


    val tomadaToResultScreen = DtoResponseEletricHouse.DtoTomada(

        largura=uiState.width.replace(",", ".").toFloat(),
        comprimento=uiState.length.replace(",", ".").toFloat(),
        ambiente=uiState.roomType,
        nomeAmbiente=uiState.roomName,
        tensao=uiState.voltage,
        perimetro =perimetro ,
        potenciaTotalTomada = fromApi.potenciaTotal.toFloat(),
        amperagemTomada = fromApi.amperagemTomada.toFloat(),
        caboToTomada = 2.5f,
        quantToamda = fromApi.quantTomada

    )
    return tomadaToResultScreen
}