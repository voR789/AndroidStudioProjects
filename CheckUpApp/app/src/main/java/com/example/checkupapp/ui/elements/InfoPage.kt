package com.example.checkupapp.ui.elements

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.checkupapp.CheckUpApplication
import com.example.checkupapp.data.tracking.HeartRateReading
import com.example.checkupapp.data.userInfo.UserData
import com.example.inventory.ui.AppViewModelProvider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoDisplay(innerPadding: PaddingValues, tab: MutableState<String>) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxSize(.85F),
    ) {
        Text(
            text = tab.value
        )
        when (tab.value) {
            "Home" -> HomePage()
            "Heart Rate" -> HeartRatePage()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomePage() {


    Column {
        val appContainer =
            (LocalContext.current.applicationContext as CheckUpApplication).container
        val viewModel: UserDataViewModel =
            viewModel(factory = AppViewModelProvider.Factory(appContainer))
        val userData by viewModel.userData.observeAsState()

        var height by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var gender by remember { mutableStateOf("") }
        var height2 by remember { mutableStateOf("") }
        var age2 by remember { mutableStateOf("") }
        var gender2 by remember { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        var buttonText = "Submit basic data"


        TextField(
            value = height,
            onValueChange = { newText ->
                height = newText.trimStart { it == '0' }
            },
            label = { Text("Enter height (in cm)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }
            )
        )
        TextField(value = age,
            onValueChange = { newText ->
                age = newText.trimStart { it == '0' }
            },
            label = { Text("Enter age") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()

            }
            )
        )
        TextField(
            value = gender,
            onValueChange = { newText ->
                gender = newText
            },
            label = { Text("Enter bio gender: male/female ") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()

            }
            )
        )
        Button(
            onClick = {
                if (height.toIntOrNull() != null && age.toIntOrNull() != null && gender != "") {
                    viewModel.deleteLast()
                    viewModel.insertUserData(
                        UserData(
                            height = height.toInt(),
                            age = age.toInt(),
                            gender = if (gender.contains(
                                    "male",
                                    ignoreCase = true
                                )
                            ) true else false
                        )
                    )
                } else {
                    buttonText = "Try again"
                }
                height = ""
                age = ""
                gender = ""
            }
        )
        {
            Text(
                text = buttonText
            )
        }

        Text(
            text = "Current user data:\n" +
                    "Height: ${userData?.height}\n" +
                    "Age: ${userData?.age}\n" +
                    "Gender: ${
                        if(userData?.gender == true)
                            "Male" 
                        else 
                            "Female"
                        }"
        )
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HeartRatePage() {
    var input by remember { mutableStateOf("") }
    val appContainer = (LocalContext.current.applicationContext as CheckUpApplication).container
    val viewModel: HeartRateViewModel =
        viewModel(factory = AppViewModelProvider.Factory(appContainer))
    val readings by viewModel.userData.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = input,
        onValueChange = { newText ->
            input = newText.trimStart { it == '0' }
        },
        label = { Text("Input daily heart rate") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            val number = input.toIntOrNull()
            if (number != null) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
                val reading = HeartRateReading(
                    reading = number,
                    day = (current.format(formatter))!!.toString()
                )
                viewModel.insertData(reading)
                keyboardController?.hide()
                input = ""
            }
        }
        )
    )
    Button(onClick = { viewModel.deleteLastData() }) {
        Text(
            text = "Delete Last User Data"
        )
    }
    LazyColumn {
        item {
            Row {
                Text(text = "Heart Rate", modifier = Modifier.weight(1f))
                Text(text = "Timestamp", modifier = Modifier.weight(1f))
            }
        }
        items(readings) { reading ->
            Row {
                Text(
                    text = reading.reading.toString(),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = reading.day,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}