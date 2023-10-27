package com.example.checkupapp.data

import android.content.Context
import com.example.checkupapp.data.tracking.HeartRateDatabase
import com.example.checkupapp.data.tracking.HeartRateRepository
import com.example.checkupapp.data.tracking.OffLineHeartRateRepository
import com.example.checkupapp.data.userInfo.OffLineUserDataRepository
import com.example.checkupapp.data.userInfo.UserDataDatabase
import com.example.checkupapp.data.userInfo.UserDataRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val heartRateRepository: HeartRateRepository
    val userDataRepository: UserDataRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    override val heartRateRepository: HeartRateRepository by lazy {
        OffLineHeartRateRepository(HeartRateDatabase.getDatabase(context).heartRateDAO())
    }
    override val userDataRepository: UserDataRepository by lazy {
        OffLineUserDataRepository(UserDataDatabase.getDatabase(context).userDataDAO())
    }
}