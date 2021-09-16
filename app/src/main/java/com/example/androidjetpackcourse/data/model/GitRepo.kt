package com.example.androidjetpackcourse.data.model

data class GitRepo(
    val full_name: String,
    val description: String,
    val stargazers_count: Int,
    val fork_count: Int,
    val language: String,
)

data class GitRepoResponse(
    val items: List<GitRepo>? = null,
    val total_count: Int = 0
)