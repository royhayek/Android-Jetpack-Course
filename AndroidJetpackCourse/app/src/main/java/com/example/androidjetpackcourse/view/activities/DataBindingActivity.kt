package com.example.androidjetpackcourse.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.androidjetpackcourse.data.model.Contact
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.databinding.ActivityDataBindingBinding
import com.example.androidjetpackcourse.handlers.EventHandler

class DataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)

//        data = MainActivityDataGenerator()
//        val myRandomNumber: String = data.getNumber()
//        binding.number = myRandomNumber;

        binding.contact = Contact("Roy Hayek", "royhayek27@gmail.com")

        binding.handler = EventHandler(this)

        binding.imageUrl = "https://i.redd.it/lhw4vp5yoy121.jpg"
    }
}