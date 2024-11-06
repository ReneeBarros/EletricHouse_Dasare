package com.example.nativeeletrichouse.presation.calcularcabo

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalcularCaboStateHolder {

    private val _uistate = MutableStateFlow(CalcularCaboUiState())
    val uiState = _uistate.asStateFlow()

    fun setTensao(value: Int){
        _uistate.update { currentState->
            currentState.copy(
                tensao = value
            )
        }
    }
    fun setPotencia(value: String){
        _uistate.update { currentState->
            currentState.copy(
                pontecia = value
            )
        }
    }
    fun setFatoPotencia(value: Double){
        _uistate.update { currentState->
            currentState.copy(
                fatoPotencia = value
            )
        }
    }

    fun setCorrente(value: String){
        _uistate.update { currentState->
            currentState.copy(
                corrente = value
            )
        }
    }

    fun setModeloInstCabo(value: String){
        _uistate.update { currentState->
            currentState.copy(
                modeloInstalacaoCabos = value
            )
        }
    }

    fun setCondutoresCarregado(value: String){
        _uistate.update { currentState->
            currentState.copy(
                condutoresCarregado = value
            )
        }
    }

    fun setQuantCircuito(value: String){
        _uistate.update { currentState->
            currentState.copy(
                quantDeCircuito = value
            )
        }
    }
}
