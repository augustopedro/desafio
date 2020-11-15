package com.auzusto.repopopular

import android.app.Application
import com.auzusto.repopopular.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GitHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GitHubApplication)
            modules(appModules)

        }
    }
}