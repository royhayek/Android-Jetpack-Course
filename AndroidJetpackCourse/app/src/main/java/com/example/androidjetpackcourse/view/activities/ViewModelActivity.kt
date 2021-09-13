package com.example.androidjetpackcourse.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.databinding.ActivityViewModelBinding
import com.example.androidjetpackcourse.viewmodel.ViewModelActivityViewModel

class ViewModelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewModelBinding
    private lateinit var data: ViewModelActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model)

        val model = ViewModelProviders.of(this).get(ViewModelActivityViewModel::class.java)
        val myRandomNumber = model.getNumber()

        myRandomNumber.observe(this, Observer<String> { number ->
            binding.tvNumber.text = number
            Log.i(TAG, "Random Number Set")
        })

        binding.bRandom.setOnClickListener {
            model.createNumber()
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}