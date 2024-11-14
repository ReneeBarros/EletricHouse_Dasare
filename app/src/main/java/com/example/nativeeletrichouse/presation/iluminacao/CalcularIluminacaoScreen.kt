package com.example.nativeeletrichouse.presation.iluminacao

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.data.reponse.ResponseCalculoIluminacao
import com.example.nativeeletrichouse.domain.caculador_iluminacao.CalcularIluminacao
import com.example.nativeeletrichouse.error.erro_cal_Ilum.erroEntradaDadosIluminacao
import com.example.nativeeletrichouse.main.core.navigation.navgation_ilumnacao.NavegationIlum
import com.example.nativeeletrichouse.presation.components.widget.FloatingActionButtonCustomize
import com.example.nativeeletrichouse.presation.components.widget.TopBar
import com.example.nativeeletrichouse.presation.uirequestdata.UiRequestDataScreenWc

class CalcularIluminacaoScreen {

    private val stateHolder = CalcularIluminacaoStateHolder()

    @Composable
    fun CalcularIluminacaoUiScreen(
        navController: NavController,
        context: Context
    ) {
        val uiState by stateHolder.uiState.collectAsState()
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = {
                TopBar(
                    title = uiState.title,
                    modifier = Modifier,
                    navController = navController,
                    fontSize = 20.sp
                )
            },
            floatingActionButton = {
                FloatingActionButtonCustomize(
                    modifier = Modifier,
                    onclik = {

                        val message =
                            erroEntradaDadosIluminacao(
                                uiState = uiState,
                                context = context
                            )
                        if(message){

                            CalcularIluminacao().calculadorIluminacao(
                                uiState = uiState,
                                context = context,
                                scope = scope,
                                navController = navController
                            )
                        }
                    },
                    icon = Icon(painter = painterResource(id = R.drawable.calculadora_icon),
                        contentDescription ="", modifier = Modifier.size(25.dp)),
                    name = "Calcular"
                )
            },
            floatingActionButtonPosition = FabPosition.Center

        ) {paddingValue ->

            Box(
                modifier = Modifier
                    .padding(paddingValues = paddingValue)
                    .fillMaxSize()
            ) {
                UiRequestDataScreenWc(
                    listAmbiente = stringArrayResource( R.array.ListaAmbientes),
                    listPotenciaLamp = stringArrayResource( R.array.ListaLuminaria),
                    listTensao = stringArrayResource(R.array.Tensao),
                    selecionandoAmbiente = {stateHolder.setRoomType(it)},
                    selecionandoLampada = {stateHolder.setpotenciaLamp(it)},
                    selecionandoTensao = {stateHolder.setVoltage(it.toInt())},
                    onValueChangeNomeAmbiente = {stateHolder.setRoomName(it)},
                    onValueChangeLargura = {stateHolder.setWidth(it)},
                    onValueChangeComp = {stateHolder.setLength(it)},
                    valueNomeAmbiente = uiState.roomName,
                    valueLargura = uiState.width,
                    valuecomp = uiState.length,
                    itemselecAmbiente = uiState.roomType,
                    itemselecTensao = uiState.voltage.toString(),
                    itemselecLamp = uiState.lampPower.toString(),
                )
            }
        }
    }
}