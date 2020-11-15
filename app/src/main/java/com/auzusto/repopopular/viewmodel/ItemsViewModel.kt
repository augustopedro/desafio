package com.auzusto.repopopular.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.auzusto.repopopular.datasource.ItemsDataSource
import com.auzusto.repopopular.datasource.ItemsDataSourceFactory
import com.auzusto.repopopular.model.Items

class ItemsViewModel: ViewModel() {

    val itemsPagedList: LiveData<PagedList<Items>>

    private val liveDataSource: LiveData<ItemsDataSource>

    init {
        val itemDataSourceFactory = ItemsDataSourceFactory()

        liveDataSource = itemDataSourceFactory.itemsLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ItemsDataSource.TOTAL_PAGES)
            .build()

        itemsPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()


    }
}