package com.example.nativeeletrichouse.presation.splash


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

class SplashScreen  {

    private val stateHolder = SplashStateHolder()

    @Composable
     fun Content() {
        val uiState by stateHolder.uiState.collectAsState()
    }

}