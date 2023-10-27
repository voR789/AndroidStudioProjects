package com.example.checkupapp.data.tracking

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HeartRateDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reading: HeartRateReading)

    @Query("DELETE FROM heartratereading WHERE id = (SELECT MAX(id) FROM heartratereading)")
    suspend fun deleteLast()

    @Query("SELECT * FROM HeartRateReading")
    fun getAll(): Flow<List<HeartRateReading>>

}