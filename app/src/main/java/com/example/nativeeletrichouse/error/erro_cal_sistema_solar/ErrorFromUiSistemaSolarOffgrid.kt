package com.example.nativeeletrichouse.error.erro_cal_sistema_solar

import android.content.Context
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.domain.Equipment
import com.example.nativeeletrichouse.domain.caculador_sistemaSolarOffgrid.CalcularSistemaSolarOffgrid
import com.example.nativeeletrichouse.presation.solar.offgrid.OffGridSolarUiState
import com.shashank.sony.fancytoastlib.FancyToast

fun erroEntradaDadosSistemaSolarOffgrid(
    uiState: OffGridSolarUiState,
    context: Context,
):Boolean {

    val errorMessage = when {
        uiState.city.isEmpty() -> "Precisa Informar Cidade"
        uiState.autonomyDay.isEmpty() -> "Precisa informar Autonomia da Bateria"
        uiState.metodoInstalacao=="Metodo de Instalacão" -> "Precisa escolher o Metodo de Instalação"
        uiState.quantityEquipment.isEmpty() ->" Precisa Inserir quantidade de Equipamento"
        uiState.equipmentPower.isEmpty() ->" Precisa Inserir potencia do Equipamento"
        //equipment.get(0).hoursDailyUse.isEmpty() ->" Precisa Informar quantas horas o equipamento fica é utilizado no dia"
        uiState.dischargeDepthOfBatteryBank=="Profundidade De Descarga do Banco de Bateria" ->" Precisa informar a profundidade desejada para descarga da bateria"
        uiState.PowerOfPainelModule.isEmpty() -> "Precisa informar Potencia do Painel Solar"
        uiState.voltagyBattery == "Voltagem Da bateria" -> "Precisa Escolher a Tensão da Bateria"
        uiState.temperaturaAmbiente.isEmpty() -> "Precisa informar Nome do Ambiente"
        uiState.voltagyBatteryBank == "Voltagem Do Banco de bateria" -> "Precisa Escolher a Tensão do banco de Bateria"

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