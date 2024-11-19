package com.example.nativeeletrichouse.main.core.navigation.navigation_tomada

import androidx.navigation.NavController
import com.example.nativeeletrichouse.dto.DtoResponseEletricHouse

class NavigationTomada {

    fun navigationTomada(
        navController: NavController,
        tomada: DtoResponseEletricHouse.DtoTomada
    ) {

        navController.navigate(
            tomada
        )

    }
}