package com.example.nativeeletrichouse.di

import androidx.room.Room
import com.example.nativeeletrichouse.data.db.EletricHouseDataBase
import com.example.nativeeletrichouse.repository.AmbienteRepository
import com.example.nativeeletrichouse.viewmodel.ViewModelAmbiente
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
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
    factory<AmbienteRepository> { (AmbienteRepository(get())) }
    viewModel { ViewModelAmbiente(get())}
}