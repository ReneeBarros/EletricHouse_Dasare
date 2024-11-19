package com.example.nativeeletrichouse.presation.tomada

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.api.api_eletri_house.ApiEletricHouse
import com.example.nativeeletrichouse.domain.calculador_ar_cond.CalcularArCondicionado
import com.example.nativeeletrichouse.domain.calculador_tomada.CalcularTomada
import com.example.nativeeletrichouse.error.erro_cal_arcond.erroEntradaDadosArCond
import com.example.nativeeletrichouse.error.erro_cal_tomada.erroEntradaDadosTomada
import com.example.nativeeletrichouse.presation.components.input.InputText
import com.example.nativeeletrichouse.presation.components.widget.FloatingActionButtonCustomize
import com.example.nativeeletrichouse.presation.components.widget.ProgressIndicator
import com.example.nativeeletrichouse.presation.components.widget.TopBar
import com.example.nativeeletrichouse.presation.uirequestdata.Requestdatascreen

class CalcularTomadaScreen {

    private val stateHolder = CalcularTomadaStateHolder()

    @Composable
    fun CalcularTomadaUiScreen(
        navController: NavController,
        context: Context,
        api: ApiEletricHouse
    ) {
        val uiState by stateHolder.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        var loading by remember { mutableStateOf(false) }


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

                        val error=  erroEntradaDadosTomada(uiState = uiState, context =context)

                        if (error){
                            loading = true
                            CalcularTomada().calculadorTomada(
                                uiState = uiState,
                                navController = navController,
                                fromApi = api,
                                scope = scope,
                                context = context
                            )
                        }
                    },
                    icon = Icon(
                        painter = painterResource(id = R.drawable.calculadora_icon),
                        contentDescription = "", modifier = Modifier.size(25.dp)
                    ),
                    name = "Calcular"
                )
            },
            floatingActionButtonPosition = FabPosition.Center

        ) { paddingValue ->

            Box(
                modifier = Modifier
                    .padding(paddingValues = paddingValue)
                    .fillMaxSize()
            ) {

                if (loading) {
                    ProgressIndicator()
                } else {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(15.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        //listAmbiente
                        Requestdatascreen(
                            listItem = stringArrayResource(R.array.ListaAmbientes),
                            itemSelecionado = uiState.roomType,
                            selecionandoItens = { stateHolder.setRoomType(it) },
                            label = "Escolher Ambiente"
                            //modifier = Modifier.height(60.dp)
                        )
                        InputText(
                            label = "Nome do Ambiente",
                            onvalueChange = { stateHolder.setRoomName(it) },
                            value = uiState.roomName

                        )
                        Spacer(modifier = Modifier.height(30.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            InputText(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 5.dp),
                                label = "Largura",
                                onvalueChange = { stateHolder.setWidth(it) },
                                keyboardType = KeyboardType.Number,
                                value = uiState.width

                            )
                            InputText(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 5.dp),
                                label = "Comprimento",
                                onvalueChange = { stateHolder.setLength(it) },
                                keyboardType = KeyboardType.Number,
                                value = uiState.length
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            //lista tensão 220 ou 110
                            Requestdatascreen(
                                listItem = stringArrayResource(R.array.Tensao),
                                itemSelecionado = uiState.voltage.toString(),
                                selecionandoItens = { stateHolder.setVoltage(it.toInt()) },
                                label = "110 ou 220",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 5.dp)
                                // .height(60.dp)
                            )
                        }
                    }
                }
            }
        }

    }
}