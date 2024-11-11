package com.example.nativeeletrichouse.presation.components.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class) 
@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    navController: NavController,
    fontSize: TextUnit = 16.sp
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        },

        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                content = {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Return")
                }
            )
        }
    )
}