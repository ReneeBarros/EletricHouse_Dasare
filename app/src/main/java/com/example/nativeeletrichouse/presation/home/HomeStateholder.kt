package com.example.nativeeletrichouse.presation.home


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeStateHolder {

    private val _uistate = MutableStateFlow(HomeUiState())
    val uiState = _uistate.asStateFlow()

    fun setIluminacao(value:Boolean){
        _uistate.update { currentState ->
            currentState.copy(
                iluminacao = !value
            )

        }
    }
    fun setTomada(value:Boolean){
        _uistate.update { currentState ->
            currentState.copy(
                tomada = !value
            )

        }
    }
    fun setArCondicionado(value:Boolean){
        _uistate.update { currentState ->
            currentState.copy(
                arCondicionado = !value
            )

        }
    }
    fun setCalculo(value:Boolean){
        _uistate.update { currentState ->
            currentState.copy(
                calculo = !value
            )

        }
    }

    fun setAmbiente(value:String){
        _uistate.update { currentState ->
            currentState.copy(
                ambiente = value
            )

        }
    }

    fun setNomeObra(value:String){
        _uistate.update { currentState ->
            currentState.copy(
                nomeObra = value
            )

        }
    }

}