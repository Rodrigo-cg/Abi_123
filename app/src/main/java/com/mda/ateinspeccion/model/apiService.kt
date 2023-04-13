package com.mda.ateinspeccion.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiService {

    @GET
    suspend fun getDataCert(@Url url:String) : Response<dataCert>
    @GET
    suspend fun getValidUser(@Url url:String) : Response<dataUser>
    @GET
    suspend fun getAllcertrelacionados(@Url url:String) : Response<responseCert>

}

