package com.example.nativeeletrichouse.presation.getData


data class DataFromUiUiState(
    val title: String = "DataFromUi",
    val largura:String = "",
    val comprimento:String = "",
    val area:String = "",
    val tensao:String = "",
    val tensao220:Boolean = false,
    val tensao110:Boolean = false,
    var isSelected: Boolean = false,
    val potenciaLamp:Double = 0.0,
    val quantPessoaAmbient:String = "",
    val quantEletrodomestico:String ="",
    val insidenciaSolar:Boolean = false,
    val insertAmbiente:String ="",
    val insertNameAmb:String =""
)