package com.example.nativeeletrichouse.presation.components.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.presation.components.SpinnerentradaDados
import com.example.nativeeletrichouse.presation.components.input.InputText
import com.example.nativeeletrichouse.presation.theme.Dimension

@Composable
fun CampoEntradaDados(
    onclick1: (String) -> Unit = {},
    onclick2: (String) -> Unit = {},
    onclick3: (String) -> Unit = {},
    onclick4Radio1Sim: () -> Unit = {},
    onclick4Radio2Nao: () -> Unit = {},
    onclick4Radio3220: () -> Unit = {},
    onclick4Radio4110: () -> Unit = {},
    painterImg1: Painter,
    painterImg2: Painter,
    painterImg3: Painter,
    painterImg4: Painter,
    label1: String = "",
    label2: String = "",
    label3: String = "",
    placeholder: String = "",
    value1: String = "",
    value2: String = "",
    value3: String = "",
    selecionado1: Boolean = false,
    selecionado2: Boolean = false,
    selecionado3: Boolean = false,
    selecionado4: Boolean = false,
    keyboardType1: KeyboardType = KeyboardType.Text,
    keyboardType2: KeyboardType = KeyboardType.Text,
    keyboardType3: KeyboardType = KeyboardType.Text,
    threeInput: Boolean = false,
    twoInput: Boolean = false,
    listaItensAmb: Array<String>,
    listaItensLampada: Array<String>,
    listaItensPessoas: Array<String>,
    listaItensEletronic: Array<String>,
    itemSelecionadoAmb: String,
    selecionandoItensAmb: (String) -> Unit = {},
    itemSelecionadoLamp: String,
    selecionandoLampada: (String) -> Unit = {},
    itemSelecionadoPessoa: String,
    selecionandoQuantPessoa: (String) -> Unit = {},
    itemSelecionadoEletronico: String,
    selecionandoQuantEletronico: (String) -> Unit = {},

) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.big),
        elevation = CardDefaults.elevatedCardElevation(Dimension.medium),
        shape = RoundedCornerShape(Dimension.medium),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            disabledContainerColor = Color.White
        )

    ) {
        Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(Dimension.medium)
                    .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 5.dp, alignment = Alignment.CenterVertically)

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Image(
                        painterImg1, contentDescription = "Calculo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(width = 50.dp, height = 50.dp)
                    )
                    Image(
                        painterImg2, contentDescription = "Iluminacao",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(width = 50.dp, height = 50.dp)
                    )
                    Image(
                        painterImg3, contentDescription = "Tomada",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(width = 50.dp, height = 50.dp)
                    )
                    Image(
                        painterImg4, contentDescription = "Ar-Condicionado",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(width = 50.dp, height = 50.dp)
                    )
                }

                if(itemSelecionadoAmb=="Banheiro"|| itemSelecionadoAmb =="Escada/Dispensa/Garagem/"){
                    //************* informacoes gerais-para calculo *********************//
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            //Spinner Selecao Ambiente
                            SpinnerentradaDados(
                                listaItens = listaItensAmb,
                                itemSelecionado = itemSelecionadoAmb,
                                selecionandoItens ={selecionandoItensAmb(it)}
                            )

                            //nome ambiente
                            InputText(
                                label = label1,
                                onvalueChange = {
                                    onclick1(it)
                                },
                                placeholder = placeholder,
                                value = value1,
                                keyboardType = keyboardType1
                            )

                            //largura
                            InputText(

                                label = label2,
                                value = value2,
                                onvalueChange = { item ->
                                    onclick2(item)
                                },
                                keyboardType = keyboardType2
                            )

                            //Comprimento
                            InputText(
                                label = label3,
                                value = value3,
                                onvalueChange = { item ->
                                    onclick3(item)
                                },
                                keyboardType = keyboardType2
                            )

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Qual Tensão/Volts Da Cidade ?")

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Row {
                                        RadioButton(
                                            selected = selecionado3,
                                            onClick = {
                                                onclick4Radio3220()
                                            },
                                            modifier = Modifier.size(20.dp,20.dp),
                                            colors = RadioButtonDefaults.colors(
                                                disabledUnselectedColor = Color.Gray,
                                                selectedColor = Color.Green,
                                                unselectedColor = Color.Gray
                                            )
                                        )
                                        Spacer(modifier = Modifier.width(Dimension.small))
                                        Text(text="220V", fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(modifier = Modifier.width(Dimension.small))
                                    Row {
                                        RadioButton(
                                            selected = selecionado4,
                                            onClick = { onclick4Radio4110() },
                                            modifier = Modifier.size(20.dp,20.dp),
                                            colors = RadioButtonDefaults.colors(
                                                disabledUnselectedColor = Color.Gray,
                                                selectedColor = Color.Green,
                                                unselectedColor = Color.Gray
                                            )
                                        )
                                        Spacer(modifier = Modifier.width(Dimension.small))
                                        Text(text="110-127V", fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                    //*************************************************************

                    // informacoes para iluminacao
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            //Spinner Potencia Lampada
                            SpinnerentradaDados(
                                listaItens = listaItensLampada,
                                itemSelecionado = itemSelecionadoLamp,
                                selecionandoItens ={selecionandoLampada(it)}
                            )
                        }
                    }

                    //*************************************************************

                }
                else{

                    //************* informacoes gerais-para calculo *********************//
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            //Spinner Selecao Ambiente
                            SpinnerentradaDados(
                                listaItens = listaItensAmb,
                                itemSelecionado = itemSelecionadoAmb,
                                selecionandoItens ={selecionandoItensAmb(it)}
                            )

                            //nome ambiente
                            InputText(
                                label = label1,
                                onvalueChange = {
                                    onclick1(it)
                                },
                                placeholder = placeholder,
                                value = value1,
                                keyboardType = keyboardType1
                            )

                            //largura
                            InputText(

                                label = label2,
                                value = value2,
                                onvalueChange = { item ->
                                    onclick2(item)
                                },
                                keyboardType = keyboardType2
                            )

                            //Comprimento
                            InputText(
                                label = label3,
                                value = value3,
                                onvalueChange = { item ->
                                    onclick3(item)
                                },
                                keyboardType = keyboardType2
                            )

                            Card(
                                modifier = Modifier.padding(top = 3.dp),
                                shape = RoundedCornerShape(0.dp),
                                border = BorderStroke(1.dp, Color.Black),
                                colors = CardDefaults. cardColors(
                                    containerColor = Color.Transparent
                                )
                            ){

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = stringResource(R.string.QualTensao))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround
                                    ) {
                                        Row {
                                            RadioButton(
                                                selected = selecionado3,
                                                onClick = {
                                                    onclick4Radio3220()
                                                },
                                                modifier = Modifier.size(20.dp,20.dp),
                                                colors = RadioButtonDefaults.colors(
                                                    disabledUnselectedColor = Color.Gray,
                                                    selectedColor = Color.Green,
                                                    unselectedColor = Color.Gray
                                                )
                                            )
                                            Spacer(modifier = Modifier.width(Dimension.small))
                                            Text(text="220V", fontWeight = FontWeight.Bold)
                                        }
                                        Spacer(modifier = Modifier.width(Dimension.small))
                                        Row {
                                            RadioButton(
                                                selected = selecionado4,
                                                onClick = { onclick4Radio4110() },
                                                modifier = Modifier.size(20.dp,20.dp),
                                                colors = RadioButtonDefaults.colors(
                                                    disabledUnselectedColor = Color.Gray,
                                                    selectedColor = Color.Green,
                                                    unselectedColor = Color.Gray
                                                )
                                            )
                                            Spacer(modifier = Modifier.width(Dimension.small))
                                            Text(text="110-127V", fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //*************************************************************

                    // informacoes para iluminacao
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            //Spinner Potencia Lampada
                            SpinnerentradaDados(
                                listaItens = listaItensLampada,
                                itemSelecionado = itemSelecionadoLamp,
                                selecionandoItens ={selecionandoLampada(it)}
                            )
                        }

                        /*                    Image(
                                                painterImg2, contentDescription = "Iluminacao",
                                                contentScale = ContentScale.Fit,
                                                modifier = Modifier.size(width = 50.dp, height = 50.dp)
                                            )*/
                    }
                    //*************************************************************

                    //************* informacoes para ar-condicionado *********************//

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            //Spinner Selecao Quantidade de Pessoas
                            SpinnerentradaDados(
                                listaItens = listaItensPessoas,
                                itemSelecionado = itemSelecionadoPessoa,
                                selecionandoItens ={selecionandoQuantPessoa(it)}
                            )
                            //Spinner Selecao Quantidade de Eletronico
                            SpinnerentradaDados(
                                listaItens = listaItensPessoas,
                                itemSelecionado = itemSelecionadoEletronico,
                                selecionandoItens ={selecionandoQuantEletronico(it)}
                            )


                            Card(
                                modifier = Modifier.padding(top = 3.dp),
                                shape = RoundedCornerShape(0.dp),
                                border = BorderStroke(1.dp, Color.Black),
                                colors = CardDefaults. cardColors(
                                    containerColor = Color.Transparent
                                )
                            ){


                                Column(
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(text = "Tem Insidencia solar ?")

                                    Row(modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround,
                                        verticalAlignment = Alignment.CenterVertically) {
                                        Row {
                                            RadioButton(
                                                selected = selecionado1,
                                                onClick = { onclick4Radio1Sim() },
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
                                                selected = selecionado2,
                                                onClick = { onclick4Radio2Nao() },
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

                        /*                    Image(
                                                painterImg3, contentDescription = "Ar-Condicionado",
                                                contentScale = ContentScale.Fit,
                                                modifier = Modifier.size(width = 50.dp, height = 50.dp)
                                            )*/
                    }
                    //*************************************************************

                }








/*                //nome comprimento
                InputText(

                    label = label3,
                    value = value3,
                    onvalueChange = { item ->
                        onclick3(item)
                    },
                    keyboardType = keyboardType3
                )*/

              /*  InputText(
                    label = label1,
                    onvalueChange = {
                        onclick1(it)
                    },
                    placeholder = placeholder,
                    value = value1,
                    keyboardType = keyboardType1
                )

                InputText(

                    label = label2,
                    value = value2,
                    onvalueChange = { item ->
                        onclick2(item)
                    },
                    keyboardType = keyboardType2
                )

                InputText(
                    label = label1,
                    onvalueChange = {
                        onclick1(it)
                    },
                    placeholder = placeholder,
                    value = value1,
                    keyboardType = keyboardType1
                )
*/            }

    }
}
