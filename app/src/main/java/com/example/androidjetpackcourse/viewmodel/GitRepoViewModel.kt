package com.example.androidjetpackcourse.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidjetpackcourse.data.model.GitRepo
import com.example.androidjetpackcourse.data.network.GitRepoRepository

class GitRepoViewModel(private val repository: GitRepoRepository) : ViewModel() {

    private val _gitReposList = MutableLiveData<PagingData<GitRepo>>()
    val gitReposList: LiveData<PagingData<GitRepo>>
        get() = _gitReposList


    fun getAllGitRepos() : LiveData<PagingData<GitRepo>> {
       val response = repository.getListData().cachedIn(viewModelScope)
        _gitReposList.postValue(response.value)
        return response;
    }


}