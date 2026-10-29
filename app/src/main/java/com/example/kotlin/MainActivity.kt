package com.example.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import kotlin.math.cbrt
import com.example.kotlin.ui.theme.KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Calculator()
            }
        }
    }
}

@Composable
fun Calculator() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var number3 by remember { mutableStateOf("") }
    var symbol by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("Результат появится здесь") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Калькулятор",
            style = MaterialTheme.typography.headlineMedium
        )
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Первое число") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Второе число") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = number3,
            onValueChange = { number3 = it },
            label = { Text("Третье число") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = symbol,
            onValueChange = {
                if (it.length <= 1) symbol = it
            },
            label = { Text("Символ a или g") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val a = number1.replace(',', '.').toDoubleOrNull()
                val b = number2.replace(',', '.').toDoubleOrNull()
                val c = number3.replace(',', '.').toDoubleOrNull()
                val s = symbol.trim().lowercase()

                result = if (a == null || b == null || c == null) {
                    "Ошибка! Введите три числа."
                } else {
                    when (s) {
                        "a" -> {
                            val average = (a + b + c) / 3.0
                            "Среднее арифметическое: $average"
                        }

                        "g" -> {
                            val average = cbrt(a * b * c)
                            "Среднее геометрическое: $average"
                        }

                        else -> "Ошибка! Неизвестный символ."
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Вычислить")
        }

        Text(
            text = result,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    MaterialTheme {
        Calculator()
    }
}