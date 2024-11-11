package com.example.nativeeletrichouse.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nativeeletrichouse.data.db.EletricHouseDataBase
import com.example.nativeeletrichouse.data.db.dao.AmbienteDao
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

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // SQL statements for schema changes
        database.execSQL("ALTER TABLE AmbienteEntity ADD COLUMN caboIluminacao REAL NOT NULL")
        database.execSQL("ALTER TABLE AmbienteEntity ADD COLUMN caboTomada REAL NOT NULL")
        database.execSQL("ALTER TABLE AmbienteEntity ADD COLUMN caboArCond REAL NOT NULL")
        // ... other migration logic
    }
}

val storeModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            EletricHouseDataBase::class.java, "eletric_house.db"

        ).addMigrations(MIGRATION_1_2)
            .build()
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