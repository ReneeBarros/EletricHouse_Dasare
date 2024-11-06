package com.example.nativeeletrichouse.presation.solar

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SystemSolarStateHolder {

    private val _uistate = MutableStateFlow(SystemSolarUiState())
    val uiState = _uistate.asStateFlow()
}
