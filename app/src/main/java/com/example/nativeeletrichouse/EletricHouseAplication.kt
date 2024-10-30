package com.example.nativeeletrichouse

import android.app.Application
import com.example.nativeeletrichouse.di.storeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class EletricHouseAplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@EletricHouseAplication)
            modules(storeModule)
        }
    }
}