package com.example.androidjetpackcourse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.androidjetpackcourse.data.model.GitRepo
import com.example.androidjetpackcourse.data.model.GitRepoResponse
import com.example.androidjetpackcourse.data.network.GitRepoPagingSource
import com.example.androidjetpackcourse.data.network.GitRepoService
import com.example.androidjetpackcourse.data.network.GitRepoServiceBuilder
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GitRepoViewModel() : ViewModel() {

    private lateinit var service: GitRepoService

    // Fetching repos from the PagingSource
    fun getListData(): LiveData<PagingData<GitRepo>> {
        // Here we created a reference to the GitRepoService to download the repos list
        service = GitRepoServiceBuilder.buildService(GitRepoService::class.java)

        // Here we are returning an instance of Pager class
        // which is used to fetch a stream of data from PagingSource
        // the Paging library supports several return types
        // Flow, LiveData, and RxJava(Flowable, Observable)
        // Here we used LiveData
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
            .cachedIn(viewModelScope)
    }
}