package com.rahul.githubapp.network

import com.rahul.githubapp.features.model.ResponseDataItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("posts")
    suspend fun getPosts(): Response<ArrayList<ResponseDataItem>>
}