package com.example.nativeeletrichouse.presation.components.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.api.api_eletri_house.ApiEletricHouse
import com.example.nativeeletrichouse.main.core.navigation.HomeGraph
import kotlinx.coroutines.launch


@Composable
fun floatingActionbuttonGenerico(
     goTo:()->Unit,
     request:Any,
    api: Any
){
    val scope = rememberCoroutineScope()

    FloatingActionButton(
    onClick = {
/*        val request = RequestCalculateAmbiente(
            uiState.insertAmbiente,
            uiState.comprimento.toDouble(),
            uiState.insidenciaSolar,
            uiState.largura.toDouble(),
            uiState.potenciaLamp,
            uiState.quantEletrodomestico.toInt(),
            uiState.quantPessoaAmbient.toInt(),
            uiState.tensao.toInt()
        )*/
        scope.launch {
            val responseApi= api
            goTo
/*            navController.navigate(
                HomeGraph.ShowResultData(
                    id = 1,
                    ambiente = responseApi.ambiente,
                    largura = responseApi.largura.toFloat(),
                    comprimento = responseApi.comprimento.toFloat(),
                    area = responseApi.area.toFloat(),
                    lumensAmbiente = responseApi.lumensAmbiente,
                    lumensLuminaria = responseApi.lumensLuminaria,
                    lumensTotal = responseApi.lumensTotal.toFloat(),
                    potenciaLuminaria = responseApi.potenciaLuminaria.toFloat(),
                    totalLuminaria = responseApi.totalLuminaria.toFloat(),
                    potenciaTotal = responseApi.potenciaTotalIlum.toFloat(),
                    amperagemIluminacao = responseApi.amperagemCircuitoIlum.toFloat(),
                    quantToamda = responseApi.quantTomada,
                    potenciaTotalTomada = responseApi.potenciaTotalTomada.toFloat(),
                    amperagemTomada = responseApi.amperagemTomada.toFloat(),
                    tensao = responseApi.tensao,
                    quantPessoasAmbiente = responseApi.quantPessoasAmbiente,
                    quantEletrodomestico = responseApi.quantEletrodomestico,
                    btuAdicionalPorPessoa = responseApi.btuAdicionalPorPessoa,
                    btuAdicionalPorEletronico = responseApi.btuAdicionalPorEletronico,
                    btuPorM2 = responseApi.btuPorM2,
                    btusTotal = responseApi.btusTotal,
                    btuAdicionalInsidenciaRaioSolar = responseApi.btuAdicionalInsidenciaRaioSolar,
                    IDRS = responseApi.IDRS.toFloat(),
                    potenciaEletria = responseApi.potenciaEletriaAc.toString(),
                    amperagemCircuitoAc = responseApi.amperagemCircuitoAc.toString()
                )
            )*/
        }
    },
    modifier = Modifier.background(MaterialTheme.colorScheme.background),
    containerColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(
            painter = painterResource(id = R.drawable.calculadora),
            contentDescription = "Calcular",
            modifier = Modifier.size(30.dp,30.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}