package com.auzusto.repopopular.data

import android.util.Log
import com.auzusto.repopopular.domain.GitHubRepository
import com.auzusto.repopopular.model.PullsUrl
import com.auzusto.repopopular.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubNetWorkRepositoryImpl(
    private val apiService: ApiService
) : GitHubRepository {
    override fun getRepositories() {
        TODO("Not yet implemented")
    }

    override fun getRepositoryPullRequests(userName: String, repository: String, onResponse: (List<PullsUrl>?) ->Unit) {
        apiService.getPulls(userName, repository).enqueue(object: Callback<List<PullsUrl>> {
            override fun onFailure(call: Call<List<PullsUrl>>, t: Throwable) {
                onResponse.invoke(null)
            }

            override fun onResponse(
                call: Call<List<PullsUrl>>,
                response: Response<List<PullsUrl>>
            ) {
                response.body()?.let {
                    onResponse.invoke(it)
                }
            }

        })
    }

}