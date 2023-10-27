package com.example.inventory.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.checkupapp.CheckUpApplication
import com.example.checkupapp.data.AppContainer
import com.example.checkupapp.data.tracking.HeartRateRepository
import com.example.checkupapp.ui.elements.HeartRateViewModel
import com.example.checkupapp.ui.elements.UserDataViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    fun Factory(container: AppContainer) = viewModelFactory() {
        lateinit var repository1: HeartRateRepository
        lateinit var repository2: UserDataViewModel
        initializer {
            HeartRateViewModel(container.heartRateRepository)
        }
        initializer {
            UserDataViewModel(container.userDataRepository)
        }
    }
}

fun CreationExtras.inventoryApplication(): CheckUpApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CheckUpApplication)