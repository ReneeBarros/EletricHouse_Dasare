package com.example.nativeeletrichouse.presation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardItem(
    img: Int,
    description:String,
    clickAciton:() ->Unit,
    isSelected:Boolean,
    title:String,
    modifier: Modifier = Modifier,
    textCentral:Boolean = false,
    textCentralConteudo:String=""

){



    Card(
        onClick = { clickAciton() },
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = modifier
            .size(180.dp, 250.dp)
            .background(color = Color.White),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            disabledContainerColor = Color.White
        )
        //border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {

            Column(
                modifier = modifier
                    .size(180.dp, 300.dp)
                    .background(color = Color.White),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = modifier.size(180.dp, 180.dp),
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        disabledContainerColor = Color.White
                    )
                ) {
                    Image(
                        modifier = modifier.size(180.dp, 180.dp),
                        painter = painterResource(id = img),
                        contentDescription = description,
                        contentScale = ContentScale.Crop,
                        )
                }
                Spacer(modifier = Modifier.height(20.dp))


                Text(
                    text = title, fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Light
                )

            }

            if(textCentral){
                Column(
                    modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Card(
                        modifier = Modifier.size(width = 185.dp, height = 30.dp).align(alignment = Alignment.CenterHorizontally),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.hsl(1f,0.5f,1f,0.8f)
                        ),

                        ) {
                        Text(text = textCentralConteudo, textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal, fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally).padding(top = 3.dp)
                        )
                    }

                }

            }


        }
    }
}