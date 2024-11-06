package com.example.nativeeletrichouse.presation.solar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.main.core.navigation.HomeGraph
import com.example.nativeeletrichouse.presation.components.CardItem

class SystemSolarScreen {

    private val stateHolder = SystemSolarStateHolder()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SystemSolarUiScreen(
        navController: NavController
    ) {
        val uiState by stateHolder.uiState.collectAsState()


        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text="SOLAR") })
            },
            snackbarHost = {},
            bottomBar = {
                BottomAppBar(actions = {
                    NavigationBar {

                    }
                })

            },

        ) {paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues).fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    CardItem(
                        img = R.drawable.solar_off_greed, description = "placa solar",
                        {
                            navController.navigate(
                                HomeGraph.dataUi(
                                    ilum = false,
                                    tom = false,
                                    ac = false,
                                    home = true
                                )
                            )
                        },
                        false,
                        title = "Off Creed",
                        modifier = Modifier.height(160.dp),
                        textCentral = true,
                        textCentralConteudo = "Off Grid"
                    )

                    CardItem(
                        img = R.drawable.sistema_on_grid, description = "placa solar",
                        {
                            navController.navigate(
                                HomeGraph.dataUi(
                                    ilum = false,
                                    tom = false,
                                    ac = false,
                                    home = true
                                )
                            )
                        },
                        false,
                        title = "Off Creed",
                        modifier = Modifier.height(160.dp),
                        textCentral = true,
                        textCentralConteudo = "On Grid"
                    )
                }


            }
        }
    }
}