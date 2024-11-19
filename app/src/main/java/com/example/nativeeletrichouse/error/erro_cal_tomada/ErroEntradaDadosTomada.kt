package com.example.nativeeletrichouse.error.erro_cal_tomada

import android.content.Context
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.presation.iluminacao.CalcularIluminacaoUiState
import com.example.nativeeletrichouse.presation.tomada.CalcularTomadaUiState
import com.shashank.sony.fancytoastlib.FancyToast

fun erroEntradaDadosTomada(
    uiState: CalcularTomadaUiState,
    context: Context
):Boolean {

    val errorMessage = when {
        uiState.length.isEmpty() -> "Precisa Informar Comprimento"
        uiState.width.isEmpty() -> "Precisa informar Largura"
        uiState.roomType.isEmpty() -> "Precisa informar Ambiente"
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