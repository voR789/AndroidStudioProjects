package com.example.checkupapp.ui.elements
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkupapp.data.tracking.HeartRateReading
import com.example.checkupapp.data.tracking.HeartRateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeartRateViewModel(private val repository: HeartRateRepository): ViewModel() {
    private val privData = MutableStateFlow<List<HeartRateReading>>(emptyList())
    val userData: StateFlow<List<HeartRateReading>> = privData
    init {
        viewModelScope.launch {
            repository.getAllItemsStream().collect { newData ->
                privData.value = newData
            }
        }
    }

    fun insertData(newData: HeartRateReading) {
        viewModelScope.launch {
            repository.insertItem(newData)
        }
    }

    fun deleteLastData() {
        viewModelScope.launch {
            repository.deleteLast()
        }
    }



}