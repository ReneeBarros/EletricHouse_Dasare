package com.example.nativeeletrichouse.domain.datalogic


import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.nativeeletrichouse.api.api_eletri_house.ApiEletricHouse
import com.example.nativeeletrichouse.data.request.RequestCalculateAmbiente
import com.example.nativeeletrichouse.error.handleUnresolvedAddressException
import com.example.nativeeletrichouse.maper.MapperResponseApiToResponseUi
import com.example.nativeeletrichouse.presation.getData.DataFromUiUiState
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.nio.channels.UnresolvedAddressException

class DataLogic {

    fun CalculateDataLogic(
        uiState: DataFromUiUiState,
        navController: NavController,
        context: Context,
        listAmbienteHome: MutableList<RequestCalculateAmbiente> ,
        isLoading: Boolean,
        scope: CoroutineScope
    ) {

        if(
            uiState.insertAmbiente.isEmpty()||
            uiState.largura.isEmpty()||
            uiState.comprimento.isEmpty()||
            uiState.tensao.isEmpty()||
            uiState.insertNameAmb.isEmpty()
        ){
            FancyToast.makeText(context,
                "Necessario Preencher Todos os Campos",
                FancyToast.LENGTH_SHORT, FancyToast.WARNING,false).show()
        }
        else{

            var request = RequestCalculateAmbiente(
                uiState.insertAmbiente,
                uiState.insertNameAmb,
                uiState.comprimento.replace(",", ".").toDouble(),
                uiState.insidenciaSolar,
                uiState.largura.replace(",", ".").toDouble(),
                uiState.potenciaLamp,
                uiState.quantEletrodomestico.toInt(),
                uiState.quantPessoaAmbient.toInt(),
                uiState.tensao.toInt()
            )

            if(uiState.insertAmbiente=="Banheiro"|| uiState.insertAmbiente =="Escada/Dispensa/Garagem/"){
                request = RequestCalculateAmbiente(
                    uiState.insertAmbiente,
                    uiState.insertNameAmb,
                    uiState.comprimento.replace(",", ".").toDouble(),
                    false,
                    uiState.largura.replace(",", ".").toDouble(),
                    uiState.potenciaLamp,
                    0,
                    0,
                    uiState.tensao.toInt()
                )
            }

            if(listAmbienteHome.isEmpty()){
                listAmbienteHome.add(request)

                scope.launch {
                    //loading = true
                    val listAmbienteCalculado = mutableListOf<String>()
                    val json = Json{
                        prettyPrint = true
                        ignoreUnknownKeys = true
                        allowSpecialFloatingPointValues = true
                    }
                    try {
                        val responseApiToResponseUi = MapperResponseApiToResponseUi(ApiEletricHouse().apiCalcularListaDeAmbiente(listAmbienteHome))

                        responseApiToResponseUi.forEach{
                            listAmbienteCalculado.add(json.encodeToString(
                                MapperResponseApiToResponseUi.serializer(),it))
                        }

                        NavigatonDataLogic().navigationDataLogic(
                            navController = navController,
                            listDataLogic = listAmbienteCalculado
                        )

                    } catch (e: UnresolvedAddressException) {
                        handleUnresolvedAddressException(context, e) // Pass the context
                    }
                }
            }else{
                scope.launch {
                    //loading = true
                    val listAmbienteCalculado = mutableListOf<String>()
                    val json = Json{prettyPrint = true}

                    try {
                        val responseApiToResponseUi = MapperResponseApiToResponseUi(ApiEletricHouse().apiCalcularListaDeAmbiente(listAmbienteHome))

                        responseApiToResponseUi.forEach{
                            listAmbienteCalculado.add(json.encodeToString(
                                MapperResponseApiToResponseUi.serializer(),it))
                        }

                        NavigatonDataLogic().navigationDataLogic(
                            navController = navController,
                            listDataLogic = listAmbienteCalculado
                        )

                    } catch (e: UnresolvedAddressException) {
                        handleUnresolvedAddressException(context, e) // Pass the context
                    }
                }
            }
        }
    }

    fun AddDataLogicToList(
        uiState: DataFromUiUiState,
        context: Context,
        listAmbienteHome: MutableList<RequestCalculateAmbiente>)
    {
        if(
            uiState.largura.isEmpty()||uiState.comprimento.isEmpty()||uiState.insertAmbiente=="Escolher Ambiente"
        ){

            Toast.makeText(context, "Necessario Preencher Todos os Campos", Toast.LENGTH_SHORT).show()

        }
        else{

            val request = when{
                uiState.insertAmbiente == "Banheiro" ->
                    RequestCalculateAmbiente(
                        uiState.insertAmbiente,
                        uiState.insertNameAmb,
                        uiState.comprimento.replace(",", ".").toDouble(),
                        false,
                        uiState.largura.replace(",", ".").toDouble(),
                        uiState.potenciaLamp,
                        0,
                        0,
                        uiState.tensao.toInt()
                    )

                uiState.insertAmbiente == "Escada/Dispensa/Garagem" ->
                    RequestCalculateAmbiente(
                        uiState.insertAmbiente,
                        uiState.insertNameAmb,
                        uiState.comprimento.replace(",", ".").toDouble(),
                        false,
                        uiState.largura.replace(",", ".").toDouble(),
                        uiState.potenciaLamp,
                        0,
                        0,
                        uiState.tensao.toInt()
                    )

                else -> {
                    RequestCalculateAmbiente(
                        uiState.insertAmbiente,
                        uiState.insertNameAmb,
                        uiState.comprimento.replace(",", ".").toDouble(),
                        uiState.insidenciaSolar,
                        uiState.largura.replace(",", ".").toDouble(),
                        uiState.potenciaLamp,
                        uiState.quantEletrodomestico.toInt(),
                        uiState.quantPessoaAmbient.toInt(),
                        uiState.tensao.toInt()
                    )
                }
            }


/*            val request = RequestCalculateAmbiente(
                uiState.insertAmbiente,
                uiState.insertNameAmb,
                uiState.comprimento.replace(",", ".").toDouble(),
                uiState.insidenciaSolar,
                uiState.largura.replace(",", ".").toDouble(),
                uiState.potenciaLamp,
                uiState.quantEletrodomestico.toInt(),
                uiState.quantPessoaAmbient.toInt(),
                uiState.tensao.toInt()
            )*/
            listAmbienteHome.add(request)
            FancyToast.makeText(context, "Adicionado", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
        }








    }
}