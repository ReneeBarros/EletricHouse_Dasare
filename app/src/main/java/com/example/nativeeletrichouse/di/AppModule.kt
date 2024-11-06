package com.example.nativeeletrichouse.di

import android.content.Context
import androidx.room.Room
import com.example.nativeeletrichouse.data.db.EletricHouseDataBase
import com.example.nativeeletrichouse.domain.calcularcabo.implemnets.CalculoCaboEletricoImp
import com.example.nativeeletrichouse.domain.calcularcabo.intefaces.CalculoCaboEletricoInterFace
import com.example.nativeeletrichouse.repository.AmbienteRepository
import com.example.nativeeletrichouse.viewmodel.ViewModelAmbiente
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {


}

val storeModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            EletricHouseDataBase::class.java, "eletric_house.db"
        ).build()
    }
    single {
        get<EletricHouseDataBase>().ambienteDao()
    }
    factory<AmbienteRepository> { AmbienteRepository(get()) }
    viewModel { ViewModelAmbiente(get())}

    factory<CalculoCaboEletricoInterFace> {
        CalculoCaboEletricoImp()
    }

}