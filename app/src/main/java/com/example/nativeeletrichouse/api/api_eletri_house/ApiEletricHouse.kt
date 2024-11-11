package com.example.nativeeletrichouse.api.api_eletri_house

import android.annotation.SuppressLint
import androidx.compose.ui.platform.LocalContext
import com.example.nativeeletrichouse.data.reponse.ResponseCaculateAmbiente
import com.example.nativeeletrichouse.data.reponse.ResponseCalculoArCond
import com.example.nativeeletrichouse.data.reponse.ResponseCalculoIluminacao
import com.example.nativeeletrichouse.data.reponse.ResponseCalculoTomada
import com.example.nativeeletrichouse.data.request.RequestCalculateAmbiente
import com.example.nativeeletrichouse.error.handleUnresolvedAddressException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.io.IOException
import java.nio.channels.UnresolvedAddressException

class ApiEletricHouse {

    private val client = HttpClient(){

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
            })
        }
        install(HttpCache)
        install(HttpTimeout){
            requestTimeoutMillis = 3000
        }
    }

    private val BASE_URL = "http://dasare-eletrichouse-dev.sa-east-1.elasticbeanstalk.com/"


    val context = LocalContext

    suspend fun apiIluminacao(
        requestIluminacao: Any
    ):ResponseCalculoIluminacao{
        val urlIluminacao = "api/v1/eletrichouse/calculariluminacao"

        val responseIlum = client.post(BASE_URL + urlIluminacao ){
            contentType(ContentType.Application.Json)
            setBody(requestIluminacao)
        }
        return responseIlum.body()
    }

    suspend fun apiTomada(
        requestTomada: Any
    ):ResponseCalculoTomada{
        val urlTomada = "api/v1/eletrichouse/calculartomada"

        val responseTomada = client.post(BASE_URL + urlTomada ){
            contentType(ContentType.Application.Json)
            setBody(requestTomada)
        }
        return responseTomada.body()
    }

    suspend fun apiIArCond(
        requestArCond: Any
    ):ResponseCalculoArCond{
        val urlArCond = "api/v1/eletrichouse/calculararcondicionado"

        val responseArCond = client.post(BASE_URL + urlArCond ){
            contentType(ContentType.Application.Json)
            setBody(requestArCond)
        }
        return responseArCond.body()
    }

    suspend fun apiCalcularAmbiente(
        requestCalAmbiente: Any
    ):ResponseCaculateAmbiente{
        val urlCalAmbinete = "api/v1/eletrichouse/calcularAmbiente"

        val responseAmbiente = client.post(BASE_URL + urlCalAmbinete ){
            contentType(ContentType.Application.Json)
            setBody(requestCalAmbiente)
        }
        return responseAmbiente.body()
    }

    suspend fun apiCalcularListaDeAmbiente(
        requestCalAmbiente: List<RequestCalculateAmbiente>
    ):List<ResponseCaculateAmbiente>{

        val urlCalListAmbiente = "api/v1/eletrichouse/calcularListaAmbiente"

        //val urlCalAmbinete = "http://192.168.18.10:8080/api/v1/eletrichouse/calcularListaAmbiente"

        val responseAmbiente = client.post( BASE_URL + urlCalListAmbiente){
            contentType(ContentType.Application.Json)
            setBody(requestCalAmbiente)
        }
        return responseAmbiente.body<List<ResponseCaculateAmbiente>>()
    }
}