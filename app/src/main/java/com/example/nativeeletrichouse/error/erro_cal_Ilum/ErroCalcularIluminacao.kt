package com.example.nativeeletrichouse.error.erro_cal_Ilum

import android.content.Context
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.presation.iluminacao.CalcularIluminacaoUiState
import com.shashank.sony.fancytoastlib.FancyToast


fun erroEntradaDadosIluminacao(
    uiState: CalcularIluminacaoUiState,
    context: Context
):Boolean {

    val errorMessage = when {
        uiState.length.isEmpty() -> "Precisa Informar Comprimento"
        uiState.width.isEmpty() -> "Precisa informar Largura"
        uiState.lampPower.isEmpty() -> "Precisa Escolher a potencia da Luminaria"
        uiState.lampPower=="Potencia Luminaria" -> "Precisa Escolher a potencia da Luminaria"
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