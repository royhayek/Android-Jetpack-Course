package com.example.androidjetpackcourse.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.viewmodel.ViewModelActivityViewModel
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityViewModelBinding
    private lateinit var data: ViewModelActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model)

        val model = ViewModelProvider(this).get(ViewModelActivityViewModel::class.java)
        val myRandomNumber = model.getNumber()

        myRandomNumber.observe(this, Observer<String> { number ->
            tvNumber.text = number
            Log.i(TAG, "Random Number Set")
        })

        bRandom.setOnClickListener {
            model.createNumber()
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}