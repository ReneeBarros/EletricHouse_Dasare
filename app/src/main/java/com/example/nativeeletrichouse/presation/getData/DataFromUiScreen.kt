package com.example.nativeeletrichouse.presation.getData


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.api.api_eletri_house.ApiEletricHouse
import com.example.nativeeletrichouse.data.reponse.ResponseCaculateAmbiente
import com.example.nativeeletrichouse.data.request.RequestCalcularArCond
import com.example.nativeeletrichouse.data.request.RequestCalcularTomada
import com.example.nativeeletrichouse.data.request.RequestCalculateAmbiente
import com.example.nativeeletrichouse.data.request.RequestCalculateIluminacao
import com.example.nativeeletrichouse.main.core.navigation.HomeGraph
import com.example.nativeeletrichouse.presation.components.Spinner
import com.example.nativeeletrichouse.presation.components.widget.CampoEntradaDados
import com.example.nativeeletrichouse.presation.components.widget.CardGetDataUi
import com.example.nativeeletrichouse.presation.components.widget.CardInputThreeCamp
import com.example.nativeeletrichouse.presation.components.widget.ThreeSpinner
import com.example.nativeeletrichouse.presation.theme.Dimension
import com.example.nativeeletrichouse.presation.uirequestdata.UiRequestDataScreen
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class DataFromUiScreen(

) {

    private val stateHolder = DataFromUiStateHolder()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Data(
        iluminacao: Boolean,
        tomada: Boolean,
        arCondicionado: Boolean,
        home: Boolean,
        ambiente: String,
        nomeAmbiente: String,
        navController: NavController

    ) {
        val uiState by stateHolder.uiState.collectAsState()
        var arcond = arCondicionado
        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        val listAmbienteHome: MutableList<RequestCalculateAmbiente> = mutableListOf()
        var loading by remember { mutableStateOf(false) }


        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = "Eletric House", fontSize = 35.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Transparent,
                    actions = {
                        if(home){
                            NavigationBarItem(

                                selected = true,
                                onClick = {

                                    if(
                                        uiState.insertAmbiente.isEmpty()||
                                        uiState.largura.isEmpty()||
                                        uiState.comprimento.isEmpty()||
                                        uiState.tensao.isEmpty()||
                                        uiState.insertNameAmb.isEmpty()
                                    ){
                                        //Toast.makeText(context, "Necessario Preencher Todos os Campos", Toast.LENGTH_SHORT).show()
                                        FancyToast.makeText(context,
                                            "Necessario Preencher Todos os Campos",
                                            FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show()
                                    }
                                    else{
                                        val request = RequestCalculateAmbiente(
                                            uiState.insertAmbiente,
                                            uiState.insertNameAmb,
                                            uiState.comprimento.replace(",", ".").toDouble(),
                                            uiState.insidenciaSolar,
                                            uiState.largura.replace(",", ".").toDouble(),
                                            uiState.potenciaLamp,
                                            uiState.quantEletrodomestico.toInt(),
                                            uiState.quantPessoaAmbient.toInt(),
                                            uiState.tensao.toInt()
                                        )

                                        if(listAmbienteHome.isEmpty()){
                                            listAmbienteHome.add(request)

                                            scope.launch {
                                                loading = true
                                                val listAmbienteCalculado = mutableListOf<String>()
                                                val json = Json{prettyPrint = true}
                                                val responseApi = ApiEletricHouse().apiCalcularListaDeAmbiente(listAmbienteHome)

                                                responseApi.forEach{
                                                     listAmbienteCalculado.add(json.encodeToString(ResponseCaculateAmbiente.serializer(),it))
                                                }
                                                navController.navigate(
                                                    route = "ResultadoApi/${true}/${listAmbienteCalculado}"
                                                )
                                            }

                                        }else{

                                            scope.launch {
                                                loading = true
                                                val listAmbienteCalculado = mutableListOf<String>()
                                                val json = Json{prettyPrint = true}
                                                val responseApi = ApiEletricHouse().apiCalcularListaDeAmbiente(listAmbienteHome)

                                                responseApi.forEach{
                                                    listAmbienteCalculado.add(json.encodeToString(ResponseCaculateAmbiente.serializer(),it))
                                                }
                                                navController.navigate(
                                                    route = "ResultadoApi/${true}/${listAmbienteCalculado}"
                                                )
                                            }
                                        }

                                        /*scope.launch {
                                            val responseApi = ApiEletricHouse().apiCalcularAmbiente(request)

                                            navController.navigate(
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
                                                    amperagemCircuitoAc = responseApi.amperagemCircuitoAc.toString(),
                                                    calcular = "ambiente"
                                                )
                                            )
                                        }*/
                                    }
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
                                selected = uiState.isSelected,
                                onClick = {
                                    stateHolder.seIsSelected(uiState.isSelected)
                                    if(
                                        uiState.largura.isEmpty()||uiState.comprimento.isEmpty()||uiState.insertAmbiente=="Escolher Ambiente"
                                    ){
                                        Toast.makeText(context, "Necessario Preencher Todos os Campos", Toast.LENGTH_SHORT).show()

                                    }
                                    else{
                                        val request = RequestCalculateAmbiente(
                                            uiState.insertAmbiente,
                                            uiState.insertNameAmb,
                                            uiState.comprimento.replace(",", ".").toDouble(),
                                            uiState.insidenciaSolar,
                                            uiState.largura.replace(",", ".").toDouble(),
                                            uiState.potenciaLamp,
                                            uiState.quantEletrodomestico.toInt(),
                                            uiState.quantPessoaAmbient.toInt(),
                                            uiState.tensao.toInt()
                                        )
                                        listAmbienteHome.add(request)
                                        FancyToast.makeText(context, "Adicionado", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()

                                    }

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
                        //botao Iluminacao
                        if(iluminacao){
                            NavigationBarItem(
                                selected = true,
                                onClick = {

                                    if(
                                        uiState.insertAmbiente.isEmpty()||
                                        uiState.largura.isEmpty()||
                                        uiState.comprimento.isEmpty()||
                                        uiState.tensao.isEmpty()
                                    ){
                                        Toast.makeText(context, "Necessario Preencher Todos os Campos", Toast.LENGTH_SHORT).show()
                                    }
                                    else{
                                        val request = RequestCalculateIluminacao(
                                            uiState.insertAmbiente,
                                            uiState.comprimento.replace(",", ".").toDouble(),
                                            uiState.largura.replace(",", ".").toDouble(),
                                            uiState.potenciaLamp,
                                            uiState.tensao.toInt()
                                        )
                                        scope.launch {
                                            val responseApi = ApiEletricHouse().apiIluminacao(request)

                                            navController.navigate(
                                                HomeGraph.ShowResultData(
                                                    id = 2,
                                                    ambiente = responseApi.ambiente,
                                                    largura = responseApi.largura.toFloat(),
                                                    comprimento = responseApi.comprimento.toFloat(),
                                                    area = responseApi.area.toFloat(),
                                                    lumensAmbiente = responseApi.lumensAmbiente,
                                                    lumensLuminaria = responseApi.lumensLuminaria,
                                                    lumensTotal = responseApi.lumensTotal.toFloat(),
                                                    potenciaLuminaria = responseApi.potenciaLuminaria.toFloat(),
                                                    totalLuminaria = responseApi.totalLuminaria.toFloat(),
                                                    potenciaTotal = responseApi.potenciaTotal.toFloat(),
                                                    amperagemIluminacao = responseApi.amperagemCircuito.toFloat(),
                                                    tensao = responseApi.tensao,
                                                    calcular = "iluminacao"
                                                )
                                            )
                                        }
                                    }
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
                        }
                        //botao tomada
                        if(tomada){
                            NavigationBarItem(
                                selected = true,
                                onClick = {

                                    if(
                                        uiState.insertAmbiente.isEmpty()||
                                        uiState.largura.isEmpty()||
                                        uiState.comprimento.isEmpty()||
                                        uiState.tensao.isEmpty()
                                    ){
                                        //Toast.makeText(context, "Necessario Preencher Todos os Campos", Toast.LENGTH_SHORT).show()
                                        FancyToast.makeText(context,"Necessario Preencher Todos os Campos",FancyToast.LENGTH_SHORT,FancyToast.WARNING,false)

                                    }
                                    else{
                                        val request = RequestCalcularTomada(
                                            ambiente = uiState.insertAmbiente,
                                            largura = uiState.largura.toDouble(),
                                            comprimento = uiState.comprimento.toDouble(),
                                            tensao = uiState.tensao.toInt()

                                        )
                                        scope.launch {
                                            val responseApi = ApiEletricHouse().apiTomada(request)

                                            navController.navigate(
                                                HomeGraph.ShowResultData(
                                                    ambiente = responseApi.ambiente,
                                                    largura = uiState.largura.toFloat(),
                                                    comprimento = uiState.comprimento.toFloat(),
                                                    tensao = uiState.tensao.toInt(),
                                                    quantToamda = responseApi.quantTomada,
                                                    amperagemTomada = responseApi.amperagemTomada.toFloat(),
                                                    potenciaTotalTomada = responseApi.potenciaTotal.toFloat(),
                                                    calcular = "tomada"
                                                )
                                            )
                                        }
                                    }
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
                        }
                        //botao ar-condicionado
                        if(arCondicionado){
                            NavigationBarItem(
                                selected = true,
                                onClick = {

                                    if(
                                        uiState.insertAmbiente.isEmpty()||
                                        uiState.largura.isEmpty()||
                                        uiState.comprimento.isEmpty()||
                                        uiState.tensao.isEmpty()
                                    ){
                                        Toast.makeText(context, "Necessario Preencher Todos os Campos", Toast.LENGTH_SHORT).show()
                                    }
                                    else{
                                        val request = RequestCalcularArCond(
                                            comprimento = uiState.comprimento.replace(",", ".").toDouble(),
                                            largura = uiState.largura.replace(",", ".").toDouble(),
                                            tensao = uiState.tensao.toInt(),
                                            insidenciaRaioSolar = uiState.insidenciaSolar,
                                            quantPessoasAmbiente = uiState.quantPessoaAmbient.toInt(),
                                            quantEletrodomestico = uiState.quantEletrodomestico.toInt()

                                        )
                                        scope.launch {
                                            val responseApi = ApiEletricHouse().apiIArCond(request)

                                            navController.navigate(
                                                HomeGraph.ShowResultData(
                                                    id = 3,
                                                    ambiente = uiState.insertAmbiente,
                                                    largura = uiState.largura.toFloat(),
                                                    comprimento = uiState.comprimento.toFloat(),
                                                    area = (uiState.largura.toFloat() * uiState.comprimento.toFloat()),
                                                    tensao = uiState.tensao.toInt(),
                                                    quantPessoasAmbiente = responseApi.quantPessoasAmbiente,
                                                    quantEletrodomestico = responseApi.quantEletrodomestico,
                                                    btuAdicionalPorPessoa = responseApi.btuAdicionalPorPessoa,
                                                    btuAdicionalInsidenciaRaioSolar = responseApi.btuAdicionalInsidenciaRaioSolar,
                                                    btuAdicionalPorEletronico = responseApi.btuAdicionalPorEletronico,
                                                    btuPorM2 = responseApi.btuPorM2,
                                                    btusTotal = responseApi.btusTotal,
                                                    IDRS = responseApi.IDRS.toFloat(),
                                                    potenciaEletria = responseApi.potenciaEletria,
                                                    amperagemCircuitoAc = responseApi.amperagemCircuitoAc,
                                                    calcular = "arCond"
                                                )
                                            )
                                        }
                                    }
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
                        }
                    }
                )
            }

        ) { paddingStad ->


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingStad)
            ) {
                Row(modifier = Modifier.fillMaxWidth( ),
                    horizontalArrangement = Arrangement.SpaceAround) {
                    val listaImag = listOf(
                        painterResource(R.drawable.calculadora),
                        painterResource(R.drawable.lampada_acesa),
                        painterResource(R.drawable.arcondsbg),
                        painterResource(R.drawable.tomada_simples_2p_t_10a)
                    ).forEach {
                        Image(
                            it, contentDescription = "Calculo",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(width = 50.dp, height = 50.dp)
                        )
                    }
                }

                if(loading) {
                    Column(Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(80.dp),
                            strokeWidth = Dimension.medium,
                            color = Color.Blue
                        )
                        Text(text="Carregando",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light,
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }

                if (home) {
                    if(uiState.insertAmbiente=="Banheiro"|| uiState.insertAmbiente =="Escada/Dispensa/Garagem/"){


                        CampoEntradaDados(

                            onclick1 = { nameAmb ->
                                stateHolder.setInsertNameAmb(nameAmb)
                            },
                            onclick2 = { largura ->
                                stateHolder.setLargura(largura)
                            },
                            onclick3 = { comp ->
                                stateHolder.setComprimento(comp)
                            },
                            onclick4Radio1Sim = {
                                stateHolder.setInsidenciaSolar(value = true)
                            },
                            onclick4Radio2Nao = {
                                stateHolder.setInsidenciaSolar(value = false)
                            },
                            onclick4Radio3220 = {
                                stateHolder.setTensao220(value = true)
                                stateHolder.setTensao110(value = false)
                                stateHolder.setTensao("220")
                            },
                            onclick4Radio4110 = {
                                stateHolder.setTensao220(value = false)
                                stateHolder.setTensao110(value = true)
                                stateHolder.setTensao("110")
                            },
                            painterImg1 = painterResource(R.drawable.calculadora),
                            painterImg2 = painterResource(R.drawable.lampada_acesa),
                            painterImg3 = painterResource(R.drawable.arcondsbg),
                            painterImg4 = painterResource(R.drawable.tomada_simples_2p_t_10a),
                            label1 = "Nome Ambiente",
                            label2 = "Largura",
                            label3 = "Comprimento",
                            value1 = uiState.insertNameAmb,
                            value2 = uiState.largura,
                            value3 = uiState.comprimento,
                            selecionado1 = uiState.insidenciaSolar,
                            selecionado2 = !uiState.insidenciaSolar,
                            selecionado3 = uiState.tensao220,
                            selecionado4 = uiState.tensao110,
                            keyboardType1 = KeyboardType.Text,
                            keyboardType2 = KeyboardType.Number,
                            keyboardType3 = KeyboardType.Number,
                            listaItensAmb = stringArrayResource( R.array.ListaAmbientes),
                            listaItensLampada = stringArrayResource( R.array.ListaLuminaria),
                            listaItensPessoas = stringArrayResource( R.array.Numeros),
                            listaItensEletronic = stringArrayResource( R.array.Numeros),
                            itemSelecionadoAmb = uiState.insertAmbiente,
                            selecionandoItensAmb = {
                                stateHolder.setInsertambiente(it)
                            },
                            itemSelecionadoLamp = "(W)Lampada: ${uiState.potenciaLamp}",
                            selecionandoLampada = {
                                stateHolder.setpotenciaLamp(it.replace(",", ".").toDouble())
                            },

                            itemSelecionadoPessoa = "0",
                            selecionandoQuantPessoa = {
                                stateHolder.setquantPessoaAmbient("0")
                            },
                            itemSelecionadoEletronico = "0",
                            selecionandoQuantEletronico = {
                                stateHolder.setQuantEletrodomestico("0")
                            }
                        )
                    }
                    else{
                        UiRequestDataScreen(
                            listAmbiente = stringArrayResource( R.array.ListaAmbientes),
                            listPotenciaLamp = stringArrayResource( R.array.ListaLuminaria),
                            listTensao = stringArrayResource(R.array.Tensao),
                            listQuantidade = stringArrayResource( R.array.Numeros),
                            selecionandoAmbiente = {stateHolder.setInsertambiente(it)},
                            selecionandoLampada = {stateHolder.setpotenciaLamp(it.replace(",", ".").toDouble())},
                            selecionandoTensao = {stateHolder.setTensao(it)},
                            onValueChangeNomeAmbiente = {stateHolder.setInsertNameAmb(it)},
                            onValueChangeLargura = {stateHolder.setLargura(it)},
                            onValueChangeComp = {stateHolder.setComprimento(it)},
                            incidenciaSolarSim = {stateHolder.setInsidenciaSolar(true)},
                            incidenciaSolarNao = {stateHolder.setInsidenciaSolar(false)},
                            valuIncidenciaSolarSim = !uiState.insidenciaSolar,
                            valuIncidenciaSolarNao = uiState.insidenciaSolar,
                            valueNomeAmbiente = uiState.insertNameAmb,
                            valueLargura = uiState.largura,
                            valuecomp = uiState.comprimento,
                            itemselecAmbiente = uiState.insertAmbiente,
                            itemselecTensao = uiState.tensao,
                            itemselecLamp = uiState.potenciaLamp.toString(),
                            itemselecQtdePessoa = uiState.quantPessoaAmbient,
                            itemselecQtdeEletr = uiState.quantEletrodomestico,
                            qtdePessoaAmbiente = {stateHolder.setquantPessoaAmbient(it)},
                            qtdeEletronicoAmb = {stateHolder.setQuantEletrodomestico(it)}
                        )

                        /*CampoEntradaDados(

                            onclick1 = { nameAmb ->
                                stateHolder.setInsertNameAmb(nameAmb)
                            },
                            onclick2 = { largura ->
                                stateHolder.setLargura(largura)
                            },
                            onclick3 = { comp ->
                                stateHolder.setComprimento(comp)
                            },
                            onclick4Radio1Sim = {
                                stateHolder.setInsidenciaSolar(value = true)
                            },
                            onclick4Radio2Nao = {
                                stateHolder.setInsidenciaSolar(value = false)
                            },
                            onclick4Radio3220 = {
                                stateHolder.setTensao220(value = true)
                                stateHolder.setTensao110(value = false)
                                stateHolder.setTensao("220")
                            },
                            onclick4Radio4110 = {
                                stateHolder.setTensao220(value = false)
                                stateHolder.setTensao110(value = true)
                                stateHolder.setTensao("110")
                            },
                            painterImg1 = painterResource(R.drawable.calculadora),
                            painterImg2 = painterResource(R.drawable.lampada_acesa),
                            painterImg3 = painterResource(R.drawable.arcondsbg),
                            painterImg4 = painterResource(R.drawable.tomada_simples_2p_t_10a),
                            label1 = "Nome Ambiente",
                            label2 = "Largura",
                            label3 = "Comprimento",
                            value1 = uiState.insertNameAmb,
                            value2 = uiState.largura,
                            value3 = uiState.comprimento,
                            selecionado1 = uiState.insidenciaSolar,
                            selecionado2 = !uiState.insidenciaSolar,
                            selecionado3 = uiState.tensao220,
                            selecionado4 = uiState.tensao110,
                            keyboardType1 = KeyboardType.Text,
                            keyboardType2 = KeyboardType.Number,
                            keyboardType3 = KeyboardType.Number,
                            listaItensAmb = stringArrayResource( R.array.ListaAmbientes),
                            listaItensLampada = stringArrayResource( R.array.ListaLuminaria),
                            listaItensPessoas = stringArrayResource( R.array.Numeros),
                            listaItensEletronic = stringArrayResource( R.array.Numeros),
                            itemSelecionadoAmb = uiState.insertAmbiente,
                            selecionandoItensAmb = {
                                stateHolder.setInsertambiente(it)
                            },
                            itemSelecionadoLamp = "(W)Lampada: ${uiState.potenciaLamp}",
                            selecionandoLampada = {
                                stateHolder.setpotenciaLamp(it.replace(",", ".").toDouble())
                            },

                            itemSelecionadoPessoa = uiState.quantPessoaAmbient,
                            selecionandoQuantPessoa = {
                                stateHolder.setquantPessoaAmbient(it)
                            },
                            itemSelecionadoEletronico = uiState.quantEletrodomestico,
                            selecionandoQuantEletronico = {
                                stateHolder.setQuantEletrodomestico(it)
                            }
                        )*/
                    }
                }
                if (iluminacao) {

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
                                .fillMaxWidth()
                                .padding(Dimension.medium),
                            verticalArrangement = Arrangement.Center
                        ) {
/*                            Spinner(
                                listaItens = stringArrayResource(id = R.array.ListaAmbientes),
                                itemSelecionado = uiState.insertAmbiente
                            ) { amb ->
                                stateHolder.setInsertambiente(amb)
                            }*/

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent),
                                shape = RoundedCornerShape(30.dp),
                                value = uiState.insertNameAmb,
                                onValueChange = { value ->
                                    stateHolder.setInsertNameAmb(value)
                                },
                                label = { Text(text = "Nome do Ambiente") },
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    disabledTextColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    focusedTextColor = Color.Gray,
                                    unfocusedTextColor = Color.Gray,
                                    disabledLabelColor = Color.Gray,
                                    unfocusedContainerColor = Color.Transparent
                                )
                            )
                        }


                        CardGetDataUi(
                            onclick1 = { largura ->
                                stateHolder.setLargura(largura)
                            },
                            onclick2 = { comp ->
                                stateHolder.setComprimento(comp)
                            },
                            onclick3 = { tensao ->
                                stateHolder.setTensao(tensao)
                            },
                            painter = painterResource(R.drawable.calculadora),
                            label1 = "largura",
                            label2 = "Comprimento",
                            label3 = "Tensão",
                            value1 = uiState.largura,
                            value2 = uiState.comprimento,
                            value3 = uiState.tensao,
                            keyboardType1 = KeyboardType.Number,
                            keyboardType2 = KeyboardType.Number,
                            keyboardType3 = KeyboardType.Number,
                            threeInput = true,
                            twoInput = false
                        )


                        CardInputThreeCamp(
                            onclick1 = { tens ->
                                stateHolder.setTensao(tens)
                            },
                            onclick2 = { area ->
                                stateHolder.setArea(area)
                            },
                            label1 = "Tensão",
                            label2 = "Area",
                            value1 = uiState.tensao,
                            value2 = uiState.area,
                            keyboardType1 = KeyboardType.Number,
                            keyboardType2 = KeyboardType.Number,
                            painter = painterResource(id = R.drawable.lampada_acesa),
                            listitem = stringArrayResource(id = R.array.ListaLuminaria),
                            getItem = {
                                stateHolder.setpotenciaLamp(it.replace(",", ".").toDouble())
                            },
                            itemselecionado = "Lampada(W): " + uiState.potenciaLamp
                        )
                    }


                }
                if (arcond) {
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
                                .fillMaxWidth()
                                .padding(Dimension.medium),
                            verticalArrangement = Arrangement.Center
                        ) {
/*                            Spinner(
                                listaItens = stringArrayResource(id = R.array.ListaAmbientes),
                                itemSelecionado = uiState.insertAmbiente
                            ) { amb ->
                                stateHolder.setInsertambiente(amb)
                            }*/

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent),
                                shape = RoundedCornerShape(30.dp),
                                value = uiState.insertNameAmb,
                                onValueChange = { value ->
                                    stateHolder.setInsertNameAmb(value)
                                },
                                label = { Text(text = "Nome do Ambiente") },
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    disabledTextColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    focusedTextColor = Color.Gray,
                                    unfocusedTextColor = Color.Gray,
                                    disabledLabelColor = Color.Gray,
                                    unfocusedContainerColor = Color.Transparent
                                )
                            )
                        }


                        CardGetDataUi(
                            onclick1 = { largura ->
                                stateHolder.setLargura(largura)
                            },
                            onclick2 = { comp ->
                                stateHolder.setComprimento(comp)
                            },
                            onclick3 = { tensao ->
                                stateHolder.setTensao(tensao)
                            },
                            painter = painterResource(R.drawable.calculadora),
                            label1 = "largura",
                            label2 = "Comprimento",
                            label3 = "Tensão",
                            value1 = uiState.largura,
                            value2 = uiState.comprimento,
                            value3 = uiState.tensao,
                            keyboardType1 = KeyboardType.Number,
                            keyboardType2 = KeyboardType.Number,
                            keyboardType3 = KeyboardType.Number,
                            threeInput = true,
                            twoInput = false
                        )


                        CardInputThreeCamp(
                            onclick1 = { tens ->
                                stateHolder.setTensao(tens)
                            },
                            onclick2 = { area ->
                                stateHolder.setArea(area)
                            },
                            label1 = "Tensão",
                            label2 = "Area",
                            value1 = uiState.tensao,
                            value2 = uiState.area,
                            keyboardType1 = KeyboardType.Number,
                            keyboardType2 = KeyboardType.Number,
                            painter = painterResource(id = R.drawable.lampada_acesa),
                            listitem = stringArrayResource(id = R.array.ListaLuminaria),
                            getItem = {
                                stateHolder.setpotenciaLamp(it.replace(",", ".").toDouble())
                            },
                            itemselecionado = "Lampada(W): " + uiState.potenciaLamp
                        )
                    }

                    CardGetDataUi(
                        onclick1 = { largura ->
                            stateHolder.setLargura(largura)
                        },
                        onclick2 = { comp ->
                            stateHolder.setComprimento(comp)
                        },
                        onclick3 = { tensao ->
                            stateHolder.setTensao(tensao)
                        },
                        painter = painterResource(R.drawable.calculadora),
                        label1 = "largura",
                        label2 = "Comprimento",
                        label3 = "Tensão",
                        value1 = uiState.largura,
                        value2 = uiState.comprimento,
                        value3 = uiState.tensao,
                        keyboardType1 = KeyboardType.Number,
                        keyboardType2 = KeyboardType.Number,
                        keyboardType3 = KeyboardType.Number,
                        threeInput = true,
                        twoInput = false
                    )

                    ThreeSpinner(
                        painter = painterResource(id = R.drawable.arcondsbg),
                        quantPessoaSelec = uiState.quantPessoaAmbient,
                        quantEletroSelec = uiState.quantEletrodomestico,
                        listitem = stringArrayResource(id = R.array.Numeros),
                        listConf = stringArrayResource(id = R.array.Tensao),
                        getItemPessoa = { pessoa ->
                            stateHolder.setquantPessoaAmbient(pessoa)
                        },
                        getItemEletro = { eletronico ->
                            stateHolder.setQuantEletrodomestico(eletronico)
                        },
                        insidenciaSolar = uiState.insidenciaSolar,
                        getInsidenciaSolar = { conf ->
                            stateHolder.setInsidenciaSolar(conf)
                        }
                    )
                }
                if (tomada) {

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
                                .fillMaxWidth()
                                .padding(Dimension.medium),
                            verticalArrangement = Arrangement.Center
                        ) {
/*                            Spinner(
                                listaItens = stringArrayResource(id = R.array.ListaAmbientes),
                                itemSelecionado = uiState.insertAmbiente
                            ) { amb ->
                                stateHolder.setInsertambiente(amb)
                            }*/

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent),
                                shape = RoundedCornerShape(30.dp),
                                value = uiState.insertNameAmb,
                                onValueChange = { value ->
                                    stateHolder.setInsertNameAmb(value)
                                },
                                label = { Text(text = "Nome do Ambiente") },
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    disabledTextColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    focusedTextColor = Color.Gray,
                                    unfocusedTextColor = Color.Gray,
                                    disabledLabelColor = Color.Gray,
                                    unfocusedContainerColor = Color.Transparent
                                )
                            )
                        }


                        CardGetDataUi(
                            onclick1 = { largura ->
                                stateHolder.setLargura(largura)
                            },
                            onclick2 = { comp ->
                                stateHolder.setComprimento(comp)
                            },
                            onclick3 = { tensao ->
                                stateHolder.setTensao(tensao)
                            },
                            painter = painterResource(R.drawable.calculadora),
                            label1 = "largura",
                            label2 = "Comprimento",
                            label3 = "Tensão",
                            value1 = uiState.largura,
                            value2 = uiState.comprimento,
                            value3 = uiState.tensao,
                            keyboardType1 = KeyboardType.Number,
                            keyboardType2 = KeyboardType.Number,
                            keyboardType3 = KeyboardType.Number,
                            threeInput = true,
                            twoInput = false
                        )


                        CardInputThreeCamp(
                            onclick1 = { tens ->
                                stateHolder.setTensao(tens)
                            },
                            onclick2 = { area ->
                                stateHolder.setArea(area)
                            },
                            label1 = "Tensão",
                            label2 = "Area",
                            value1 = uiState.tensao,
                            value2 = uiState.area,
                            keyboardType1 = KeyboardType.Number,
                            keyboardType2 = KeyboardType.Number,
                            painter = painterResource(id = R.drawable.lampada_acesa),
                            listitem = stringArrayResource(id = R.array.ListaLuminaria),
                            getItem = {
                                stateHolder.setpotenciaLamp(it.replace(",", ".").toDouble())
                            },
                            itemselecionado = "Lampada(W): " + uiState.potenciaLamp
                        )
                    }

                    CardGetDataUi(
                        onclick1 = { largura ->
                            stateHolder.setLargura(largura)
                        },
                        onclick2 = { comp ->
                            stateHolder.setComprimento(comp)
                        },
                        onclick3 = { tensao ->
                            stateHolder.setTensao(tensao)
                        },
                        painter = painterResource(R.drawable.calculadora),
                        label1 = "largura",
                        label2 = "Comprimento",
                        label3 = "Tensão",
                        value1 = uiState.largura,
                        value2 = uiState.comprimento,
                        value3 = uiState.tensao,
                        keyboardType1 = KeyboardType.Number,
                        keyboardType2 = KeyboardType.Number,
                        keyboardType3 = KeyboardType.Number,
                        threeInput = true,
                        twoInput = false
                    )

                }
            }

        }
    }
}