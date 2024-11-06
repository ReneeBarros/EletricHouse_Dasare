package com.example.nativeeletrichouse.presation.uirequestdata

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nativeeletrichouse.presation.components.SpinnerTextDados
import com.example.nativeeletrichouse.presation.components.input.InputText
import com.example.nativeeletrichouse.presation.components.spinnerTextValue

@Composable
fun Requestdatascreen(
    listItem: Array<String>,
    itemSelecionado: String,
    label:String,
    selecionandoItens: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()

) {
    Column(modifier = modifier) {


        spinnerTextValue(
            listaItens = listItem,
            itemSelecionado = itemSelecionado,
            selecionandoItens = { selecionandoItens(it) },
            label = label,
            modifier = modifier
        )
    }
}

@Composable
fun AlertDialogConf(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Deletar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}


@Composable
fun UiRequestDataScreen(

    listAmbiente: Array<String>,
    listPotenciaLamp: Array<String>,
    listQuantidade: Array<String>,
    listTensao: Array<String>,
    onValueChangeNomeAmbiente:(String) ->Unit,
    onValueChangeLargura:(String) ->Unit,
    onValueChangeComp:(String) ->Unit,
    selecionandoAmbiente:(String) -> Unit,
    selecionandoLampada:(String) -> Unit,
    selecionandoTensao:(String) -> Unit,
    qtdePessoaAmbiente:(String) -> Unit,
    qtdeEletronicoAmb:(String) -> Unit,
    incidenciaSolarSim: () -> Unit = {},
    incidenciaSolarNao: () -> Unit = {},
    valuIncidenciaSolarSim:Boolean=false,
    valuIncidenciaSolarNao:Boolean=false,
    valueNomeAmbiente:String,
    valueLargura:String,
    valuecomp:String,
    itemselecAmbiente:String,
    itemselecQtdePessoa:String,
    itemselecQtdeEletr:String,
    itemselecLamp:String,
    itemselecTensao:String,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(15.dp),
        verticalArrangement = Arrangement.Center
    ) {
        //listAmbiente
        Requestdatascreen(
            listItem = listAmbiente,
            itemSelecionado = itemselecAmbiente,
            selecionandoItens = { selecionandoAmbiente(it) },
            label = "Escolher Ambiente"
            //modifier = Modifier.height(60.dp)
        )
        InputText(
            label = "Nome do Ambiente",
            onvalueChange = onValueChangeNomeAmbiente,
            value = valueNomeAmbiente

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
                onvalueChange = onValueChangeLargura,
                keyboardType = KeyboardType.Number,
                value = valueLargura

            )
            InputText(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp),
                label = "Comprimento",
                onvalueChange = onValueChangeComp,
                keyboardType = KeyboardType.Number,
                value = valuecomp
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //lista Potencia lampada
            Requestdatascreen(
                listItem = listPotenciaLamp,
                itemSelecionado =itemselecLamp ,
                selecionandoItens ={selecionandoLampada(it)} ,
                label = "(W)-Lampada",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp)
                    //.height(60.dp)
            )
            //lista tensão 220 ou 110
            Requestdatascreen(
                listItem = listTensao,
                itemSelecionado =itemselecTensao ,
                selecionandoItens ={selecionandoTensao(it)} ,
                label = "110 ou 220",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp)
                   // .height(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            //QTDE pessoas no ambiente
            Requestdatascreen(
                listItem = listQuantidade,
                itemSelecionado = itemselecQtdePessoa,
                label = "Qtde Pess.AMbiente",
                {qtdePessoaAmbiente(it)},
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp)

            )
            //QTDE eletronicos no ambiente
            Requestdatascreen(
                listItem = listQuantidade,
                itemSelecionado = itemselecQtdeEletr,
                label = "Qtde Pess.AMbiente" ,
                {qtdeEletronicoAmb(it)},
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp)

            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Card(
                modifier = Modifier.padding(top = 3.dp),
                shape = RoundedCornerShape(0.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = CardDefaults. cardColors(
                    containerColor = Color.Transparent
                )
            ){

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Tem Insidencia solar ?", textAlign = TextAlign.Center)

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically) {
                        Row {
                            RadioButton(
                                selected = valuIncidenciaSolarSim,
                                onClick = { incidenciaSolarSim() } ,
                                modifier = Modifier.size(20.dp,20.dp),
                                colors = RadioButtonDefaults.colors(
                                    disabledUnselectedColor = Color.Gray,
                                    selectedColor = Color.Green,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(text="Sim", fontWeight = FontWeight.Bold)
                        }
                        Row {
                            RadioButton(
                                selected = valuIncidenciaSolarNao,
                                onClick = { incidenciaSolarNao() },
                                modifier = Modifier.size(20.dp,20.dp),
                                colors = RadioButtonDefaults.colors(
                                    disabledUnselectedColor = Color.Gray,
                                    selectedColor = Color.Green,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(text="Não", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}