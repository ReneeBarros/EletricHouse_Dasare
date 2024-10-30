package com.example.nativeeletrichouse.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nativeeletrichouse.data.db.dao.AmbienteDao
import com.example.nativeeletrichouse.data.db.entity.AmbienteEntity

@Database(entities = [AmbienteEntity::class], version = 1)
abstract class EletricHouseDataBase: RoomDatabase() {

    abstract fun ambienteDao(): AmbienteDao

}