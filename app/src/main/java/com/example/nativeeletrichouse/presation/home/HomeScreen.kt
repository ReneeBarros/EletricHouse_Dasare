package com.example.nativeeletrichouse.presation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nativeeletrichouse.R
import com.example.nativeeletrichouse.presation.components.CardItem
import com.example.nativeeletrichouse.presation.components.Spinner
import kotlinx.serialization.Serializable




class HomeScreen {

    private val stateHolder = HomeStateHolder()


    @Composable
     fun HomeApp() {

        val uiState by stateHolder.uiState.collectAsState()



        Column(
            modifier = Modifier.fillMaxSize().padding(2.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                colors = CardColors(
                    contentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    disabledContentColor = Color.Gray
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
/*                    Spinner(
                        listaItens = stringArrayResource(id = R.array.ListaAmbientes),
                        itemSelecionado = uiState.ambiente ) {
                        stateHolder.setAmbiente(it)
                    }*/

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth().background(Color.Transparent),
                        shape = RoundedCornerShape(30.dp),
                        value = uiState.nomeObra,
                        onValueChange = {
                            stateHolder.setNomeObra(it)
                        },
                        label = { Text(text = "Nome da Obra")},
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            disabledTextColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.Gray,
                            unfocusedTextColor = Color.Gray,
                            disabledLabelColor = Color.Gray,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                }
               // Spacer(modifier = Modifier.height(6.dp))

            }


            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.spacedBy(10.dp)
            ) {
                CardItem(img = R.drawable.luminaria, description ="lampada",
                    {
                        stateHolder.setIluminacao(uiState.iluminacao)
                    },
                    uiState.iluminacao, title = "Calcular Iluminação")

                CardItem(img = R.drawable.tomada, description ="Tomada",
                    {
                        stateHolder.setTomada(uiState.tomada)
                    },
                    uiState.tomada, title = "Calcular Tomada" )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.spacedBy(10.dp)
            ) {
                CardItem(img = R.drawable.arcond, description ="arCondicionado",
                    {
                        stateHolder.setArCondicionado(uiState.arCondicionado)
                    },
                    uiState.arCondicionado, title = "Calcular Ar-condic" )

                CardItem(img = R.drawable.cabeletrico, description ="calculadora",
                    {
                        stateHolder.setCalculo(uiState.calculo)
                    },
                    uiState.calculo,
                    title = "Calculo Diversos"
                )
            }
        }
    }
}

