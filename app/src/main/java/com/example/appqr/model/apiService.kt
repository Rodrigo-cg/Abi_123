package com.example.appqr.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiService {

    @GET
    suspend fun getQuotes(@Url url: String) : Response<data>
}

