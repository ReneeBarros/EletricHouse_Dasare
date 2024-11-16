package com.example.nativeeletrichouse.domain.calculador_ar_cond

import android.content.Context
import androidx.navigation.NavController
import com.example.nativeeletrichouse.api.api_eletri_house.ApiEletricHouse
import com.example.nativeeletrichouse.data.reponse.ResponseCalculoArCond
import com.example.nativeeletrichouse.data.request.RequestCalcularArCond
import com.example.nativeeletrichouse.main.core.navigation.navigation_arcond.NavigationArCond
import com.example.nativeeletrichouse.maper.responsefromApiAcToResultScreenAC
import com.example.nativeeletrichouse.presation.arcondicionado.CalcularArCondicionadoUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CalcularArCondicionado {

    fun calculadorArCondicionado(
        uiState: CalcularArCondicionadoUiState,
        context: Context,
        scope: CoroutineScope,
        navController: NavController
    ) {


        val request = RequestCalcularArCond(
            largura = uiState.width.replace(",", ".").toDouble(),
            comprimento = uiState.length.replace(",", ".").toDouble(),
            tensao = uiState.voltage,
            quantEletrodomestico=uiState.numberOfAppliances.toInt(),
            quantPessoasAmbiente = uiState.numberOfPeopleInRoom.toInt(),
            insidenciaRaioSolar = uiState.solarIncidence,
            ambiente = uiState.roomType
        )


        scope.launch {
            //val responseApi =

            val ac =responsefromApiAcToResultScreenAC(
                uiState = uiState,
                fromApi = ApiEletricHouse().apiArCond(request)
            )

            NavigationArCond().navigationArCond(
                navController = navController,
                ac = ac
            )

        }




    }
}