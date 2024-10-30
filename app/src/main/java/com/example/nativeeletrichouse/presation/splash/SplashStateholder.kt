package com.example.nativeeletrichouse.presation.splash


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SplashStateHolder {

    private val _uistate = MutableStateFlow(SplashUiState())
    val uiState = _uistate.asStateFlow()

}