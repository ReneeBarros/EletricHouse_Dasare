package com.example.nativeeletrichouse.domain.caculador_sistemaSolarOffgrid

import android.content.Context
import androidx.navigation.NavController
import com.example.nativeeletrichouse.api.api_eletri_house.ApiEletricHouse
import com.example.nativeeletrichouse.domain.Equipment
import com.example.nativeeletrichouse.data.request.RequestToCalculateSistemSolar
import com.example.nativeeletrichouse.error.erro_cal_sistema_solar.erroEntradaDadosSistemaSolarOffgrid
import com.example.nativeeletrichouse.presation.solar.offgrid.OffGridSolarUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CalcularSistemaSolarOffgrid() {

    fun calcularSistemaSolarOffgrid(
        uiState: OffGridSolarUiState,
        navController: NavController,
        context: Context,
        isLoading: Boolean,
        scope: CoroutineScope,
        equipment: List<Equipment>
    ) {

        val controleDeErro = erroEntradaDadosSistemaSolarOffgrid(
            context = context,
            uiState = uiState,

        )

        if (controleDeErro) {

            val request = RequestToCalculateSistemSolar(
                city = uiState.city,
                equipments = equipment,
                metodoInstalacao = uiState.metodoInstalacao,
                temperaturaAmbiente = uiState.temperaturaAmbiente.replace(",", ".").toDouble(),
                voltagyBattery = uiState.voltagyBattery.toInt(),
                voltagyBatteryBank = uiState.voltagyBatteryBank.toInt(),
                autonomyDay = uiState.autonomyDay.toDouble(),
                capacityBateryAH = uiState.capacityBateryAH.toInt(),
                dischargeDepthOfBatteryBank = (uiState.dischargeDepthOfBatteryBank.replace("%","").toDouble()/100),
                PowerOfPainelModule = uiState.PowerOfPainelModule.toInt()
            )

            scope.launch {
                val sistemSolarCalculated =
                    ApiEletricHouse().apiCalcularSistemaSolarOffgrid(request)
                navController.navigate(
                    sistemSolarCalculated
                )
            }
        }
    }
}