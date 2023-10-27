package com.example.checkup

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.*
import androidx.room.*
import com.example.checkupapp.CheckUpApplication
import com.example.checkupapp.ui.elements.InfoDisplay
import com.example.checkupapp.ui.theme.CheckUpAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as CheckUpApplication).container

        setContent {
            CheckUpAppTheme {
                CheckUp()
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CheckUp() {
    var tab = remember {
        mutableStateOf("")
    }
    CheckUpAppTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(tab)
            },
        ) { innerPadding ->
            InfoDisplay(innerPadding = innerPadding, tab = tab)
        }
    }
}
