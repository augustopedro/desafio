package com.auzusto.repopopular.domain

import com.auzusto.repopopular.model.PullsUrl

interface GitHubRepository {
    fun getRepositories()
    fun getRepositoryPullRequests(userName: String, repository: String,  onResponse: (List<PullsUrl>?) ->Unit)
}