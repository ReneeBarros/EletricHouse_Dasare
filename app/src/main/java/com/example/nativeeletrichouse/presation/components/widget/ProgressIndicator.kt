package com.example.nativeeletrichouse.presation.components.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nativeeletrichouse.presation.theme.Dimension


@Composable
fun ProgressIndicator(){
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        CircularProgressIndicator(
            modifier = Modifier
                .size(80.dp),
            strokeWidth = Dimension.medium,
            color = Color.Blue
        )
        Text(text="Carregando",
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.SansSerif
        )
    }
}