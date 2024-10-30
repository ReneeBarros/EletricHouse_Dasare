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
import com.example.nativeeletrichouse.presation.components.input.InputText
import com.example.nativeeletrichouse.presation.theme.Dimension

@Composable
fun CardGetDataUi(
    onclick1:(String)->Unit ={},
    onclick2:(String)->Unit ={},
    onclick3:(String)->Unit ={},
    painter: Painter,
    label1:String = "",
    label2:String = "",
    label3:String = "",
    placeholder:String="",
    value1:String="",
    value2:String="",
    value3:String="",
    keyboardType1: KeyboardType = KeyboardType.Text,
    keyboardType2: KeyboardType = KeyboardType.Text,
    keyboardType3: KeyboardType = KeyboardType.Text,
    threeInput:Boolean = false,
    twoInput:Boolean = false
){

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

                if(threeInput){
                    InputText(
                        label = label1,
                        onvalueChange = {
                            onclick1(it)
                        },
                        placeholder = placeholder,
                        value = value1,
                        keyboardType = keyboardType1
                    )

                    InputText(

                        label = label2,
                        value = value2,
                        onvalueChange = { item->
                            onclick2(item)
                        },
                        keyboardType = keyboardType2
                    )

                    InputText(

                        label = label3,
                        value = value3,
                        onvalueChange = { item->
                            onclick3(item)
                        },
                        keyboardType = keyboardType3
                    )
                }
                else if(twoInput){
                    InputText(
                        label = label1,
                        onvalueChange = {
                            onclick1(it)
                        },
                        placeholder = placeholder,
                        value = value1,
                        keyboardType = keyboardType1
                    )

                    InputText(

                        label = label2,
                        value = value2,
                        onvalueChange = { item->
                            onclick2(item)
                        },
                        keyboardType = keyboardType2
                    )
                } else{
                    InputText(
                        label = label1,
                        onvalueChange = {
                            onclick1(it)
                        },
                        placeholder = placeholder,
                        value = value1,
                        keyboardType = keyboardType1
                    )
                }
            }
            Image(
                painter, contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(width = 100.dp, height = 100.dp)
            )

        }

    }

}