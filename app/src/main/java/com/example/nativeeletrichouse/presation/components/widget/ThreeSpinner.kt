package com.example.nativeeletrichouse.presation.components.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.nativeeletrichouse.presation.components.Spinner
import com.example.nativeeletrichouse.presation.components.input.InputText
import com.example.nativeeletrichouse.presation.theme.Dimension


@Composable
fun ThreeSpinner(
    getItemPessoa:(String)->Unit ={},
    getItemEletro:(String)->Unit ={},
    getInsidenciaSolar:(Boolean)->Unit ={},
    painter: Painter,

    listitem: Array<String> = arrayOf(),
    listConf: Array<String> = arrayOf(),
    quantPessoaSelec:String,
    quantEletroSelec:String,
    insidenciaSolar:Boolean

) {
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.big),
        elevation = CardDefaults.elevatedCardElevation(Dimension.medium) ,
        shape = RoundedCornerShape(Dimension.medium),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            disabledContainerColor = Color.White
        )

    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(Dimension.medium)
            ) {

 /*               Spinner(listaItens =listitem , itemSelecionado = quantPessoaSelec
                ) {
                    getItemPessoa(it)
                }

                Spinner(listaItens =listitem , itemSelecionado = quantEletroSelec
                ) {
                    getItemEletro(it)
                }

                Spinner(listaItens =listConf , itemSelecionado = insidenciaSolar.toString()
                ) {
                    getInsidenciaSolar(it.toBoolean())
                }*/
            }
            Image(
                painter, contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(width = 100.dp, height = 100.dp)
            )

        }

    }

}