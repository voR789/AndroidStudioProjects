package com.example.checkupapp.ui.elements
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.checkupapp.data.tracking.HeartRateReading
import com.example.checkupapp.data.userInfo.UserData
import com.example.checkupapp.data.userInfo.UserDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserDataViewModel(private val repository: UserDataRepository): ViewModel() {
    val userData = MutableLiveData<UserData>()

    init {
        viewModelScope.launch {
            repository.getRow().collect { data ->
                userData.value = data
            }
        }
    }

    fun insertUserData(userData: UserData){
        viewModelScope.launch {
            repository.insert(userData)
        }
    }

    fun deleteLast(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

}