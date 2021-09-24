package com.example.androidjetpackcourse.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidjetpackcourse.adapters.WeatherLocationAdapter
import com.example.androidjetpackcourse.databinding.FragmentSearchBinding
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment() : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: WeatherLocationAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        rv_locations.layoutManager = LinearLayoutManager(requireActivity())
        adapter = WeatherLocationAdapter()
        rv_locations.adapter = adapter

        et_location.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

}
