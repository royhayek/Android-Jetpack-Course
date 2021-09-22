package com.example.androidjetpackcourse.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("EXCEPTION", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result
            Log.d("TOKEN", token!!)
        })

        binding.btDataBindingActivity.setOnClickListener { navigateToActivity(DataBindingActivity()) }
        binding.btLifecycleAwareActivity.setOnClickListener { navigateToActivity(LifeCycleAwareActivity()) }
        binding.btViewModelActivity.setOnClickListener { navigateToActivity(ViewModelActivity()) }
        binding.btNavigationActivity.setOnClickListener { navigateToActivity(NavigationActivity()) }
        binding.btPagingActivity.setOnClickListener { navigateToActivity(PagingActivity()) }
        binding.btRoomActivity.setOnClickListener { navigateToActivity(RoomActivity()) }
        binding.btWorkManagerActivity.setOnClickListener { navigateToActivity(WorkManagerActivity()) }
    }

    private fun navigateToActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}
