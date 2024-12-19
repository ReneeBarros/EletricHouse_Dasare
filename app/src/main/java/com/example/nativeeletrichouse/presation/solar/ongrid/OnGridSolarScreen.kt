package com.example.nativeeletrichouse.presation.solar.ongrid

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

class OnGridSolarScreen {

    private val stateHolder = OnGridSolarStateHolder()

    @Composable
    fun OnGridSolarUiScreen() {
        val uiState by stateHolder.uiState.collectAsState()

    }
}