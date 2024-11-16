package com.example.nativeeletrichouse.main.core.navigation.navigation_arcond

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.nativeeletrichouse.data.reponse.ResponseCalculoArCond
import com.example.nativeeletrichouse.dto.DtoResponseEletricHouse
import com.example.nativeeletrichouse.presation.arcondicionado.ShowResultArcondScreen


class NavigationArCond {


    fun navigationArCond(
        navController: NavController,
        ac: DtoResponseEletricHouse.DtoArCond
    ) {

        navController.navigate(
            ac
        )

       /* navController.navigate(
            DtoResponseEletricHouse.DtoArCond(
                IDRS = ac.IDRS,
                amperagemCircuitoAc = ac.amperagemCircuitoAc,
                btuAdicionalInsidenciaRaioSolar = ac.btuAdicionalInsidenciaRaioSolar,
                btuAdicionalPorEletronico = ac.btuAdicionalPorEletronico,
                btuAdicionalPorPessoa = ac.btuAdicionalPorPessoa,
                btuPorM2 = ac.btuPorM2,
                btusTotal = ac.btusTotal,
                potenciaEletria = ac.potenciaEletria,
                quantEletrodomestico = ac.quantEletrodomestico,
                quantPessoasAmbiente = ac.quantPessoasAmbiente,
                caboArCond = ac.caboArCond,
                comprimento = ac.comprimento,
                area = ac.area,
                nomeAmbiente = ac.nomeAmbiente,
                largura = ac.largura,
                ambiente = ac.ambiente,
                tensao = ac.tensao

            )
        )*/
    }
}
