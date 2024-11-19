package com.example.nativeeletrichouse.domain.calculador_tomada

import android.content.Context
import androidx.navigation.NavController
import com.example.nativeeletrichouse.api.api_eletri_house.ApiEletricHouse
import com.example.nativeeletrichouse.data.request.RequestCalcularTomada
import com.example.nativeeletrichouse.main.core.navigation.navigation_tomada.NavigationTomada
import com.example.nativeeletrichouse.maper.mapperResponsefromApiTomToResultUiTomada
import com.example.nativeeletrichouse.presation.tomada.CalcularTomadaUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CalcularTomada {

    fun calculadorTomada(
        uiState: CalcularTomadaUiState,
        context: Context,
        scope: CoroutineScope,
        navController: NavController,
        fromApi: ApiEletricHouse
    ){
       val request =RequestCalcularTomada(
           comprimento = uiState.length.replace(",",".").toDouble(),
           ambiente = uiState.roomType,
           largura = uiState.width.replace(",",".").toDouble(),
           tensao = uiState.voltage
       )


       scope.launch {

           val tomada = mapperResponsefromApiTomToResultUiTomada(
               uiState = uiState,
               fromApi = fromApi.apiTomada(request)
           )
           NavigationTomada().navigationTomada(
               navController = navController,
               tomada = tomada
           )
       }


    }




}