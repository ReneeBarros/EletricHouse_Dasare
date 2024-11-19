package com.example.nativeeletrichouse.error.erro_cal_cabo

import android.content.Context
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.presation.calcularcabo.CalcularCaboUiState
import com.shashank.sony.fancytoastlib.FancyToast


class ErroCalcularCabo{

    fun erroEntradaDados(
        uiState: CalcularCaboUiState,
        context: Context):Boolean {

        val errorMessage = when {
            uiState.modeloInstalacaoCabos == "Metodo de Instalaçâo" -> "Precisa Informar Metodo de Instalação"
            uiState.modeloInstalacaoCabos.isEmpty() -> "Precisa Informar Metodo de Instalação"
            uiState.quantDeCircuito == "Quantos Circuito?" -> "Precisa informar quantidade de Circuito Agrupado2"
            uiState.quantDeCircuito == "Quant.Circuito Agrupado" -> "Precisa informar quantidade de Circuito Agrupado"
            uiState.fatoPotencia == 0.0 -> "Precisa informar fator de potencia"
            uiState.condutoresCarregado.isEmpty() -> "Precisa informar Condutores Carregado"
            uiState.condutoresCarregado == "Condutores Carregado" -> "Precisa informar Condutores Carregado1"
            uiState.tensao == 0 && uiState.corrente == "0.0" -> "Precisa Informar a Tensão para Calcular a Corrente do Circuito"
            uiState.pontecia == "0.0" && uiState.corrente == "0.0" -> "Precisa Informar a Potencia do Circuito para Calcular a Corrente do Circuito"
            else -> null
        }


        if (errorMessage != null) {
            FancyToast.makeText(context,
                errorMessage, FancyToast.LENGTH_SHORT,
                FancyToast.WARNING, R.drawable.lampada_acesa,
                false
            ).show()
        }
        return true
    }




}