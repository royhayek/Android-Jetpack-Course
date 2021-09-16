package com.example.androidjetpackcourse.view.activities

import GitRepoAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.databinding.ActivityPagingBinding
import com.example.androidjetpackcourse.viewmodel.GitRepoViewModel
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagingBinding
    lateinit var recyclerViewAdapter: GitRepoAdapter

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

    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(GitRepoViewModel::class.java)

        // here we are sticking this coroutine to the lifecycle of this activity
        // if the activity is destroyed  everything launched inside this scope will be destroyed
        // here we didn't use the GlobalScope to prevent memory leaks because when this activity
        // will be destroyed the resources will stay in the GlobalScope
        lifecycleScope.launch {
            // Here we are observing the PageData stream
            // since the ViewModel returns a Flow, we can use collectLast to access the stream
            viewModel.getListData().collectLatest { pagingData ->
                // once we have the results we send the list to the adapter by calling submitData
                recyclerViewAdapter.submitData(pagingData)
            }
        }
    }
}