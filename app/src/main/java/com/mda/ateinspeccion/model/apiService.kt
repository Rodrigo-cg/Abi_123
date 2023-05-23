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
    suspend fun getAllLicIndt(@Url url:String) : Response<responseCert>
    @POST("certificados_apps/conexiones_php/historiallogin.php")
    fun addHistorialSesion(@Body cert:userInfo) : Call<postRes>
//Api Services de base de datos

    //Api services de MySQL
    @GET
    suspend fun getAllLicTemp(@Url url: String): Response<ResponseMuniateSQL_ind>

    @GET
    suspend fun getAllDataInd(@Url url: String): Response<ResponseListeDatacertGoogleSGHUE>

    @GET
    suspend fun getAllITCSE_ECSE_2023(@Url url: String): Response<ResponseListeDatacertGoogleSGHUE>

    @GET
    suspend fun getAllITCSE_ECSE_2022(@Url url: String): Response<ResponseListeDatacertGoogleSGHUE>

    @GET
    suspend fun getAllHabilitacion(@Url url: String): Response<ResponseListeDatacertGoogleSGHUE>

    @GET
    suspend fun getAllConstruccion(@Url url: String): Response<ResponseMuniateSQL_ind>


}

