package com.auzusto.repopopular.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


data class BaseResponse<T>(
    @SerializedName("total_count")
    @Expose
    val totalCount: Int? = null,
    @SerializedName("incomplete_results")
    @Expose
    val incompleteResults: Boolean? = null,
    @SerializedName("items")
    @Expose
    val items: List<T>? = null
)

data class Items(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    @SerializedName("stargazers_count")
    val stargazersCount: Int? = null,
    @SerializedName("forks_count")
    val forksCount: Int? = null,
    val owner: Owner
)

data class Owner(
    val login: String? = null,
    val id: Int? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null
)

data class PullsUrl(
    val url: String? = null,
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null,
    val user: User
)

data class User(
    val id: Int? = null,
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null
)





