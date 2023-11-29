package com.example.progresspal.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.progresspal.R
import com.example.progresspal.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePage(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ppall),
            contentDescription = null,
            modifier = Modifier
                .size(500.dp)
                .padding(2.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "your ultimate ally in your tasks ", style = TextStyle(fontSize = 15.sp, fontFamily = FontFamily.Monospace))


        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(20.dp))


        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    navController.navigate(Routes.TodoScreen.route)

                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFDD308E))
            ) {
                Text(text = "Continue")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}