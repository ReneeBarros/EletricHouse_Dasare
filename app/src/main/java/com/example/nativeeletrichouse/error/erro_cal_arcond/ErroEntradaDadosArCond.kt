package com.example.nativeeletrichouse.error.erro_cal_arcond

import android.content.Context
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.presation.arcondicionado.CalcularArCondicionadoUiState
import com.shashank.sony.fancytoastlib.FancyToast


fun erroEntradaDadosArCond(
    uiState: CalcularArCondicionadoUiState,
    context: Context
):Boolean {

    val errorMessage = when {
        uiState.length.isEmpty() -> "Precisa Informar Comprimento"
        uiState.width.isEmpty() -> "Precisa informar Largura"
        uiState.numberOfAppliances.isEmpty() -> "Precisa Quantidade de Aparelho eletronico"
        uiState.numberOfAppliances=="Quant de Eletronico no Ambiente" -> "Precisa Escolher a potencia da Luminaria"
        uiState.numberOfPeopleInRoom.isEmpty() ->" Precisa Inserir quantidade de Pessoas no Ambiente"
        uiState.numberOfPeopleInRoom=="Quant de Pessoa no Ambiente" ->" Precisa Inserir quantidade de Pessoas no Ambiente"
        uiState.roomType.isEmpty() -> "Precisa informar Nome do Ambiente"
        uiState.roomType == "Escolher Ambiente" -> "Precisa informar Ambiente"
        uiState.roomName.isEmpty() -> "Precisa informar Nome do Ambiente"
        uiState.voltage == 0 -> "Precisa Informar a Tensão para Calcular a Corrente do Circuito"

        else -> null
    }
    if (errorMessage != null) {
        FancyToast.makeText(context,
            errorMessage, FancyToast.LENGTH_SHORT,
            FancyToast.WARNING, R.drawable.lampada_acesa,
            false
        ).show()
        return false
    }

    return true
}