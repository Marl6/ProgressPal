package com.example.progresspal.screen

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.progresspal.R
import com.example.progresspal.Routes
import com.google.firebase.auth.FirebaseAuth
import com.google.mlkit.vision.text.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupPage(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPopupVisible by remember { mutableStateOf(false) }
    var popupMessage by remember { mutableStateOf("") }

    fun showPopupMessage(message: String) {
        popupMessage = message
        isPopupVisible = true
    }

    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ppall),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Sign Up",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = Color(0xFFDD308E),
            style = MaterialTheme.typography.bodyLarge, // or any other desired large style
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Your Email") },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Your Password") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Navigate to MainScreen
                createAccount(email, password, navController, ::showPopupMessage)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFDD308E))
        ) {
            Icon(Icons.Filled.Send, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Create Account")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        ) {
            Text(
                text = "Already have an account?",
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodyLarge
                    .copy(
                        color = Color.Gray,
                    ),
                modifier = Modifier.weight(1f)
                    .padding(start = 20.dp)
            )

            Text(
                text = "Login here",
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodyLarge
                    .copy(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    ),
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clickable { navController.navigate(Routes.StartScreen.route) }
            )
        }

        if (isPopupVisible) {
            PopUp(popupMessage) {
                // Dismiss the popup when clicked
                isPopupVisible = false
            }
        }
    }
}

private fun createAccount(
    email: String,
    password: String,
    navController: NavHostController,
    showPopupMessage: (String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
                navController.navigate(Routes.StartScreen.route)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                showPopupMessage("Authentication failed.")
                reload()
            }
        }
}

@Composable
fun PopUp(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text("Error 404")
        },
        text = {
            Text(message)
        },
        confirmButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(Color(0xFFDD308E))
            ) {
                Text("OK")
            }
        }
    )
}
private fun reload() {
}