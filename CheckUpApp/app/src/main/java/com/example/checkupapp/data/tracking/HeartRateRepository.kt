package com.example.checkupapp.data.tracking

import kotlinx.coroutines.flow.Flow


interface HeartRateRepository {
    fun getAllItemsStream(): Flow<List<HeartRateReading>>

    suspend fun insertItem(item: HeartRateReading)

    suspend fun deleteLast()

}