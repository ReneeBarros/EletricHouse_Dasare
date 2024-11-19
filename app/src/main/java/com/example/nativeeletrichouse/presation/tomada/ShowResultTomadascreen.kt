package com.example.nativeeletrichouse.presation.tomada

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.dto.DtoResponseEletricHouse
import com.example.nativeeletrichouse.presation.components.widget.TopBar

@Composable
fun ShowResultTomadaScreen(
    navController: NavController,
    toResultScreen: DtoResponseEletricHouse.DtoTomada

) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Resultado",
                fontSize = 16.sp,
                modifier = Modifier,
                navController = navController
            )
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {

            val listToShowResult = listOf(
                toResultScreen.ambiente,
                toResultScreen.nomeAmbiente,
                "${toResultScreen.largura}",
                "${toResultScreen.comprimento}",
                "${toResultScreen.tensao}",
                "${toResultScreen.perimetro}",
                "${toResultScreen.quantToamda}",
                "${toResultScreen.potenciaTotalTomada}",
                "${toResultScreen.amperagemTomada}",
                "${toResultScreen.caboToTomada}",

            )
            val listItem = listOf(

                "Ambiente:",
                "Nome Ambiente:",
                "Largura:",
                "Comp:",
                "Tensão:",
                "Perimetro:",
                "Quant Tomada:",
                "(W)Tomada:",
                "(A)Tomada:",
                "CAbo Eletrico p/ Tomada:"
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
                            text =  listToShowResult[index],
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            }
        }
    }
}