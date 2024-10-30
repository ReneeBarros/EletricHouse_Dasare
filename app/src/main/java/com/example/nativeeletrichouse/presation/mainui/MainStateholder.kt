package com.example.nativeeletrichouse.presation.mainui


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainStateHolder {

    private val _uistate = MutableStateFlow(MainUiState())
    val uiState = _uistate.asStateFlow()

    fun setTest(value:Boolean){
        _uistate.update { current ->
            current.copy(
                test = !value
            )
        }
    }

}