package com.example.nativeeletrichouse.domain.caculador_iluminacao

import android.content.Context
import androidx.navigation.NavController
import com.example.nativeeletrichouse.api.api_eletri_house.ApiEletricHouse
import com.example.nativeeletrichouse.data.reponse.ResponseCalculoIluminacao
import com.example.nativeeletrichouse.data.request.RequestCalculateIluminacao
import com.example.nativeeletrichouse.main.core.navigation.navgation_ilumnacao.NavegationIlum
import com.example.nativeeletrichouse.presation.iluminacao.CalcularIluminacaoUiState
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CalcularIluminacao {

    fun calculadorIluminacao(
        uiState: CalcularIluminacaoUiState,
        context: Context,
        scope: CoroutineScope,
        navController: NavController

    ): ResponseCalculoIluminacao {
        var responseApi = ResponseCalculoIluminacao()


        val request = RequestCalculateIluminacao(
            ambiente = uiState.roomType,
            comprimento = uiState.length.replace(",", ".").toDouble(),
            largura = uiState.width.replace(",", ".").toDouble(),
            potenciaLumianria = uiState.lampPower.replace(",", ".").toDouble(),
            tensao = uiState.voltage
        )



        scope.launch {

            try {

                responseApi = ApiEletricHouse().apiIluminacao(request)

                NavegationIlum().navigationIlum(
                    navController = navController,
                    lampadaCaculada = responseApi
                )

            } catch (e: Exception) {
                FancyToast.makeText(
                    context,
                    "Erro ao Conectar com Servidor",
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,
                    false

                )

            }
        }

        return responseApi
    }
}