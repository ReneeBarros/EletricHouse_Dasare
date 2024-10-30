package com.example.nativeeletrichouse.presation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Spinner(
    listaItens: Array<String>,
    itemSelecionado: String,
    selecionandoItens: (String) -> Unit,
    label:String = ""

) {
    var expansivo by remember { mutableStateOf(false) }

    Button(
        shape = RoundedCornerShape(2.dp),
        onClick = { expansivo = true },
        content = {
            Text(
                text = itemSelecionado,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDown")
            DropdownMenu(
                expanded = expansivo,
                onDismissRequest = { expansivo = !expansivo }
            ) {
                listaItens.forEach {
                    DropdownMenuItem(
                        onClick = {
                            expansivo = !expansivo
                            selecionandoItens(it)
                        }, text = {
                            Text(text = it)
                        }
                    )
                }
            }
        },

        modifier = Modifier.fillMaxWidth().height(50.dp)
    )
}