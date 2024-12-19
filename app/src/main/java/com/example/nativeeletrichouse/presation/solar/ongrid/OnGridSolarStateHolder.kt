package com.example.nativeeletrichouse.presation.solar.ongrid

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnGridSolarStateHolder {

    private val _uistate = MutableStateFlow(OnGridSolarUiState())
    val uiState = _uistate.asStateFlow()
}
