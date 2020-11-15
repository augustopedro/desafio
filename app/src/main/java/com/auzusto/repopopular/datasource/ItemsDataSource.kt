package com.auzusto.repopopular.datasource

import androidx.paging.PageKeyedDataSource
import com.auzusto.repopopular.model.BaseResponse
import com.auzusto.repopopular.model.Items
import com.auzusto.repopopular.service.ApiService
import com.auzusto.repopopular.service.ApiServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemsDataSource: PageKeyedDataSource<Int, Items>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Items>
    ) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getRepositories(page = FIRST_PAGE)


        call.enqueue(object: Callback<BaseResponse<Items>>{
            override fun onFailure(call: Call<BaseResponse<Items>>, t: Throwable) {

            }

            override fun onResponse(call: Call<BaseResponse<Items>>, response: Response<BaseResponse<Items>>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.items

                    responseItems?.let {
                        callback.onResult(it, null, FIRST_PAGE + 1)
                    }
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Items>) {

        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getRepositories(page = params.key)

        call.enqueue(object: Callback<BaseResponse<Items>>{
            override fun onFailure(call: Call<BaseResponse<Items>>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse<Items>>, response: Response<BaseResponse<Items>>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.items
                    val key = params.key + 1

                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Items>) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getRepositories(page = params.key)

        call.enqueue(object: Callback<BaseResponse<Items>>{
            override fun onFailure(call: Call<BaseResponse<Items>>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse<Items>>, response: Response<BaseResponse<Items>>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.items

                    val key = if (params.key > 1) params.key -1 else 0

                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

        })
    }

    companion object {
        const val FIRST_PAGE = 1
        const val TOTAL_PAGES = 10
    }

}