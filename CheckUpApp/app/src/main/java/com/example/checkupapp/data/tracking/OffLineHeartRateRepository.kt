package com.example.checkupapp.data.tracking

import kotlinx.coroutines.flow.Flow

class OffLineHeartRateRepository(private val dao: HeartRateDAO) : HeartRateRepository {
    override fun getAllItemsStream(): Flow<List<HeartRateReading>> = dao.getAll()

    override suspend fun insertItem(item: HeartRateReading) = dao.insert(item)

    override suspend fun deleteLast() { dao.deleteLast() }

}