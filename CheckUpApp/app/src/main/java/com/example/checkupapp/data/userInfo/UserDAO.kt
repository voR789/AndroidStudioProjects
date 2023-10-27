package com.example.checkupapp.data.userInfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.checkupapp.data.tracking.HeartRateReading
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query("SELECT * FROM UserData LIMIT 1")
    fun getRow(): Flow<UserData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrUpdate(userData: UserData)

    @Query("DELETE FROM UserData")
    suspend fun deleteAll()

    @Query("SELECT height FROM UserData")
    suspend fun getHeight(): Int
    @Query("SELECT age FROM UserData")
    suspend fun getAge(): Int
    @Query("SELECT gender FROM UserData")
    suspend fun isMale(): Boolean
}