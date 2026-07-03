package com.example.gerenciadorimoveis.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// 1. O modelo de dados que representa o JSON do ViaCEP
data class ViaCepResponse(
    val logradouro: String?,
    val bairro: String?,
    val localidade: String?, // Cidade
    val uf: String?,
    val erro: Boolean?
)

// 2. A rota da API que será consultada
interface ViaCepService {
    @GET("{cep}/json/")
    suspend fun buscarCep(@Path("cep") cep: String): ViaCepResponse
}

// 3. O construtor do Retrofit
object RetrofitClient {
    private const val BASE_URL = "https://viacep.com.br/ws/"

    val viaCepService: ViaCepService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ViaCepService::class.java)
    }
}