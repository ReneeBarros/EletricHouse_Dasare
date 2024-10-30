package com.example.nativeeletrichouse.presation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpinnerentradaDados(

    listaItens: Array<String>,
    itemSelecionado: String,
    selecionandoItens: (String) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
){
    var expansivo by remember { mutableStateOf(false) }

    OutlinedButton(
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

        modifier = modifier.fillMaxWidth()
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerTextDados(
    listaItens: Array<String>,
    itemSelecionado: String,
    label:String,
    selecionandoItens: (String) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()

){
    var expansivo by remember { mutableStateOf(false) }

   ExposedDropdownMenuBox(
       expanded = expansivo,
       onExpandedChange = { expansivo = !expansivo }
   ) {

       OutlinedTextField(
           value = itemSelecionado,
           onValueChange = {},
           readOnly = true,
           label = { Text(text = label) },
           trailingIcon = {
               ExposedDropdownMenuDefaults.TrailingIcon(expanded = expansivo)
           },
           colors = OutlinedTextFieldDefaults.colors(
               disabledContainerColor = Color.Transparent,
               focusedContainerColor = Color.Transparent,
               disabledTextColor = Color.Black,
               unfocusedTextColor = Color.Black,
               focusedTextColor = Color.Black
           ),
           shape = RoundedCornerShape(2.dp)
       )

       ExposedDropdownMenu(
           expanded = expansivo,
           onDismissRequest = {expansivo = false},
           content = {
               listaItens.forEach {
                   DropdownMenuItem(
                       text = { Text(text = it) },
                       onClick = {
                           expansivo = false
                           selecionandoItens(it)
                       }
                   )
               }
           }
       )
   }
}

@Composable
fun spinnerTextValue(
    listaItens: Array<String>,
    itemSelecionado: String,
    label:String,
    selecionandoItens: (String) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
){
  var expanded by remember { mutableStateOf(false) }
  var selecionado by remember { mutableStateOf(listaItens[0]) }

    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
      Card(
          border = BorderStroke(1.dp, Color.Black),
          modifier = Modifier.fillMaxWidth().height(55.dp),
          shape = RoundedCornerShape(5.dp),
          colors = CardColors(
              containerColor = Color.Transparent,
              contentColor = Color.Gray,
              disabledContainerColor = Color.Transparent,
              disabledContentColor = Color.Black
          )
      ) {
          Row(modifier = modifier.fillMaxSize().padding(5.dp)
              .clickable { expanded = !expanded },
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Absolute.SpaceBetween
          ) {
              Text(text = selecionado, fontSize = 16.sp)
              Icon(Icons.Filled.ArrowDropDown, contentDescription = "upAndDonw")
              DropdownMenu(
                  expanded = expanded,
                  onDismissRequest = {expanded = false}
              ) {
                  listaItens.forEachIndexed { intex,  ambient->
                      DropdownMenuItem(
                          onClick = {
                              expanded = false
                              selecionandoItens(listaItens[intex])
                              selecionado = listaItens[intex]
                          },
                          text = {
                              Text(text = ambient)
                          }
                      )
                  }
              }
          }
      }
  }
}