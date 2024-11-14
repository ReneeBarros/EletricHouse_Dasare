package com.example.nativeeletrichouse.presation.components.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FloatingActionButtonCustomize(
    modifier: Modifier = Modifier,
    onclik: ()->Unit = {},
    icon: Unit,
    name: String,
){
    FloatingActionButton(
        onClick = {
            onclik()

        },
        modifier = modifier
            .size(75.dp, 70.dp),
        containerColor = Color.White,

        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            icon
            Text(text = name)
        }

    }


}