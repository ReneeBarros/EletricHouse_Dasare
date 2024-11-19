package com.example.nativeeletrichouse.presation.tomada

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalcularTomadaStateHolder {

    private val _uistate = MutableStateFlow(CalcularTomadaUiState())
    val uiState = _uistate.asStateFlow()

    fun setRoomType(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
                roomType = value
            )
        }
    }

    fun setRoomName(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
                roomName = value
            )
        }
    }

    fun setWidth(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
                width = value
            )
        }
    }
    fun setLength(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
                length = value
            )
        }
    }

    fun setVoltage(value: Int){
        _uistate.update { currentStat ->
            currentStat.copy(
                voltage = value
            )
        }
    }
}
