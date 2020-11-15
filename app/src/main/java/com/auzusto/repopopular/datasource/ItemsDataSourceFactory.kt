package com.auzusto.repopopular.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.auzusto.repopopular.model.Items

class ItemsDataSourceFactory: DataSource.Factory<Int, Items>() {

    val itemsLiveDataSource = MutableLiveData<ItemsDataSource>()

    override fun create(): DataSource<Int, Items> {
        val itemsDataSource = ItemsDataSource()
        itemsLiveDataSource.postValue(itemsDataSource)

        return itemsDataSource
    }

}