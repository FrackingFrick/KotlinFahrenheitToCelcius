package com.example.celciustofahrenheit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.runtime.setValue

import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.celciustofahrenheit.ui.theme.CelciusToFahrenheitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CelciusToFahrenheitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FahrenheitCelcius()
                }
            }
        }
    }
}

@Composable
fun FahrenheitCelcius() {
    var temperature: String by remember{ mutableStateOf("") }
    var fahrenheitSelected: Boolean by remember{ mutableStateOf(true) }
    val temperatureToConvert: Float=temperature.toFloatOrNull() ?:0.0f
    val result= when(fahrenheitSelected){
        true->if(temperatureToConvert>0.0f) (temperatureToConvert -32)/1.8f else 0.0f
        false-> if(temperatureToConvert>0.0f) (temperatureToConvert*1.8f)+32 else 0.0f
    }

    Column (
        verticalArrangement= Arrangement.spacedBy(8.dp),
        modifier=Modifier.padding(8.dp)

    ){
        Text(
            text = "Fahrenheit/Celcius",
            color=MaterialTheme.colors.primary,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value =temperature ,
            onValueChange = {temperature=it},
            label={Text(text="temperature")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Row(verticalAlignment = Alignment.CenterVertically){
            RadioButton(
                selected = fahrenheitSelected,
                onClick = { fahrenheitSelected=true }
            )
            Text(text="Fahrenheit to Celcius")
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            RadioButton(
                selected = !fahrenheitSelected, 
                onClick = {fahrenheitSelected=false}
            )
            Text(text = "Celcius to Fahrenheit")
        }
        Text(text=String.format("%.2f", result))
    }

}


