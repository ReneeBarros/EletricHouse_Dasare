package com.example.nativeeletrichouse

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.nativeeletrichouse.data.db.entity.AmbienteEntity
import com.example.nativeeletrichouse.data.reponse.ResponseCaculateAmbiente
import com.example.nativeeletrichouse.dto.DtoResponseEletricHouse
import com.example.nativeeletrichouse.main.core.navigation.HomeGraph
import com.example.nativeeletrichouse.presation.getData.DataFromUiScreen
import com.example.nativeeletrichouse.presation.mainui.MainScreen
import com.example.nativeeletrichouse.presation.showresult.ShowResultScreen
import com.example.nativeeletrichouse.ui.theme.NativeEletrichouseTheme
import com.example.nativeeletrichouse.viewmodel.ViewModelAmbiente
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            NativeEletrichouseTheme(
                theme = "",
                content = {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                    ) {
                        val navController = rememberNavController()
                        val viewModelAmbiente = koinViewModel<ViewModelAmbiente>()

                        NavHost(
                            navController = navController,
                            startDestination = HomeGraph.Home
                        ){
                            composable<HomeGraph.Home> {
                                MainScreen().MainScreenCore(
                                navController = navController,
                                    viewModelAmbiente = viewModelAmbiente
                                )
                            }


                            composable<HomeGraph.dataUi> { bringData ->
                                val bring: HomeGraph.dataUi = bringData.toRoute()
                                DataFromUiScreen().Data(
                                    iluminacao = bring.ilum!!,
                                    tomada = bring.tom!!,
                                    arCondicionado = bring.ac!!,
                                    home = bring.home!!,
                                     ambiente =  bring.ambiente!!,
                                    nomeAmbiente = bring.nomeAmbiente!!,
                                    navController = navController,

                                )
                            }
                            composable<HomeGraph.ShowResultData> {
                                    bringData ->
                                val bring: HomeGraph.ShowResultData = bringData.toRoute()
                               ShowResultScreen().ShowResultApp(
                                    DtoResponseEletricHouse.EletricHouseComplete(
                                        id = bring.id,
                                        ambiente = bring.ambiente,
                                        largura = bring.largura,
                                        comprimento = bring.comprimento,
                                        tensao = bring.tensao,
                                        area = bring.area,
                                        lumensAmbiente = bring.lumensAmbiente,
                                        lumensLuminaria = bring.lumensLuminaria,
                                        lumensTotal = bring.lumensTotal,
                                        potenciaLuminaria = bring.potenciaLuminaria,
                                        totalLuminaria = bring.totalLuminaria,
                                        potenciaTotalLampadas = bring.potenciaTotal,
                                        amperagemIluminacao = bring.amperagemIluminacao,
                                        quantToamda = bring.quantToamda,
                                        potenciaTotalTomada = bring.potenciaTotalTomada,
                                        amperagemTomada = bring.amperagemTomada,
                                        quantPessoasAmbiente = bring.quantPessoasAmbiente,
                                        quantEletrodomestico = bring.quantEletrodomestico,
                                        btuAdicionalPorPessoa = bring.btuAdicionalPorPessoa,
                                        btuAdicionalPorEletronico = bring.btuAdicionalPorEletronico,
                                        btuAdicionalInsidenciaRaioSolar = bring.btuAdicionalInsidenciaRaioSolar,
                                        btuPorM2 = bring.btuPorM2,
                                        btusTotal = bring.btusTotal,
                                        potenciaEletria = bring.potenciaEletria,
                                        IDRS = bring.IDRS,
                                        amperagemCircuitoAc = bring.amperagemCircuitoAc
                                    ),
                                   calcular = bring.calcular
                               )
                            }

                            composable(
                                route = "ResultadoApi/{fromDb}/{Ambiente}",
                                arguments = listOf(
                                    navArgument(name = "Ambiente"){
                                        type = NavType.StringType
                                        defaultValue=""
                                    },
                                    navArgument(name = "fromDb"){
                                        type = NavType.BoolType
                                        defaultValue=false
                                    }
                                )
                            ) {
                                val jsonData = it.arguments?.getString("Ambiente")?:"No Data"
                                val fromDb = it.arguments?.getBoolean("fromDb")?:false

                                if(!fromDb){
                                    val listAmbienteFromDB = Json.decodeFromString<List<AmbienteEntity>>(jsonData)
                                    ShowResultScreen().ApresentationResultApi(
                                        ambienteFromDbRoom = listAmbienteFromDB,
                                        ambienteCalculado = listOf(),
                                        fromBd = fromDb,
                                        navController = navController
                                    )
                                }else{
                                    val listAmbiente = Json.decodeFromString<List<ResponseCaculateAmbiente>>(jsonData)
                                    ShowResultScreen().ApresentationResultApi(
                                        ambienteFromDbRoom = listOf(),
                                        ambienteCalculado = listAmbiente,
                                        fromBd = fromDb,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}

