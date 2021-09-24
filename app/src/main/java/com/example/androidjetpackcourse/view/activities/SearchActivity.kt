package com.example.androidjetpackcourse.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.view.fragments.SearchFragment

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportFragmentManager.beginTransaction().
        add(R.id.fragmentContainer, SearchFragment(),"SearchFragment").commit()
    }
}
