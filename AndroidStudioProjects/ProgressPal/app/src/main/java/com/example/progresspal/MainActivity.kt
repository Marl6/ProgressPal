package com.example.progresspal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.progresspal.screen.MainScreen
import com.example.progresspal.ui.theme.ProgressPalTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        setContent {
            ProgressPalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var showMainScreen by remember { mutableStateOf(false) }

                    LaunchedEffect(true) {
                        delay(1000)
                        // Set the flag to true to show the MainScreen
                        showMainScreen = true
                    }

                    // Display the appropriate screen based on the flag
                    if (showMainScreen) {
                        MainScreen()
                    } else {
                        SplashScreen()
                    }
                }
            }
        }
    }
}



@Composable
fun SplashScreen() {
    // Customize your splash screen as needed
    // For example, you can use an image or any other composable content

    // Assuming you have an image resource named 'logo' in your 'res/drawable' folder
    val logoImage = painterResource(id = R.drawable.ppall)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = logoImage,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp) // Adjust the size as needed
                .align(Alignment.Center)
        )

        LaunchedEffect(true) {
            delay(1000) // Delay for 1 second
            // Navigate to MainScreen or perform any other action
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProgressPalTheme {
        MainScreen()
    }
}
