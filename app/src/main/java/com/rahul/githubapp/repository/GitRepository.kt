package com.rahul.githubapp.repository

import com.rahul.githubapp.features.model.ResponseDataItem
import com.rahul.githubapp.network.ApiClient
import com.rahul.githubapp.network.Network
import retrofit2.Response

class GitRepository {
    private val apiClient = Network.getNetwork.create(ApiClient::class.java)

    suspend fun getList(): Response<ArrayList<ResponseDataItem>> {
        return apiClient.getPosts()
    }
}