package com.auzusto.repopopular.di

import com.auzusto.repopopular.data.GitHubNetWorkRepositoryImpl
import com.auzusto.repopopular.domain.GitHubRepository
import com.auzusto.repopopular.service.ApiService
import com.auzusto.repopopular.service.ApiServiceBuilder
import com.auzusto.repopopular.viewmodel.PullListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { ApiServiceBuilder.buildService(ApiService::class.java) }
    single{ GitHubNetWorkRepositoryImpl(get()) as GitHubRepository}


    viewModel { PullListViewModel(get()) }
}