package com.example.androidjetpackcourse.data.network

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.androidjetpackcourse.data.model.GitRepo

class GitRepoRepository(private val service: GitRepoApi) {

    fun getListData(): LiveData<PagingData<GitRepo>> {
        return Pager(
            // Configuring how data is loaded by adding additional properties to PagingConfig
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                // Here we are calling the load function of the paging source which is returning a LoadResult
                GitRepoPagingSource(service)
            }
        ).liveData
    }
}
