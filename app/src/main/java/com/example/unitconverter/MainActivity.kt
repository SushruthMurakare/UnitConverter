package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier) {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("0.00") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var isExpandedInput by remember { mutableStateOf(false) }
    var isExpandedOutput by remember { mutableStateOf(false) }
    var conversionFactorInput = remember { mutableStateOf(1.00) }
    var conversionFactorOutput = remember { mutableStateOf(1.00) }

    fun Converter() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        outputValue =
            ((inputValueDouble * conversionFactorInput.value * 100 / conversionFactorOutput.value).roundToInt() / 100.0).toString()

    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter",  style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            Converter()
        },
            label = { Text("Enter value") })
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Box {
                // Input Button
                Button(onClick = { isExpandedInput = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(
                    expanded = isExpandedInput,
                    onDismissRequest = { isExpandedInput = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") },
                        onClick = {
                            isExpandedInput = false
                            inputUnit = "Centimeters"
                            conversionFactorInput.value = 0.01
                            Converter()
                        })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        isExpandedInput = false
                        inputUnit = "Meters"
                        conversionFactorInput.value = 1.0
                        Converter()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        isExpandedInput = false
                        inputUnit = "Feet"
                        conversionFactorInput.value = 0.3048
                        Converter()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") },
                        onClick = {
                            isExpandedInput = false
                            inputUnit = "Millimeters"
                            conversionFactorInput.value = 0.001
                            Converter()
                        })
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box {
                //Output Button
                Button(onClick = { isExpandedOutput = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(
                    expanded = isExpandedOutput,
                    onDismissRequest = { isExpandedOutput = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        isExpandedOutput = false
                        outputUnit = "Centimeters"
                        conversionFactorOutput.value = 0.01
                        Converter()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        isExpandedOutput = false
                        outputUnit = "Meters"
                        conversionFactorOutput.value = 1.0
                        Converter()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        isExpandedOutput = false
                        outputUnit = "Feet"
                        conversionFactorOutput.value = 0.3048
                        Converter()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        isExpandedOutput = false
                        outputUnit = "Millimeters"
                        conversionFactorOutput.value = 0.001
                        Converter()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text("Result : $outputValue",
            style = MaterialTheme.typography.headlineLarge)
    }
}


//@Preview(showBackground = true)
//fun GreetingPreview() {
//    UnitConverterTheme {
//        Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
//            UnitConverter(Modifier.padding(innerPadding))
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            UnitConverter(Modifier.padding(innerPadding))
        }
    }
}