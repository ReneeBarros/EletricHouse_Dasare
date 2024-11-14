package com.example.nativeeletrichouse.presation.calcularcabo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.domain.calcularcabo.CalculadorDeCabo
import com.example.nativeeletrichouse.domain.calcularcabo.ShowCaboCalculate
import com.example.nativeeletrichouse.domain.calcularcabo.intefaces.CalculoCaboEletricoInterFace
import com.example.nativeeletrichouse.error.erro_cal_cabo.ErroCalcularCabo
import com.example.nativeeletrichouse.presation.components.input.InputText
import com.example.nativeeletrichouse.presation.components.widget.TopBar
import com.example.nativeeletrichouse.presation.theme.Dimension
import com.example.nativeeletrichouse.presation.uirequestdata.Requestdatascreen
import com.shashank.sony.fancytoastlib.FancyToast

@OptIn(ExperimentalMaterial3Api::class)
class CalcularCaboScreen(

    private val caboCalculator:CalculoCaboEletricoInterFace,
    private val navController: NavController
) {

    private val stateHolder = CalcularCaboStateHolder()

    @Composable
    fun CalcularCaboUiScreen() {

        val uiState by stateHolder.uiState.collectAsState()
        val context = LocalContext.current
        //var listCabo:MutableList<ShowCaboCalculate> = mutableListOf()
        var listChecked by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        val items = remember { mutableStateListOf<ShowCaboCalculate>() }


        Scaffold(
            topBar = {

                TopBar(" Calcular Cabo Eletrico", Modifier, navController = navController,20.sp)
               // TopAppBar(title = { Text(text=" Calcular Cabo Eletrico") })
            },

            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

                        if (ErroCalcularCabo().erroEntradaDados(uiState = uiState, context = context)) {

                            val calCabo = CalculadorDeCabo().calculadarCabos(
                                correnteEnt = uiState.corrente.replace(",", ".").toDouble(),
                                tensaoEnt = uiState.tensao,
                                potenciaEnt = uiState.pontecia.replace(",", ".").toDouble(),
                                fatoPotenciaEnt = uiState.fatoPotencia,
                                modeloInstalacaoCabos = uiState.modeloInstalacaoCabos,
                                condutoresCarregado = uiState.condutoresCarregado,
                                quantDeCircuito = uiState.quantDeCircuito,
                                calculoCabo = caboCalculator
                            )

                            items.add(calCabo)
                            FancyToast.makeText(context,"Cabo Calculado",
                                FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
                            listChecked = true
                        }
                    },
                    modifier = Modifier
                        .size(75.dp, 70.dp),
                    containerColor = Color.White,

                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(painter = painterResource(id = R.drawable.calculadora_icon),
                            contentDescription ="", modifier = Modifier.size(25.dp))
                        Text(text = "Calcular")
                    }

                }
            },

            floatingActionButtonPosition = FabPosition.Center,


        ){paddingValues ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    //lista tensão 220 ou 110
                    Requestdatascreen(
                        listItem = stringArrayResource(R.array.Tensao),
                        itemSelecionado = uiState.tensao.toString(),
                        selecionandoItens = {
                            stateHolder.setTensao(it.toInt())
                        },
                        label = "Tensão",
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp)
                    )

                    Requestdatascreen(
                        listItem = stringArrayResource(R.array.fator_potencia),
                        itemSelecionado = uiState.tensao.toString(),
                        selecionandoItens = { stateHolder.setFatoPotencia(it.toDouble()) },
                        label = "Fator Potencia",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    InputText(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp),
                        label = "Potencia",
                        onvalueChange = {stateHolder.setPotencia(it)},
                        keyboardType = KeyboardType.Number,
                        value = uiState.pontecia
                    )

                    InputText(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp),
                        label = " Se tiver Corrente",
                        onvalueChange = {stateHolder.setCorrente(it)},
                        keyboardType = KeyboardType.Number,
                        value = uiState.corrente
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Requestdatascreen(
                    listItem = stringArrayResource(R.array.modeloInstalacaoDosCabos),
                    itemSelecionado = uiState.modeloInstalacaoCabos,
                    selecionandoItens = {stateHolder.setModeloInstCabo(it) },
                    label = "Modelo Instalação",
                    modifier = Modifier.height(70.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //lista Potencia lampada
                    Requestdatascreen(
                        listItem = stringArrayResource(R.array.condutoresCarregado),
                        itemSelecionado =uiState.condutoresCarregado,
                        selecionandoItens ={stateHolder.setCondutoresCarregado(it)} ,
                        label = "Condutores Carregado",
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp)
                        //.height(60.dp)
                    )

                    Requestdatascreen(
                        listItem = stringArrayResource(R.array.quantCircuitosNoEletroduto),
                        itemSelecionado =uiState.quantDeCircuito,
                        selecionandoItens ={ stateHolder.setQuantCircuito(it) } ,
                        label = "Quant Circuito ?",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp)
                        // .height(60.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                if (listChecked){

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            , colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                        border = BorderStroke(1.dp, color = Color.LightGray)
                    ) {
                        LazyColumn(Modifier.fillMaxSize()) {
                            items(
                                items = items
                            ) { cabo ->
                                Spacer(modifier = Modifier.height(Dimension.small))
                                ShowCabo(
                                    tensao=cabo.tensao,
                                    potencia=cabo.pontecia,
                                    corrente=cabo.corrente,
                                    fatorDePotencia=cabo.fatoPotencia,
                                    metodoDeInstalação=cabo.modeloInstalacaoCabos,
                                    condutorCarregado=cabo.condutoresCarregado,
                                    quantidadeDeCircuito=cabo.quantDeCircuito,
                                    caboCalculado=cabo.caboCalculado,
                                    correnteDeSuporteCabo = cabo.correnteSuportadoPeloCabo
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ShowCabo(
        tensao:Int,
        potencia:Double,
        corrente:Double,
        fatorDePotencia:Double,
        metodoDeInstalação: String,
        condutorCarregado: Int,
        quantidadeDeCircuito:Int,
        caboCalculado: Double,
        correnteDeSuporteCabo: Double

    ){
        val listItem = listOf(
            "Tensão: ",
            "Potencia: ",
            "Corrente: ",
            "Fator de Potencia: ",
            "Metodo de Instalação: ",
            "Condutor Carregado: ",
            "Quant. De Circuito Agrupado: ",
            "Cabo Calculado (mm²): ",
            "Corrente de Cabo (A): "

        )
        val InfoToCabo = listOf(
            "${tensao}",
            "${potencia}",
            "${corrente}",
            "${fatorDePotencia}",
            metodoDeInstalação,
            "${condutorCarregado}",
            "${quantidadeDeCircuito}",
            "${caboCalculado}",
            "${correnteDeSuporteCabo}"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
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
                        text = InfoToCabo[index],
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }



}

