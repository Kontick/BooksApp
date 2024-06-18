package com.kontvip.common.data.cloud

import com.kontvip.common.data.cloud.model.CloudBook
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface BooksApi {

    @Headers("Content-Type: application/json")
    @GET("/")
    suspend fun fetchBooks(): Response<List<CloudBook>>

}