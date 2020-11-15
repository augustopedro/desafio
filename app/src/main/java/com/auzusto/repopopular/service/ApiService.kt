package com.auzusto.repopopular.service

import com.auzusto.repopopular.model.BaseResponse
import com.auzusto.repopopular.model.Items
import com.auzusto.repopopular.model.Owner
import com.auzusto.repopopular.model.PullsUrl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    fun getRepositories(
        @Query("q") q: String = "language:Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int
    ): Call<BaseResponse<Items>>

    @GET("repos/{login}/{name}/{repos}")
    fun getPulls(
        @Path("login") userName: String,
        @Path("name") repository: String,
        @Path("repos") method: String = "pulls"
    ): Call<List<PullsUrl>>
}