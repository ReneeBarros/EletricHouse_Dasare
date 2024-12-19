package com.example.nativeeletrichouse.presation.solar.offgrid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.data.reponse.ResponseFromCalculatedSistemSolar
import com.example.nativeeletrichouse.presation.components.widget.TopBar


@Composable
fun ShowSolarSistem(
    navController: NavController,
    response: ResponseFromCalculatedSistemSolar,
){
    Scaffold(
        topBar = {
            TopBar(
                title = "Sistema Solar Offgrid",
                fontSize = 16.sp,
                modifier = Modifier,
                navController = navController
            )
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            val listItem = listOf(
                "Cidade:",
                "Carga Total (W):",
                "Wh/dia Requerida:",
                "(A) Gerada no Pior dia de Irradiação Solar:",
                "Capacidade do Banco de Bateria(AH):",
                "Capacidade da bateria (AH):",
                "Numero de Bateria em Serie:",
                "Numero de Bateria em Paralelo:",
                "Numero Total de Bateria:",
                "Angulo Ideal de Instalacão:",
                "Potencia do Painel Solar:",
                "Potencia Corrigida do Painel Solar:",
                "Corrente Diaria Requerida Por Arranjo (A):",
                "Numero de Painel Solar em Serie:",
                "Numero de Painel Solar em Paralelo:",
                "Numero Total de Painel Solar:",
               "Potencia Minima de Carga do Inversor:",
                "Potencia Maxima de Carga do Inversor:",
            )

            val responseSolar= listOf(
                response.city,
                "${response.totalLoad}",
                "${response.GeralenergyRequired}",
                "${response.worstDaySolarPanelCurrent}",
                "${response.batteryBankCapacityReal}",
                "${response.capacityBateryAH}",
                "${response.numberOfBatteriesInsires}",
                "${response.numberOfBatteriesParallel}",
                "${response.numberTotalOfBattty}",
                "${response.anguloIdealCorrecaoIrradiacaoSolar}",
                "${response.PowerOfPainelModule}",
                "${response.powerPicoOfModuleCorrigida}",
                "${response.currentDiariaPorArranjo}",
                "${response.quantMoludoSerie}",
                "${response.numberSolarPanelsParallel}",
                "${response.quantityTotalOfModule}",
                "${response.MimPowerInvesor}",
                "${response.MaxPowerInversor}"
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                listItem.forEachIndexed() { index, item ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = responseSolar[index],
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            }
        }
    }
}