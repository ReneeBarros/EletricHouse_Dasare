package com.example.nativeeletrichouse.presation.showresult

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.data.db.entity.AmbienteEntity
import com.example.nativeeletrichouse.data.reponse.ResponseCaculateAmbiente
import com.example.nativeeletrichouse.dto.DtoResponseEletricHouse
import com.example.nativeeletrichouse.main.core.navigation.HomeGraph
import com.example.nativeeletrichouse.pdfgeneration.PdfCreater
import com.example.nativeeletrichouse.presation.theme.Dimension
import com.example.nativeeletrichouse.presation.uirequestdata.AlertDialogConf
import com.example.nativeeletrichouse.viewmodel.ViewModelAmbiente
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class ShowResultScreen {


    private val stateHolder = ShowResultStateHolder()



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowResultApp(
        eletricHouseComplete: DtoResponseEletricHouse.EletricHouseComplete,
        calcular: String
    ) {
        val itemresult = listOf(
            "${eletricHouseComplete.id}",
            eletricHouseComplete.ambiente,
            "${eletricHouseComplete.largura}",
            "${eletricHouseComplete.comprimento}",
            "${eletricHouseComplete.tensao}",
            "${eletricHouseComplete.area}",
            "${eletricHouseComplete.lumensAmbiente}",
            "${eletricHouseComplete.lumensLuminaria}",
            "${eletricHouseComplete.lumensTotal}",
            "${eletricHouseComplete.potenciaLuminaria}",
            "${eletricHouseComplete.totalLuminaria}",
            "${eletricHouseComplete.potenciaTotalLampadas}",
            "${eletricHouseComplete.amperagemIluminacao}",
            "${eletricHouseComplete.quantToamda}",
            "${eletricHouseComplete.potenciaTotalTomada}",
            "${eletricHouseComplete.amperagemTomada}",
            "${eletricHouseComplete.btuPorM2}",
            "${eletricHouseComplete.quantPessoasAmbiente}",
            "${eletricHouseComplete.quantEletrodomestico}",
            "${eletricHouseComplete.btuAdicionalPorPessoa}",
            "${eletricHouseComplete.btuAdicionalPorEletronico}",
            "${eletricHouseComplete.btuAdicionalInsidenciaRaioSolar}",
            "${eletricHouseComplete.btusTotal}",
            "${eletricHouseComplete.IDRS}",
            "${eletricHouseComplete.potenciaEletria}",
            "${eletricHouseComplete.amperagemCircuitoAc}",
        )

        val itemResultTomada = listOf(
            "${eletricHouseComplete.id}",
            eletricHouseComplete.ambiente,
            "${eletricHouseComplete.largura}",
            "${eletricHouseComplete.comprimento}",
            "${eletricHouseComplete.tensao}",
            "${eletricHouseComplete.quantToamda}",
            "${eletricHouseComplete.potenciaTotalTomada}",
            "${eletricHouseComplete.amperagemTomada}",
        )
        val itemResultArCond = listOf(
            "${eletricHouseComplete.id}",
            eletricHouseComplete.ambiente,
            "${eletricHouseComplete.largura}",
            "${eletricHouseComplete.comprimento}",
            "${eletricHouseComplete.area}",
            "${eletricHouseComplete.tensao}",
            "${eletricHouseComplete.btuPorM2}",
            "${eletricHouseComplete.quantPessoasAmbiente}",
            "${eletricHouseComplete.quantEletrodomestico}",
            "${eletricHouseComplete.btuAdicionalPorPessoa}",
            "${eletricHouseComplete.btuAdicionalPorEletronico}",
            "${eletricHouseComplete.btuAdicionalInsidenciaRaioSolar}",
            "${eletricHouseComplete.btusTotal}",
            "${eletricHouseComplete.IDRS}",
            "${eletricHouseComplete.potenciaEletria}",
            "${eletricHouseComplete.amperagemCircuitoAc}",
        )

        val uiState by stateHolder.uiState.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = uiState.title) }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                ) {

                    Row(
                        Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = "Salvar")
                            Text(text = "Salvar", fontSize = 16.sp)
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Deletar")
                            Text(text = "Deletar", fontSize = 16.sp)

                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.Create, contentDescription = "Gerar PDF")
                            Text(text = "Gerar PDF", fontSize = 16.sp)
                        }

                    }
                }
            }

        ) { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {


                var listItem: List<String> = listOf()

                if (calcular == "ambiente") {
                    listItem = listOf(
                        "ID:",
                        "Ambiente:",
                        "Largura:",
                        "Comp:",
                        "Tensão:",
                        "Area:",
                        "LumensAmbiente:",
                        "LumensLampada:",
                        "Lumens Total:",
                        "Potencia da Lampada:",
                        "Total de Lampada:",
                        "Potencia Total:",
                        "(A)Lampada:",
                        "Quant Tomada:",
                        "Potencia Tomada:",
                        "Corrente Tomada:",
                        "btusM²:",
                        "QuantPessoa no Ambiente:",
                        "QuantEletronico no Ambiente:",
                        "btusAdicionalPessoa:",
                        "btusAdicionaleletronico:",
                        "btusAdicionalIndSolar:",
                        "btusTotal:",
                        "IDRS:",
                        "(W)Arcond:",
                        "(A)Arcond:"
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
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
                                    text = itemresult[index],
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }
                    }
                } else if (calcular == "iluminacao") {
                    listItem = listOf(
                        "ID:",
                        "Ambiente:",
                        "Largura:",
                        "Comprimento:",
                        "Tensão:",
                        "Area:",
                        "LumensAmbiente",
                        "LumensLampada:",
                        "Lumens Total:",
                        "Potencia da Lampada:",
                        "Total de Lampada:",
                        "Potencia Total:"
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
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
                                    text = itemresult[index],
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }
                    }
                } else if (calcular == "tomada") {
                    listItem = listOf(
                        "ID:",
                        "Ambiente:",
                        "Largura:",
                        "Comprimento:",
                        "Tensão:",
                        "Quantidade de Tomada:",
                        "Potencia Tomada:",
                        "Corrente Tomada:"
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
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
                                    text = itemResultTomada[index],
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }
                    }
                } else if (calcular == "arCond") {
                    listItem = listOf(
                        "ID:",
                        "Ambiente:",
                        "Largura:",
                        "Comp:",
                        "Tensão:",
                        "Area:",
                        "btusM²:",
                        "QuantPessoa no Ambiente:",
                        "QuantEletronico no Ambiente:",
                        "btusAdicionalPessoa:",
                        "btusAdicionaleletronico:",
                        "btusAdicionalIndSolar:",
                        "btusTotal:",
                        "IDRS:",
                        "(W)Arcond:",
                        "(A)Arcond:"
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
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
                                    text = itemResultArCond[index],
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ApresentationResultApi(
        ambienteFromDbRoom:List<AmbienteEntity>,
        ambienteCalculado: List<ResponseCaculateAmbiente>,
        fromBd: Boolean,
        navController: NavController
    ) {
        val uiState by stateHolder.uiState.collectAsState()
        val viewmodel = koinViewModel<ViewModelAmbiente>()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val listaAmbienteFromDb by viewmodel.ambientesFromDb.collectAsState()

        val createDocumentLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { it ->
                it.data?.data?.let {
                    context.contentResolver.openOutputStream(it)?.let {
                        scope.launch {
                            PdfCreater().createPdf(
                                it,
                                response = ambienteCalculado
                            )
                        }
                    }
                }
            }
        )



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
                    } }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    actions = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {

                            if (fromBd) {
                                NavigationBarItem(
                                    modifier = Modifier.size(30.dp),
                                    selected = false,
                                    onClick = {
                                        /*scope.launch {}*/
                                        try{
                                            viewmodel.addAmbiente(ambienteCalculado)
                                            FancyToast.makeText(
                                                context, "salvo", FancyToast.LENGTH_SHORT,
                                                FancyToast.SUCCESS, false
                                            ).show()
                                        }catch (e:Exception){
                                            e.message
                                        }

                                           /* navController.navigate(
                                                HomeGraph.Home
                                            )*/

                                    },
                                    icon = {
                                        Icon(
                                            modifier = Modifier.size(30.dp),
                                            painter = painterResource(R.drawable.salve),
                                            contentDescription = "Salvar"
                                        )
                                    },
                                    label = {
                                        Text(text = "Salvar", fontSize = 16.sp)
                                    }
                                )
                            }
                            //DELETAR
                            NavigationBarItem(
                                modifier = Modifier.size(30.dp),
                                selected = false,
                                onClick = {
                                    stateHolder.setShowAlertDialog(value = true)
                                    //viewmodel.deletarAmbiente(ambienteCalculado)

                                },
                                icon = {
                                    Icon(
                                        modifier = Modifier.size(30.dp),
                                        painter = painterResource(R.drawable.botaoapagar),
                                        contentDescription = "Deletar"
                                    )
                                },
                                label = {
                                    Text(text = "Deletar", fontSize = 16.sp)
                                }
                            )
                            // criar PDF
                            NavigationBarItem(
                                modifier = Modifier.size(30.dp),
                                selected = false,
                                onClick = {
                                    Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                                        type = "application/pdf"
                                        putExtra(Intent.EXTRA_TITLE,"AMBIENTE.pdf")
                                    }.also{
                                        createDocumentLauncher.launch(it)
                                    }
                                    FancyToast.makeText(
                                        context,"PDF Criado",
                                        FancyToast.LENGTH_SHORT,
                                        FancyToast.SUCCESS,
                                        false
                                    ).show()
                                },
                                icon = {
                                    Icon(
                                        modifier = Modifier.size(30.dp),
                                        painter = painterResource(R.drawable.baixarpdf),
                                        contentDescription = "PDF"
                                    )
                                },
                                label = {
                                    Text(text = "PDF", fontSize = 16.sp)
                                }
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        paddingValues
                    )
            ) {

                val viewmodelT = koinViewModel<ViewModelAmbiente>()
                if (!fromBd){
                    
                    LazyColumn {
                        items(
                            items =listaAmbienteFromDb
                        ){ ambiente ->
                            Spacer(modifier = Modifier.height(Dimension.small))
                            TabeladeDadosAmbienteFromDbRoom(
                                ambienteFromDbRoom = ambiente,
                                context = context,
                                viewmodel = viewmodelT,
                            ) { stateHolder.setShowAlertDialog(value = true) }
                        }
                    }
                }else{
                    LazyColumn() {
                        items(
                            items = ambienteCalculado
                        ) { ambiente ->
                            Spacer(modifier = Modifier.height(Dimension.small))
                            TabeladeDadosAmbiente(
                                ambienteCalculado = ambiente,
                                context = context
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun TabeladeDadosAmbiente(
        ambienteCalculado: ResponseCaculateAmbiente,
        context: Context,
    ) {
        val scope = rememberCoroutineScope()
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val ambienteListado = listOf(
                ambienteCalculado.ambiente,
                ambienteCalculado.nomeAmbiente,
                "${ambienteCalculado.largura}",
                "${ambienteCalculado.comprimento}",
                "${ambienteCalculado.tensao}",
                ambienteCalculado.area,
                "${ambienteCalculado.lumensAmbiente}",
                "${ambienteCalculado.lumensLuminaria}",
                "${ambienteCalculado.lumensTotal}",
                "${ambienteCalculado.potenciaLuminaria}",
                "${ambienteCalculado.totalLuminaria}",
                "${ambienteCalculado.potenciaTotalIlum}",
                "${ambienteCalculado.amperagemCircuitoIlum}",
                "${ambienteCalculado.quantTomada}",
                "${ambienteCalculado.potenciaTotalTomada}",
                "${ambienteCalculado.amperagemTomada}",
                "${ambienteCalculado.btuPorM2}",
                "${ambienteCalculado.quantPessoasAmbiente}",
                "${ambienteCalculado.quantEletrodomestico}",
                "${ambienteCalculado.btuAdicionalPorPessoa}",
                "${ambienteCalculado.btuAdicionalPorEletronico}",
                "${ambienteCalculado.btuAdicionalInsidenciaRaioSolar}",
                "${ambienteCalculado.btusTotal}",
                "${ambienteCalculado.IDRS}",
                "${ambienteCalculado.potenciaEletriaAc}",
                "${ambienteCalculado.amperagemCircuitoAc}",
            )
            var listItem: List<String> = listOf()
            listItem = listOf(
                "Ambiente:",
                "Nome do Ambiente:",
                "Largura:",
                "Comp:",
                "Tensão:",
                "Area:",
                "LumensAmbiente:",
                "LumensLampada:",
                "Lumens Total:",
                "Potencia da Lampada:",
                "Total de Lampada:",
                "Potencia Total:",
                "(A)Lampada:",
                "Quant Tomada:",
                "Potencia Tomada:",
                "Corrente Tomada:",
                "btusM²:",
                "QuantPessoa no Ambiente:",
                "QuantEletronico no Ambiente:",
                "btusAdicionalPessoa:",
                "btusAdicionaleletronico:",
                "btusAdicionalIndSolar:",
                "btusTotal:",
                "IDRS:",
                "(W)Arcond:",
                "(A)Arcond:"
            )

            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                colors = CardColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Gray,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Black
                ),
                border = BorderStroke(width = 1.dp, color = Color.LightGray)

            ) {
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
                                text = ambienteListado[index],
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun TabeladeDadosAmbienteFromDbRoom(
        ambienteFromDbRoom: AmbienteEntity,
        context: Context,
        viewmodel: ViewModelAmbiente,
        isChecked:()->Unit,

    ) {
        val uiState by stateHolder.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {

            val ambienteListado = listOf(
                "${ambienteFromDbRoom.id}",
                ambienteFromDbRoom.ambiente,
                ambienteFromDbRoom.nomeAmbiente,
                "${ambienteFromDbRoom.largura}",
                "${ambienteFromDbRoom.comprimento}",
                "${ambienteFromDbRoom.tensao}",
                ambienteFromDbRoom.area,
                "${ambienteFromDbRoom.lumensAmbiente}",
                "${ambienteFromDbRoom.lumensLuminaria}",
                "${ambienteFromDbRoom.lumensTotal}",
                "${ambienteFromDbRoom.potenciaLuminaria}",
                "${ambienteFromDbRoom.totalLuminaria}",
                "${ambienteFromDbRoom.potenciaTotalIlum}",
                "${ambienteFromDbRoom.amperagemCircuitoIlum}",
                "${ambienteFromDbRoom.quantTomada}",
                "${ambienteFromDbRoom.potenciaTotalTomada}",
                "${ambienteFromDbRoom.amperagemTomada}",
                "${ambienteFromDbRoom.btuPorM2}",
                "${ambienteFromDbRoom.quantPessoasAmbiente}",
                "${ambienteFromDbRoom.quantEletrodomestico}",
                "${ambienteFromDbRoom.btuAdicionalPorPessoa}",
                "${ambienteFromDbRoom.btuAdicionalPorEletronico}",
                "${ambienteFromDbRoom.btuAdicionalInsidenciaRaioSolar}",
                "${ambienteFromDbRoom.btusTotal}",
                "${ambienteFromDbRoom.IDRS}",
                "${ambienteFromDbRoom.potenciaEletriaAc}",
                "${ambienteFromDbRoom.amperagemCircuitoAc}",
            )
            var listItem: List<String> = listOf()
            listItem = listOf(
                "ID",
                "Ambiente:",
                "Nome do Ambiente:",
                "Largura:",
                "Comp:",
                "Tensão:",
                "Area:",
                "LumensAmbiente:",
                "LumensLampada:",
                "Lumens Total:",
                "Potencia da Lampada:",
                "Total de Lampada:",
                "Potencia Total:",
                "(A)Lampada:",
                "Quant Tomada:",
                "Potencia Tomada:",
                "Corrente Tomada:",
                "btusM²:",
                "QuantPessoa no Ambiente:",
                "QuantEletronico no Ambiente:",
                "btusAdicionalPessoa:",
                "btusAdicionaleletronico:",
                "btusAdicionalIndSolar:",
                "btusTotal:",
                "IDRS:",
                "(W)Arcond:",
                "(A)Arcond:"
            )

            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                colors = CardColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Gray,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Black
                ),
                border = BorderStroke(width = 1.dp, color = Color.LightGray)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                    , verticalArrangement = Arrangement.Top
                    , horizontalAlignment = Alignment.Start
                ) {
                    if(uiState.showAlertDialog){
                        AlertDialogConf(
                            onDismissRequest = {
                                FancyToast.makeText(context,
                                    "cancelado",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
                            },
                            onConfirmation = {
                                scope.launch {
                                    viewmodel.deletarAmbiente(ambienteFromDbRoom)
                                    FancyToast
                                        .makeText(
                                            context,
                                            ambienteFromDbRoom.nomeAmbiente,
                                            FancyToast.LENGTH_SHORT,
                                            FancyToast.SUCCESS,
                                            false
                                        )
                                        .show()
                                }

                                FancyToast.makeText(
                                    context, "Deletado", FancyToast.LENGTH_SHORT,
                                    FancyToast.SUCCESS, true
                                ).show()
                                stateHolder.setShowAlertDialog(value = false)
                            },
                            dialogTitle = "Eletric House",
                            dialogText = "Tem certeza que desejar deletar esse aquivo ",
                            icon = Icons.Default.Notifications
                        )
                    }

                    IconButton(
                        modifier = Modifier.size(20.dp),
                        onClick = {
                            stateHolder.setShowAlertDialog(value = true)
                        }

                    ) {
                        Row(
                            modifier = Modifier.padding(start = 5.dp, bottom = 5.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Deletar", tint = Color.Red)
                            Text(text = "Deletar Ambiente",
                                color = Color.Gray,
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

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
                                text = ambienteListado[index],
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }
                }
            }
        }
    }
}


