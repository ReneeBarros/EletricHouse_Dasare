package com.example.nativeeletrichouse.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nativeeletrichouse.api.api_eletri_house.ApiEletricHouse
import com.example.nativeeletrichouse.data.db.entity.AmbienteEntity
import com.example.nativeeletrichouse.data.reponse.ResponseCaculateAmbiente
import com.example.nativeeletrichouse.maper.MapperResponseApiToResponseUi
import com.example.nativeeletrichouse.repository.AmbienteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewModelAmbiente(
    val repository: AmbienteRepository
): ViewModel() {

    fun addAmbiente(ambiente: List<MapperResponseApiToResponseUi>){
       viewModelScope.launch {
           ambiente.forEach {
               repository.saveAmbeinte(ambiente = it)
           }
       }

    }

   /*val ambientesFromDb = repository.ambientes*/
   val ambientesFromDb: StateFlow<List<AmbienteEntity>> = repository.ambientes
       .stateIn(
           scope = viewModelScope,
           started = SharingStarted.WhileSubscribed(5000),
           initialValue = emptyList()
       )

  suspend fun deletarAmbiente(ambiente: AmbienteEntity){
      repository.deletarAmbiente(ambiente = ambiente)
      Log.d("ViewModelAmbiente", "Ambiente deletado: ${ambiente.nomeAmbiente}")
  }
}