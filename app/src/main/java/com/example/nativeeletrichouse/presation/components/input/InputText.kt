package com.example.nativeeletrichouse.presation.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun InputText(
    label:String = "",
    placeholder:String="",
    value:String="",
    onvalueChange:(String) ->Unit={},
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier.fillMaxWidth()
){
    OutlinedTextField(
        modifier = modifier,
        value = value,
        placeholder = { Text(text = placeholder) },
        label = {Text(text = label)},
        onValueChange = { onvalueChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            disabledTextColor = Color.Yellow,
            focusedTextColor = Color.Black,
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.Transparent,
        )
    )
}