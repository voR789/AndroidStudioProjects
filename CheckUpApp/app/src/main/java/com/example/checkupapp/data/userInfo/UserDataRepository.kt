package com.example.checkupapp.data.userInfo

import com.example.checkupapp.data.tracking.HeartRateReading
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    suspend fun insert(user: UserData)

    suspend fun getRow(): Flow<UserData>

    suspend fun deleteAll()

    suspend fun getHeight()
    suspend fun getAge()
    suspend fun isMale()
}