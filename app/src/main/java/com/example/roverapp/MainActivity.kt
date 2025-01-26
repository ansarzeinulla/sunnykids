package com.example.roverapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roverapp.ui.theme.RoverAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RoverAppTheme {
                RoverAppUI(
                    onFetchData = { showToast("Fetching data") },
                    onForward = { showToast("FORWARD") },
                    onBackward = { showToast("BACKWARD") },
                    onTurnRight = { showToast("TURN RIGHT") },
                    onTurnLeft = { showToast("TURN LEFT") },
                    onFlashlightToggle = { showToast("Flashlight toggled") }
                )
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
@Composable
fun RoverAppUI(
    onFetchData: () -> Unit,
    onForward: () -> Unit,
    onBackward: () -> Unit,
    onTurnRight: () -> Unit,
    onTurnLeft: () -> Unit,
    onFlashlightToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // First column (Left) - Rotation buttons
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center, // Center buttons horizontally
                verticalAlignment = Alignment.CenterVertically // Center buttons vertically
            ) {
                // Rotation Buttons (Left and Right centered)
                Button(onClick = onTurnLeft, modifier = Modifier.height(80.dp)) {
                    Text("↺", fontSize = 48.sp)
                }
                Spacer(modifier = Modifier.width(16.dp)) // Spacer between buttons
                Button(onClick = onTurnRight, modifier = Modifier.height(80.dp)) {
                    Text("↻", fontSize = 48.sp)
                }
            }
        }

        // Second column (Center) - Fetch and Flashlight buttons on top, Labels on bottom
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp), // Automatically space the buttons and labels
        ) {
            Button(onClick = onFlashlightToggle, modifier = Modifier.height(50.dp)) {
                Text("FLASHLIGHT", fontSize = 24.sp)
            }

            Button(onClick = onFetchData, modifier = Modifier.height(50.dp)) {
                Text("FETCH DATA", fontSize = 24.sp)
            }

            // Spacer between buttons and labels
            Spacer(modifier = Modifier.weight(1f))

            // Labels (Temperature, Brightness, Humidity)
            val temperature = remember { mutableStateOf("23°C") }
            val brightness = remember { mutableStateOf("75%") }
            val humidity = remember { mutableStateOf("45%") }

            Column {
                Text(text = "Temperature: ${temperature.value}", fontSize = 20.sp)
                Text(text = "Brightness: ${brightness.value}", fontSize = 20.sp)
                Text(text = "Humidity: ${humidity.value}", fontSize = 20.sp)
            }
        }

        // Third column (Right) - Movement buttons (Forward and Backward)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Forward Button (Top Centered)
            Button(
                onClick = onForward,
                modifier = Modifier.height(80.dp).fillMaxWidth()
            ) {
                Text("↑", fontSize = 48.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Backward Button (Bottom Centered)
            Button(
                onClick = onBackward,
                modifier = Modifier.height(80.dp).fillMaxWidth()
            ) {
                Text("↓", fontSize = 48.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoverAppUI() {
    RoverAppTheme {
        RoverAppUI(
            onFetchData = {},
            onForward = {},
            onBackward = {},
            onTurnRight = {},
            onTurnLeft = {},
            onFlashlightToggle = {}
        )
    }
}