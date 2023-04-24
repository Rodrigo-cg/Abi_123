package com.mda.ateinspeccion.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface apiService {

    @GET
    suspend fun getDataCert(@Url url:String) : Response<dataCert>
    @GET
    suspend fun getValidUser(@Url url:String) : Response<dataUser>
    @GET
    suspend fun getAllcertrelacionados(@Url url:String) : Response<responseCert>
    @POST("certificados_apps/conexiones_php/historiallogin.php")
    fun addHistorialSesion(@Body cert:userInfo) : Call<postRes>
}

