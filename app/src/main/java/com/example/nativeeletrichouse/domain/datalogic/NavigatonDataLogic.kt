package com.example.nativeeletrichouse.domain.datalogic

import androidx.navigation.NavController

class NavigatonDataLogic {

    fun navigationDataLogic(
        navController: NavController,
        listDataLogic: MutableList<String>

    ){
        navController.navigate(
            route = "ResultadoApi/${true}/${listDataLogic}"
        )
    }

}