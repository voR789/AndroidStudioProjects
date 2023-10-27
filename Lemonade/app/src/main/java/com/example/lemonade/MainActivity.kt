package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeIconAndText(modifier: Modifier = Modifier.fillMaxSize()){
    var phase by remember {mutableStateOf(1)}
    var randomClicks by remember { mutableStateOf((2..4).random()) }
    var painterID =
        when(phase){
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }
    var desc =
        when(phase){
            1 -> stringResource(R.string.lemonTreeDesc)
            2 -> stringResource(R.string.lemonDesc)
            3 -> stringResource(R.string.glassDesc)
            else -> stringResource(R.string.emptyDesc)
        }
    var instructions =
        when(phase){
            1 -> stringResource(R.string.tree)
            2 -> stringResource(R.string.lemon)
            3 -> stringResource(R.string.drink)
            else -> stringResource(R.string.empty)
        }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(
        text = "Lemonade",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.wrapContentHeight(Alignment.Top).background(Color.Yellow).padding(16.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier.wrapContentSize(Alignment.Center).fillMaxSize()
        ) {

            Image(
                painter = painterResource(painterID),
                contentDescription = desc,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .background(Color(105,216,216), RoundedCornerShape((R.drawable.lemon_restart).dp))
                    .clickable {
                        if(phase == 1 || phase == 3) phase++
                        else if(phase == 4) {
                            phase = 1
                            randomClicks = (2..4).random()}
                        else if(randomClicks == 0)
                            phase++
                        else
                            randomClicks--
                    }
            )
            Text(
                text = instructions,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadeApp(){
    LemonadeTheme {
        LemonadeIconAndText()
    }
}