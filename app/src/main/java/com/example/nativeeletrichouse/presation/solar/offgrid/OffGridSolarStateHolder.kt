package com.example.nativeeletrichouse.presation.solar.offgrid

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OffGridSolarStateHolder {

    private val _uistate = MutableStateFlow(OffGridSolarUiState())
    val uiState = _uistate.asStateFlow()


    fun setCity(value:String){
        _uistate.update{ current->
            current.copy(
                city = value
            )
        }
    }

    fun setEquipment(value:String){
        _uistate.update{ current->
            current.copy(
                equipment = value
            )
        }
    }

    fun setQuantityEquipment(value:String){
        _uistate.update{ current->
            current.copy(
                quantityEquipment = value
            )
        }
    }

    fun setEquipmentPower(value:String ){
        _uistate.update{ current->
            current.copy(
                equipmentPower = value
            )
        }
    }

    fun setHoursDailyUse(value:String){
        _uistate.update{ current->
            current.copy(
                hoursDailyUse = value
            )
        }
    }

    fun setLoad(value:Double){
        _uistate.update{ current->
            current.copy(
                load = value
            )
        }
    }

    fun setConsumptionEquipmentPerDay(value:Double){
        _uistate.update{ current->
            current.copy(
                consumptionEquipmentPerDay = value
            )
        }
    }

    fun setTotalLoad(value:Double){
        _uistate.update{ current->
            current.copy(
                totalLoad = value
            )
        }
    }

    fun setTotalConsumptionPower(value:Double){
        _uistate.update{ current->
            current.copy(
                totalConsumptionPowerPerDay = value
            )
        }
    }

    fun setPotenciaMaiximaInversor(value:Double){
        _uistate.update{ current->
            current.copy(
                potenciaMaiximaInversor = value
            )
        }
    }

    fun setEficienciaInversor(value:Double){
        _uistate.update{ current->
            current.copy(
                eficienciaInversor = value
            )
        }
    }

    fun setEnergiaCorrigidaPerDay(value:Double){
        _uistate.update{ current->
            current.copy(
                energiaCorrigidaPerDay = value
            )
        }
    }

    fun setRendimentoGlobal(value:Double){
        _uistate.update{ current->
            current.copy(
                rendimentoGlobal = value
            )
        }
    }

    fun setEnergyGeralRequired(value:Double){
        _uistate.update{ current->
            current.copy(
                energyGeralRequired= value
            )
        }
    }

    fun setPotenciaMinimaInversor(value:Double){
        _uistate.update{ current->
            current.copy(
                potenciaMinimaInversor = value
            )
        }
    }

    fun setShowAlertDialog(value:Boolean){
        _uistate.update{ current->
            current.copy(
                showAlertDialog = value
            )
        }
    }

    fun setAutonomyDay(value:String){
        _uistate.update{ current->
            current.copy(
                autonomyDay = value
            )
        }
    }

    fun setVoltagyBattery(value:String){
        _uistate.update{ current->
            current.copy(
                voltagyBattery = value
            )
        }
    }

    fun setVoltagyBatteryBank(value:String){
        _uistate.update{ current->
            current.copy(
                voltagyBatteryBank = value
            )
        }
    }

    fun setDischargeDepthOfBatteryBank(value:String){
        _uistate.update{ current->
            current.copy(
                dischargeDepthOfBatteryBank = value
            )
        }
    }

    fun setMetodoInstalacao(value:String){
        _uistate.update{ current->
            current.copy(
                metodoInstalacao = value
            )
        }
    }

    fun setTemperaturaAmbiente(value:String){
        _uistate.update{ current->
            current.copy(
                temperaturaAmbiente = value
            )
        }
    }

    fun setPowerOfPainelModule(value:String){
        _uistate.update{ current->
            current.copy(
                PowerOfPainelModule = value
            )
        }
    }

    fun setCapacidadeBateriaAH(value:String){
        _uistate.update{ current->
            current.copy(
                capacityBateryAH = value
            )
        }
    }
}
