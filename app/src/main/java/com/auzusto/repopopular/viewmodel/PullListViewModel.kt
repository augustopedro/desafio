package com.auzusto.repopopular.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.auzusto.repopopular.domain.GitHubRepository
import com.auzusto.repopopular.model.PullsUrl

class PullListViewModel(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {
    private val _pullRequestsObservable = MutableLiveData<List<PullsUrl>>()
    val pullRequestsObservable : LiveData<List<PullsUrl>> = _pullRequestsObservable


    fun getPulls(userName: String, repository: String) {
       gitHubRepository.getRepositoryPullRequests(userName, repository) {
           _pullRequestsObservable.value = it
        }
    }

}