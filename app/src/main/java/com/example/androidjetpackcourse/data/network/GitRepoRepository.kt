package com.example.androidjetpackcourse.data.network

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.androidjetpackcourse.BuildConfig
import com.example.androidjetpackcourse.data.model.GitRepo
import com.example.androidjetpackcourse.di.BaseUrlInterceptor

class GitRepoRepository(
    private val service: GitRepoApi,
    private val interceptor: BaseUrlInterceptor
) {

    fun getListData(): LiveData<PagingData<GitRepo>> {
        interceptor.setHost(BuildConfig.API_URL)
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
