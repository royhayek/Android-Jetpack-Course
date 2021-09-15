package com.example.androidjetpackcourse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidjetpackcourse.data.model.GitRepo
import com.example.androidjetpackcourse.data.network.GitRepoDataSource
import com.example.androidjetpackcourse.data.network.GitRepoService
import com.example.androidjetpackcourse.data.network.GitRepoServiceBuilder
import kotlinx.coroutines.flow.Flow

class GitRepoViewModel : ViewModel() {

    private lateinit var service: GitRepoService

    fun getListData(): Flow<PagingData<GitRepo>> {
        service = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { GitRepoDataSource(service) }).flow.cachedIn(viewModelScope)
    }
}