package com.example.nativeeletrichouse.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nativeeletrichouse.data.db.entity.AmbienteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AmbienteDao {

    @Query("SELECT * FROM AmbienteEntity")
    fun findAll(): Flow<List<AmbienteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(ambiente: AmbienteEntity)

    @Delete
    suspend fun delete(ambiente: AmbienteEntity)

}