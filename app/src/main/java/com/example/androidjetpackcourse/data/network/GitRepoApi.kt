package com.example.androidjetpackcourse.data.network

import com.example.androidjetpackcourse.data.model.GitRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoApi {
    @GET("search/repositories?sort=stars")
    suspend fun getRepositories(
        @Query("page") page: Int,
        @Query("per_page") size: Int,
        @Query("q") topic: String
    ): GitRepoResponse
}