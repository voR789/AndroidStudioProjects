package com.example.checkupapp.data.userInfo

import com.example.checkupapp.data.tracking.HeartRateReading
import kotlinx.coroutines.flow.Flow

class OffLineUserDataRepository(private val dao: UserDAO): UserDataRepository {
    override suspend fun insert(user: UserData) = dao.insertOrUpdate(user)

    override suspend fun getRow(): Flow<UserData> = dao.getRow()

    override suspend fun deleteAll() { dao.deleteAll() }

    override suspend fun getHeight() {
        dao.getHeight()
    }
    override suspend fun getAge() {
        dao.getAge()
    }

    override suspend fun isMale() {
        dao.isMale()
    }
}