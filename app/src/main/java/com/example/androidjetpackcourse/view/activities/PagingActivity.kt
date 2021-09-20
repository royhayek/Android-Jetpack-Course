package com.example.androidjetpackcourse.view.activities

import GitRepoAdapter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidjetpackcourse.databinding.ActivityPagingBinding
import com.example.androidjetpackcourse.viewmodel.GitRepoViewModel
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PagingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagingBinding
    lateinit var recyclerViewAdapter: GitRepoAdapter
    private val gitRepoViewModel by viewModel<GitRepoViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        rv_repositories.apply {
            layoutManager = LinearLayoutManager(this@PagingActivity)
            recyclerViewAdapter = GitRepoAdapter()
            adapter = recyclerViewAdapter
        }

        recyclerViewAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {
                pb_loading.visibility = View.VISIBLE
            } else {
                pb_loading.visibility = View.GONE

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                error?.let {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            gitRepoViewModel.getAllGitRepos().observe(this@PagingActivity, {
                recyclerViewAdapter.submitData(lifecycle, it)
            })
        }
    }
}