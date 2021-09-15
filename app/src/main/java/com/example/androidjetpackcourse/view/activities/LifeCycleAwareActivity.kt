package com.example.androidjetpackcourse.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.observers.LifeCycleAwareActivityObserver

class LifeCycleAwareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_aware)

        Log.i(TAG, "Owner onCreate")

        lifecycle.addObserver(LifeCycleAwareActivityObserver())
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "Owner onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "Owner onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Owner onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Owner onDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "Owner onStop")
    }


    companion object {
        private val TAG: String = LifeCycleAwareActivity::class.java.simpleName
    }
}