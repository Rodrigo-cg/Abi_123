package com.mda.ateinspeccion.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface IapiService {


 //Api obtener todo los datos del certificado

    @GET
    suspend fun getDataCert(@Url url:String) : Response<dataCert>

 //Api para obtener la validacion del usuario
    @GET
    suspend fun getValidUser(@Url url:String) : Response<dataUser>

 //Api para obtener la lista de certificados asociados
    @GET
    suspend fun getAllLicIndt(@Url url:String) : Response<responseCert>

//Api para guardar el historial de los usuarios que ingresaron a la aplicacion
    @POST("certificados_apps/conexiones_php/historiallogin.php")
    fun addHistorialSesion(@Body cert:userInfo) : Call<postRes>


//Api Services de base de datos



    //Api services de MySQL para obtener todos los datos de certificados asociados a las licencias temporales
    @GET
    suspend fun getAllLicTemp(@Url url: String): Response<ResponseMysqlTemp>

    //Api services de MySQL para obtener todos los datos de certificados asociados a las licencias indeterminadas
    @GET
    suspend fun getAllLicInd(@Url url: String): Response<ResponseMysqlInd>

    //Api services que se conecta a google sheets para obtener todos los datos de certificados asociados a las licencias de riesgo y desastre de 2023
    @GET("exec")
    suspend fun getAllITCSE_ECSE_2023(
        @Query("spreadsheetId") spreadsheetId: String,
        @Query("sheet") sheet: String,
        @Query("expediente") expediente: String,
        @Query("razon_nombre") razonNombre: String
    ):Response<ResponseGoogleRiesgoD2023>


    //Api services que se conecta a MySQL para obtener todos los datos de certificados asociados a las licencias de riesgo y desastre de 2022 y anteriores

    @GET
    suspend fun getAllITCSE_ECSE_2022(@Url url: String): Response<ResponseMySQLRiesgoD2022>

    //Api services que se conecta a google sheets para obtener todos los datos de certificados asociados a las licencias de edificaciones
    @GET("exec")
    suspend fun getAllHabilitacion(
        @Query("spreadsheetId") spreadsheetId: String,
        @Query("sheet") sheet: String,
        @Query("expediente") expediente: String,
        @Query("razon_nombre") razonNombre: String
    ):Response<ResponseGoogleSGHUE>

    //Api services que se conecta a google sheets para obtener todos los datos de certificados asociados a las licencias de edificaciones
    @GET("exec")
    suspend fun getAllConstruccion(
        @Query("spreadsheetId") spreadsheetId: String,
        @Query("sheet") sheet: String,
        @Query("expediente") expediente: String,
        @Query("razon_nombre") razonNombre: String
    ):Response<ResponseGoogleSGHUE>


}

