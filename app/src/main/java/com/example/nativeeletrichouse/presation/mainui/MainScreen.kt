package com.example.nativeeletrichouse.presation.mainui


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.data.db.entity.AmbienteEntity
import com.example.nativeeletrichouse.data.reponse.ResponseCaculateAmbiente
import com.example.nativeeletrichouse.main.core.navigation.HomeGraph
import com.example.nativeeletrichouse.presation.components.CardItem
import com.example.nativeeletrichouse.presation.home.HomeStateHolder
import com.example.nativeeletrichouse.viewmodel.ViewModelAmbiente
import kotlinx.serialization.json.Json


class MainScreen {

    private val stateHolder = MainStateHolder()
    private val stateHolderHome=HomeStateHolder()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType", "SuspiciousIndentation")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
     fun MainScreenCore(
         navController: NavController,
         viewModelAmbiente: ViewModelAmbiente
     ){
            val context = LocalContext.current
            val uiState by stateHolder.uiState.collectAsState()
            val uiStateHome by stateHolderHome.uiState.collectAsState()
            val listaAmbienteFromDb by viewModelAmbiente.ambientesFromDb.collectAsState()
            val scope = rememberCoroutineScope()

        Box(
               Modifier.fillMaxSize(),

           ) {
               Scaffold(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(color = Color.White)
                       .padding(0.dp),
                   topBar = {
                       TopAppBar(
                           title = {
                               Column(

                                   verticalArrangement = Arrangement.Center,
                                   horizontalAlignment = Alignment.Start
                               ) {
                                   Text(text = "Welcome", fontSize = 16.sp,
                                       color = MaterialTheme.colorScheme.onBackground
                                   )

                                   Text(text = stringResource(id = R.string.Name_app),
                                       color = MaterialTheme.colorScheme.onBackground
                                   )

                               }
                           },
                           navigationIcon = {
                               IconButton(onClick = {
                               }) {
                                   Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "${(R.string.Return_Icon)}" )
                               }

                           },
                           colors = TopAppBarDefaults.topAppBarColors(
                               titleContentColor = Color.Black,
                               containerColor = Color.Transparent,
                               actionIconContentColor = MaterialTheme.colorScheme.onBackground,
                               navigationIconContentColor = Color.Gray
                           ),
                           actions = {
                               IconButton(onClick = { /*TODO*/ }) {
                                   Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")
                               }

                           }
                       )
                   },
                   bottomBar = {
                       BottomAppBar(
                           containerColor = Color.Transparent,
                           contentColor = Color.Black,

                           actions ={
                               Row(
                                   modifier = Modifier.fillMaxWidth(),
                                   verticalAlignment = Alignment.CenterVertically,
                                   horizontalArrangement = Arrangement.SpaceAround
                               ) {

                                   NavigationBarItem(
                                       selected = true,
                                       onClick = {},
                                       icon = {
                                           Icon(modifier = Modifier.size(30.dp),
                                               painter = painterResource(id = R.drawable.home),
                                               contentDescription = "Home")
                                       },
                                       label = {
                                           Text(text = "Home")
                                       },
                                       colors = NavigationBarItemColors(
                                           selectedIconColor = Color.Black,
                                           unselectedIconColor = Color.DarkGray,
                                           unselectedTextColor = Color.Black,
                                           selectedTextColor = Color.DarkGray,
                                           disabledTextColor = Color.LightGray,
                                           disabledIconColor = Color.LightGray,
                                           selectedIndicatorColor = Color.LightGray
                                       )
                                   )
                                   // botao Banco de Dados
                                   NavigationBarItem(
                                       selected = false,
                                       onClick = {

                                           val listAmbienteCalculadoFromDB = mutableListOf<String>()
                                           val json = Json{prettyPrint = true}
                                           //val responseFromDb = ambienteEntitytoResponseCaculateAmbiente(listaAmbienteFromDb)
                                            listaAmbienteFromDb.forEach{
                                               listAmbienteCalculadoFromDB.add(json.encodeToString(
                                                   AmbienteEntity.serializer(),it)
                                               )
                                           }
                                           navController.navigate(
                                               route = "ResultadoApi/${false}/${listAmbienteCalculadoFromDB}"
                                           )
                                       },
                                       icon = {
                                           Icon(modifier = Modifier.size(30.dp),
                                               painter = painterResource(id = R.drawable.arquivodebancodedados),
                                               contentDescription = "Banco de dados")
                                       },
                                       label = {
                                           Text(text = "Salvos")
                                       }
                                   )
                                   FloatingActionButton(
                                       onClick = {
                                            navController.navigate(HomeGraph.dataUi(
                                                ilum = uiStateHome.iluminacao,
                                                tom = uiStateHome.tomada,
                                                ac = uiStateHome.arCondicionado,
                                                home = false,
                                            ))
                                       },
                                       modifier = Modifier.size(60.dp,60.dp)
                                   ) {
                                       Column(
                                           verticalArrangement = Arrangement.Center,
                                           horizontalAlignment = Alignment.CenterHorizontally
                                       ) {

                                           Icon(painter = painterResource(id = R.drawable.calculadora), contentDescription ="")
                                           Text(text = "Conversôes")
                                       }

                                   }

                                   NavigationBarItem(
                                       selected = false,
                                       onClick = {},
                                       icon = {
                                           Icon(modifier = Modifier.size(30.dp),
                                               painter = painterResource(id = R.drawable.definicoes),
                                               contentDescription = "Definicoes")
                                       },
                                       label = {
                                           Text(text = "Config")
                                       }
                                   )
                                   NavigationBarItem(
                                       selected = false,
                                       onClick = {
         /*                                  Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
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
                                               ).show()*/
                                       },
                                       icon = {
                                           Icon(modifier = Modifier.size(30.dp),
                                               painter = painterResource(id = R.drawable.orcamento),
                                               contentDescription = "Orcamento")
                                       },
                                       label = {
                                           Text(text = "Orcamento")
                                       }
                                   )
                               }
                           }
                       )
                   }
               ) {
                   Column(modifier = Modifier
                       .fillMaxSize()
                       .padding(it)
                       .verticalScroll(rememberScrollState()),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {

                       Column(
                           modifier = Modifier
                               .fillMaxSize()
                               .padding(2.dp),
                           verticalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterVertically),
                           horizontalAlignment = Alignment.CenterHorizontally
                       ) {

                           Column(
                               modifier = Modifier
                                   .fillMaxSize()
                                   .padding(2.dp),
                               verticalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterVertically),
                               horizontalAlignment = Alignment.CenterHorizontally
                           ) {

                               Row(
                                   modifier = Modifier
                                       .fillMaxWidth()
                                       .padding(start = 2.dp),
                                   verticalAlignment = Alignment.CenterVertically,
                                   horizontalArrangement = Arrangement.Absolute.spacedBy(10.dp)
                               ) {

                                   CardItem(img = R.drawable.luminaria, description ="lampada",
                                       {
                                           stateHolderHome.setIluminacao(uiStateHome.iluminacao)
                                           navController.navigate(HomeGraph.dataUi(
                                               ilum = true,
                                               tom = uiStateHome.tomada,
                                               ac = uiStateHome.arCondicionado,
                                               home = false,
                                               ambiente = uiStateHome.ambiente,
                                               nomeAmbiente = uiStateHome.nomeObra
                                           )
                                           )

                                       },
                                       uiStateHome.iluminacao, title = "Calcular Iluminação"
                                   )

                                   CardItem(img = R.drawable.tomada, description ="Tomada",
                                       {
                                           stateHolderHome.setTomada(uiStateHome.tomada)
                                           navController.navigate(HomeGraph.dataUi(
                                               ilum = false,
                                               tom = true,
                                               ac = false,
                                               home = false,
                                               ambiente = uiStateHome.ambiente,
                                               nomeAmbiente = uiStateHome.nomeObra,
                                               calcular = "tomada"
                                           ))
                                       },
                                       uiStateHome.tomada,
                                       title = "Calcular Tomada"
                                   )
                               }
                               Row(
                                   modifier = Modifier
                                       .fillMaxWidth()
                                       .padding(start = 2.dp),
                                   verticalAlignment = Alignment.CenterVertically,
                                   horizontalArrangement = Arrangement.Absolute.spacedBy(10.dp)
                               ) {
                                   CardItem(
                                       img = R.drawable.arcond,
                                       description ="arCondicionado",
                                       {
                                           stateHolderHome.setArCondicionado(uiStateHome.arCondicionado)
                                           navController.navigate(HomeGraph.dataUi(
                                               ilum = false,
                                               tom = false,
                                               ac = true,
                                               home = false,
                                               ambiente = uiStateHome.ambiente,
                                               nomeAmbiente = uiStateHome.nomeObra
                                           )
                                           )
                                       },
                                       uiStateHome.arCondicionado,
                                       title = "Calcular Ar-condic"
                                   )

                                   CardItem(
                                       img = R.drawable.cabeletrico,
                                       description ="Cabo Eletrico",
                                       {
                                           stateHolderHome.setCalculo(uiStateHome.calculo)
                                       },
                                       uiStateHome.calculo,
                                       title = "Cabo Eletrico"
                                   )
                               }

                               Row(
                                   modifier = Modifier
                                       .fillMaxWidth()
                                       .padding(start = 2.dp),
                                   verticalAlignment = Alignment.CenterVertically,
                                   horizontalArrangement = Arrangement.Absolute.spacedBy(10.dp)
                               ) {
                                   CardItem(img = R.drawable.casa, description ="Casa",
                                       {
                                           navController.navigate(HomeGraph.dataUi(
                                               ilum = false,
                                               tom = false,
                                               ac = false,
                                               home = true
                                           ))
                                       },
                                       uiStateHome.arCondicionado,
                                       title = "Calcular Ambiente"
                                   )

                                   CardItem(img = R.drawable.placasolar, description ="placa solar",
                                       {
                                           navController.navigate(
                                               HomeGraph.dataUi(
                                               ilum = false,
                                               tom = false,
                                               ac = false,
                                               home = true
                                           ))
                                       },
                                       uiStateHome.arCondicionado,
                                       title = "Calcular placa Solar"
                                   )

                               }
                           }
                       }
                   }
               }
           }
        }
    }


