package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Grid(stringResource(R.string.q1_title), stringResource(R.string.q1desc),
                        stringResource(R.string.q2_title),
                        stringResource(R.string.q2_desc),
                        stringResource(R.string.q3_title),
                        stringResource(R.string.q3_desc),
                        stringResource(R.string.q4_title), stringResource(R.string.q4_desc)
                    )
                }
            }
        }
    }
}

@Composable
fun Card(title: String, desc: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(
                    bottom = 16.dp
                )
        )
        Text(
            text = desc,
            textAlign = TextAlign.Justify,
        )
    }
}

@Composable
fun Grid(t1: String,d1: String,t2: String,d2: String,t3: String,d3: String,t4: String,d4: String, modifier: Modifier = Modifier){
    Column(modifier.fillMaxWidth(1F)) {
        Row(modifier = modifier.weight(1F)){
            Card(t1,d1,
                modifier
                    .weight(1F)
                    .background(color = Color(0xFFEADDFF)))
            Card(t2,d2,
                modifier
                    .weight(1F)
                    .background(color = Color(0xFFD0BCFF)))
        }
        Row(modifier = modifier.weight(1F)){
            Card(t3,d3,
                modifier
                    .weight(1F)
                    .background(color = Color(0xFFB69DF8)))
            Card(t4,d4,
                modifier
                    .weight(1F)
                    .background(color = Color(0xFFF6EDFF)))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        Grid(stringResource(R.string.q1_title),
            stringResource(R.string.q1desc),
            stringResource(R.string.q2_title),
            stringResource(R.string.q2_desc),
            stringResource(R.string.q3_title),
            stringResource(R.string.q3_desc),
            stringResource(R.string.q4_title),
            stringResource(R.string.q4_desc)
        )
    }
}