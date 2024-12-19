package com.example.nativeeletrichouse.presation.solar.offgrid


data class OffGridSolarUiState(
    val title: String = "OffGridSolar",

    // Informações que precisa ser fornecidas pelo usuario----------------------------*

    val city:String="",
    val equipment:String="", // equipamento (nome do equipamento)
    val quantityEquipment:String = "", // quantiadade de equipamento
    val equipmentPower:String = "", // potencia do equipamento
    val hoursDailyUse:String = "", //horas de uso do equipamento no dia
    //-------------------------------------------------------------------------------------------

    //Variaveis que precisa ser calculada pelo sistema-------------------------------------------*
    val load:Double=0.0, // load will be the result of the quantityEquipment * equipmentPower
    val consumptionEquipmentPerDay:Double=0.0,  // consumo da somatoria das cargas de um equipamento por dia
    val totalLoad:Double=0.0, // (carga total) é somatoria de todas as cargas
    val totalConsumptionPowerPerDay:Double=0.0, // consumo total de potencia horas dias W/dia

    val potenciaMinimaInversor: Double=0.0,  // PotenciaMinimaInversor = 1,43 * totalLoad
    val potenciaMaiximaInversor:Double=0.0,  // potenciaMaiximaInversor = 2 * totalLoad

    val eficienciaInversor:Double = 0.90,
    val energiaCorrigidaPerDay:Double=0.0, // energiaCorrigidaPerDay = totalConsumptionPowerPerDay / eficienciaInversor
    val rendimentoGlobal:Double=0.89,
    //---------------------------------****************----------------------------------------------------------
    //Considerando-se o rendimento global, calcula-se a energia real diária requerida:

    val energyGeralRequired:Double=0.0, // energyGeralRequired = energiaCorrigidaPerDay / rendimentoGlobal
            // lembrar de colocar uma lista de rendimentos globais para que seja escolhido pelo usuario.
    val showAlertDialog:Boolean = false,
    val indexToremove: Int = -0,
// informaçõe spara dimencionar banco de bateria para o sistema
    val autonomyDay: String = "",
    val voltagyBattery: String = "",
    val voltagyBatteryBank: String = "",
    val dischargeDepthOfBatteryBank: String = "",
    val capacityBateryAH: String = "",


    val metodoInstalacao: String = "",
    val temperaturaAmbiente: String = "",
    val PowerOfPainelModule: String = "330",

    )