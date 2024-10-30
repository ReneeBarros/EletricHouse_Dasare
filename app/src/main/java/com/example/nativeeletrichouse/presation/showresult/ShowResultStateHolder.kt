package com.example.nativeeletrichouse.presation.showresult

import com.example.nativeeletrichouse.presation.home.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShowResultStateHolder {

    private val _uistate = MutableStateFlow(ShowResultUistate())
    val uiState = _uistate.asStateFlow()

    fun setShowAlertDialog(value: Boolean){
        _uistate.update { current ->
            current.copy(
                showAlertDialog = value
            )
        }
    }
}