fun ambienteEntitytoResponseCaculateAmbiente(listAmbienteEntity: List<AmbienteEntity>): List<ResponseCaculateAmbiente>{
    val listAmbiente: MutableList<ResponseCaculateAmbiente> = mutableListOf()

    listAmbienteEntity.forEach {
        ambienteEntity ->
       listAmbiente.add(ambEntityToResponseAmbiente(ambienteEntity))
    }
    return listAmbiente
}
fun ambEntityToResponseAmbiente(ambienteEntity: AmbienteEntity): ResponseCaculateAmbiente{

    return   ResponseCaculateAmbiente(
        ambiente = ambienteEntity.ambiente,
        nomeAmbiente = ambienteEntity.nomeAmbiente,
        largura = ambienteEntity.largura,
        comprimento = ambienteEntity.comprimento,
        tensao = ambienteEntity.tensao,
        area = ambienteEntity.area,
        lumensAmbiente = ambienteEntity.lumensAmbiente,
        lumensLuminaria = ambienteEntity.lumensLuminaria,
        lumensTotal = ambienteEntity.lumensTotal,
        totalLuminaria = ambienteEntity.totalLuminaria,
        potenciaLuminaria = ambienteEntity.potenciaLuminaria,
        potenciaTotalIlum = ambienteEntity.potenciaTotalIlum,
        amperagemCircuitoIlum = ambienteEntity.amperagemCircuitoIlum,
        quantTomada = ambienteEntity.quantTomada,
        potenciaTotalTomada = ambienteEntity.potenciaTotalTomada,
        amperagemTomada = ambienteEntity.amperagemTomada,
        quantPessoasAmbiente = ambienteEntity.quantPessoasAmbiente,
        quantEletrodomestico = ambienteEntity.quantEletrodomestico,
        btuPorM2 = ambienteEntity.btuPorM2,
        btuAdicionalPorPessoa = ambienteEntity.btuAdicionalPorPessoa,
        btuAdicionalPorEletronico = ambienteEntity.btuAdicionalPorEletronico,
        btuAdicionalInsidenciaRaioSolar = ambienteEntity.btuAdicionalInsidenciaRaioSolar,
        btusTotal = ambienteEntity.btusTotal,
        IDRS = ambienteEntity.IDRS,
        potenciaEletriaAc = ambienteEntity.potenciaEletriaAc,
        amperagemCircuitoAc = ambienteEntity.amperagemCircuitoAc
    )


}
