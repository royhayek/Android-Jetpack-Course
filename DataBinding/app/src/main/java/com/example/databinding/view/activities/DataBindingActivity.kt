package com.example.databinding.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databinding.model.Contact
import com.example.databinding.R
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.handler.EventHandler

class DataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        data = MainActivityDataGenerator()
//        val myRandomNumber: String = data.getNumber()
//        binding.number = myRandomNumber;

        binding.contact = Contact("Roy Hayek", "royhayek27@gmail.com")

        binding.handler = EventHandler(this)

        binding.imageUrl = "https://i.redd.it/lhw4vp5yoy121.jpg"
    }
}