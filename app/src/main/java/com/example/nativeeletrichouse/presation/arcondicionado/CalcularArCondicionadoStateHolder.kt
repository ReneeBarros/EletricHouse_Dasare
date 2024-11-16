package com.example.nativeeletrichouse.presation.arcondicionado

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalcularArCondicionadoStateHolder {

    private val _uistate = MutableStateFlow(CalcularArCondicionadoUiState())
    val uiState = _uistate.asStateFlow()


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
    fun setArea(value: Double){
        _uistate.update { currentStat ->
            currentStat.copy(
                area = value
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

    fun setNumberOfPeopleInRoom(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
                numberOfPeopleInRoom = value
            )
        }
    }
    fun setNumberOfAppliances(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
                numberOfAppliances = value
            )
        }
    }
    fun setSolarIncidence(value:Boolean){
        _uistate.update { currentStat ->
            currentStat.copy(
                solarIncidence = !value
            )
        }
    }

    fun setRoomType (value:String){
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

}
