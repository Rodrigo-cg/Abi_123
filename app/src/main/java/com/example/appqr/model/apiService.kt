package com.example.appqr.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiService {

    @GET("consultar.php")
    Call<List<data>>Response<dataCertificado>

    @GET
    suspend fun getMascotas(@Url url:String): Response<dataCertificado>


}