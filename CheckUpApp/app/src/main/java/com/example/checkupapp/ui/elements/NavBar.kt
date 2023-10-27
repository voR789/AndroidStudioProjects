package com.example.checkup

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkupapp.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int
) {
    object Home :
        BottomNavItem(
            "Home",
            R.drawable.baseline_home_24
        )

    object HeartRate :
        BottomNavItem(
            "Heart Rate",
            R.drawable.baseline_favorite_border_24
        )

    object BloodPressure :
        BottomNavItem(
            "Blood Pre.",
            R.drawable.blood_pressure_24px
        )

    object BloodOxy :
        BottomNavItem(
            "Blood Oxy.",
            R.drawable.baseline_bloodtype_24
        )
    object BMI :
        BottomNavItem(
            "BMI",
            R.drawable.baseline_monitor_weight_24
        )
}

@Composable
fun BottomNavigation(tab: MutableState<String>) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.HeartRate,
        BottomNavItem.BloodOxy,
        BottomNavItem.BloodPressure,
        BottomNavItem.BMI
    )

    NavigationBar {
        items.forEach { item ->
            AddItem(
                tab = tab,
                screen = item
            ) { newTab ->
                tab.value = newTab
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    tab: MutableState<String>,
    screen: BottomNavItem,
    onTabChange: (String) -> Unit
) {
    NavigationBarItem(
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = screen.title,
            )
        },
        label = {

            Text(
                text = screen.title,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                lineHeight = 12.sp,
            )
        },

        // Display if the icon it is select or not
        selected = true,

        // Always show the label bellow the icon or not
        alwaysShowLabel = true,

        // Click listener for the icon
        onClick = { onTabChange(screen.title) },

        // Control all the colors of the icon
        colors = NavigationBarItemDefaults.colors()
    )
}