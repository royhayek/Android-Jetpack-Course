package com.example.androidjetpackcourse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.androidjetpackcourse.data.model.GitRepo
import com.example.androidjetpackcourse.data.network.GitRepoRepository

class GitRepoViewModel(private val repository: GitRepoRepository) : ViewModel() {

    // Fetching repos from the PagingSource
    fun getListData(): LiveData<PagingData<GitRepo>> {
        return repository.getListData().cachedIn(viewModelScope)
    }
}