package com.example.nativeeletrichouse.main.core.navigation.navgation_ilumnacao

import androidx.navigation.NavController
import com.example.nativeeletrichouse.data.reponse.ResponseCalculoIluminacao

class NavegationIlum(){

    fun navigationIlum(
        navController: NavController,
        lampadaCaculada: ResponseCalculoIluminacao
    ){
        navController.navigate(
            lampadaCaculada

/*            ResponseCalculoIluminacao(
                ambiente = lampadaCaculada.ambiente,
                nomeAmbiente = lampadaCaculada.nomeAmbiente,
                largura = lampadaCaculada.largura,
                comprimento = lampadaCaculada.comprimento,
                tensao = lampadaCaculada.tensao,
                lumensAmbiente = lampadaCaculada.lumensAmbiente,
                area = lampadaCaculada.area,
                lumensLuminaria =lampadaCaculada.lumensLuminaria,
                potenciaLuminaria = lampadaCaculada.potenciaLuminaria,
                lumensTotal = lampadaCaculada.lumensTotal,
                totalLuminaria = lampadaCaculada.totalLuminaria,
                potenciaTotal = lampadaCaculada.potenciaTotal,
                amperagemCircuito =lampadaCaculada.amperagemCircuito
            )*/
        )
    }
}

