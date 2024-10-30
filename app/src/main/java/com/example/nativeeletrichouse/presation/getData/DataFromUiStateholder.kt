package com.example.nativeeletrichouse.presation.getData


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DataFromUiStateHolder {

    private val _uistate = MutableStateFlow(DataFromUiUiState())
    val uiState = _uistate.asStateFlow()

    fun setLargura(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
                largura = value
            )
        }
    }
    fun setComprimento(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
                comprimento = value
            )
        }
    }
    fun setArea(value: String){
        _uistate.update { currentStat ->
            currentStat.copy(
                area = value
            )
        }
    }
    fun setTensao(value: String){
        _uistate.update { currentStat ->
            currentStat.copy(
                tensao = value
            )
        }
    }
    fun setpotenciaLamp(value:Double) {
        _uistate.update { currentStat ->
            currentStat.copy(
                potenciaLamp = value
            )
        }
    }
    fun setquantPessoaAmbient(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
                quantPessoaAmbient = value
            )
        }
    }
    fun setQuantEletrodomestico(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
                quantEletrodomestico = value
            )
        }
    }
    fun setInsidenciaSolar(value:Boolean){
        _uistate.update { currentStat ->
            currentStat.copy(
               insidenciaSolar = !value
            )
        }
    }
    fun setTensao220(value:Boolean){
        _uistate.update { currentStat ->
            currentStat.copy(
               tensao220 = value
            )
        }
    }
    fun setTensao110(value:Boolean){
        _uistate.update { currentStat ->
            currentStat.copy(
               tensao110 = value
            )
        }
    }
    fun setInsertambiente(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
               insertAmbiente = value
            )
        }
    }
    fun setInsertNameAmb(value:String){
        _uistate.update { currentStat ->
            currentStat.copy(
               insertNameAmb = value
            )
        }
    }

    fun seIsSelected(value:Boolean){
        _uistate.update { currentStat ->
            currentStat.copy(
                isSelected = !value
            )
        }
    }

}