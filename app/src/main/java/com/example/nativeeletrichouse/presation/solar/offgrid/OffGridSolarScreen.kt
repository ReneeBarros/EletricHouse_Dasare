package com.example.nativeeletrichouse.presation.solar.offgrid

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.domain.Equipment
import com.example.nativeeletrichouse.domain.caculador_sistemaSolarOffgrid.CalcularSistemaSolarOffgrid
import com.example.nativeeletrichouse.presation.components.input.InputText
import com.example.nativeeletrichouse.presation.components.widget.TopBar
import com.example.nativeeletrichouse.presation.theme.Dimension
import com.example.nativeeletrichouse.presation.uirequestdata.Requestdatascreen


class OffGridSolarScreen(

    private val navController: NavController,
    private val stateHolder:OffGridSolarStateHolder

) {

    @Composable
    fun OffGridSolarUiScreen() {
        val uiState by stateHolder.uiState.collectAsState()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        var listEquipamentos = remember { mutableStateListOf<Equipment>() }

        Scaffold(
            topBar = {

                TopBar(" Calcular Cabo Eletrico", Modifier, navController = navController, 20.sp)
                // TopAppBar(title = { Text(text=" Calcular Cabo Eletrico") })
            },
            bottomBar = {

                BottomAppBar(
                    containerColor = Color.Transparent,
                    actions = {

                        NavigationBarItem(
                            selected = false,
                            onClick = {
                                CalcularSistemaSolarOffgrid().calcularSistemaSolarOffgrid(
                                    uiState=uiState,
                                    navController=navController,
                                    context = context,
                                    equipment = listEquipamentos,
                                    isLoading = true,
                                    scope = scope
                                )
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.calculadora),
                                    contentDescription = "Calcular",
                                    modifier = Modifier.size(30.dp, 30.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            label = { Text(text = "Calcular") }
                        )

                        NavigationBarItem(
                            modifier = Modifier.background(Color.Transparent),
                            colors = NavigationBarItemColors(
                                selectedTextColor = Color.Blue,
                                selectedIconColor = Color.Blue,
                                selectedIndicatorColor = Color.LightGray,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray,
                                disabledIconColor = MaterialTheme.colorScheme.primary,
                                disabledTextColor = MaterialTheme.colorScheme.primary
                            ),
                            selected = false,
                            onClick = {

                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.pasta_de_arquivo_sbg),
                                    contentDescription = "Calcular",
                                    modifier = Modifier.size(30.dp, 30.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            label = { Text(text = "Adicionar") }
                        )
                    }
                )
            }

        )
        { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    // cidade
                    InputText(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp),
                        label = "Cidade",
                        onvalueChange = { stateHolder.setCity(it) },
                        keyboardType = KeyboardType.Text,
                        value = uiState.city
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray
                    )

                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "Inserir equipamentos",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // inputText-para inserir equipamento + potencia + horas de uso
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ){
                            InputText(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(end = 5.dp),
                                label = "Equipamento",
                                onvalueChange = { stateHolder.setEquipment(it) },
                                keyboardType = KeyboardType.Text,
                                value = uiState.equipment
                            )

                            InputText(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(start = 5.dp),
                                label = "Potencia",
                                onvalueChange = {
                                    it.replace(",", ".")
                                    stateHolder.setEquipmentPower(it)
                                },
                                keyboardType = KeyboardType.Number,
                                value = uiState.equipmentPower.toString()
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            InputText(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding( end = 5.dp),
                                label = "QTDE",
                                onvalueChange = {
                                    it.replace(",", ".")
                                     stateHolder.setQuantityEquipment(it)
                                },
                                keyboardType = KeyboardType.Number,
                                value = uiState.quantityEquipment.toString()
                            )

                            InputText(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(start = 5.dp),
                                label = "Hrs de uso diário",
                                onvalueChange = {
                                    it.replace(",", ".")
                                    stateHolder.setHoursDailyUse(it)
                                },
                                keyboardType = KeyboardType.Number,
                                value = uiState.hoursDailyUse
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))


                        Card(
                            modifier = Modifier.fillMaxWidth().padding(5.dp).height(200.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )

                        ) {
                            ShowEquipamentos(listEquipamentos, context = context, uiState = uiState)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        TextButton(
                            onClick = {
                                listEquipamentos.add(
                                    Equipment(
                                        uiState.equipment,
                                        uiState.equipmentPower.replace(",",".").toFloat(),
                                        uiState.hoursDailyUse.toFloat(),
                                        uiState.quantityEquipment.toInt()
                                    )
                                )
                                stateHolder.setEquipment("")
                            },
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                            colors =  ButtonDefaults.textButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.White,
                            )
                        ) {
                            Text(
                                text = "Inserir",
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // inputText-para inserir autonomia da bateria
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    InputText(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp),
                        label = "Dias de Autonomia",
                        onvalueChange = { stateHolder.setAutonomyDay(it) },
                        keyboardType = KeyboardType.Number,
                        value = uiState.autonomyDay.toString()
                    )

                    InputText(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp),
                        label = "Temperatura Ambiente",
                        onvalueChange = { stateHolder.setTemperaturaAmbiente(it) },
                        keyboardType = KeyboardType.Number,
                        value = uiState.temperaturaAmbiente.toString()
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Requestdatascreen(
                        listItem = stringArrayResource(R.array.VoltagemBateria),
                        itemSelecionado = uiState.voltagyBattery.toString(),
                        selecionandoItens = { stateHolder.setVoltagyBattery(it) },
                        label = "Voltagem da Bateria",
                        modifier = Modifier.weight(1f).height(70.dp)
                            .padding(end = 5.dp)
                    )

                    Requestdatascreen(
                        listItem = stringArrayResource(R.array.VoltagemdoBancodeBateria),
                        itemSelecionado = uiState.voltagyBatteryBank.toString(),
                        selecionandoItens = { stateHolder.setVoltagyBatteryBank(it) },
                        label = "Voltagem do Banco de Bateria",
                        modifier = Modifier
                            .weight(1f)
                            .height(70.dp)
                            .padding(end = 5.dp
                        )
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Requestdatascreen(
                        listItem = stringArrayResource(R.array.Prof_Descarga_Banco_Bateria),
                        itemSelecionado = uiState.dischargeDepthOfBatteryBank.toString(),
                        selecionandoItens = { stateHolder.setDischargeDepthOfBatteryBank(it) },
                        label = "Profundidade de descarga da bateria",
                        modifier = Modifier.weight(1f).height(70.dp)
                            .padding(end = 5.dp)
                    )

                    Requestdatascreen(
                        listItem = stringArrayResource(R.array.metodoInstalacao),
                        itemSelecionado = uiState.metodoInstalacao,
                        selecionandoItens = { stateHolder.setMetodoInstalacao(it) },
                        label = "Metodo de Instalação do Sistema",
                        modifier = Modifier.weight(1f).height(70.dp)
                            .padding(end = 5.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    InputText(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp),
                        label = "Cap.Bateria em AH",
                        onvalueChange = { stateHolder.setCapacidadeBateriaAH(it) },
                        keyboardType = KeyboardType.Number,
                        value = uiState.capacityBateryAH
                    )

                    /*InputText(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp),
                        label = "Potencia Painel Solar",
                        onvalueChange = { stateHolder.setPowerOfPainelModule(it) },
                        keyboardType = KeyboardType.Number,
                        value = uiState.PowerOfPainelModule
                    )*/

                    Requestdatascreen(
                        listItem = stringArrayResource(R.array.Potencia_Painel),
                        itemSelecionado = uiState.PowerOfPainelModule,
                        selecionandoItens = { stateHolder.setPowerOfPainelModule(it) },
                        label = "Potencia Painel Solar",
                        modifier = Modifier.weight(1f).height(70.dp)
                            .padding(end = 5.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun ShowEquipamentos(
        equipamentos: MutableList<Equipment>,
        context: Context,
        uiState: OffGridSolarUiState

        ){

        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(start = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                items = equipamentos
            ){ equi ->
                Spacer(modifier = Modifier.height(Dimension.small))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = equi.equipmentName,
                        modifier = Modifier.weight(1f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = equi.equipmentPower.toString(),
                        modifier = Modifier.weight(0.5f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = equi.hoursDailyUse.toString(),
                        modifier = Modifier.weight(0.5f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    )

                    IconButton(
                        modifier = Modifier.width(110.dp),
                        onClick = {
                            stateHolder.setShowAlertDialog(value = true)
                            equipamentos.remove(equi)
                        }

                    ) {
                        Row(
                            modifier = Modifier.padding(start = 2.dp, bottom = 1.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Deletar", tint = Color.Red)
                        }
                    }
                }
            }
        }
    }
}

