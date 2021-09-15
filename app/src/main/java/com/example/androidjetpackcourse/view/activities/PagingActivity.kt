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
import kotlinx.coroutines.flow.collectLatest

class PagingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPagingBinding
    lateinit var recyclerViewAdapter: GitRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_paging)

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
        val viewModel  = ViewModelProvider(this).get(GitRepoViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
}