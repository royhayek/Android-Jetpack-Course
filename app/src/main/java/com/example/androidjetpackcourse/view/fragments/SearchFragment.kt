package com.example.androidjetpackcourse.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidjetpackcourse.adapters.WeatherLocationAdapter
import com.example.androidjetpackcourse.data.model.weather.Location
import com.example.androidjetpackcourse.databinding.FragmentSearchBinding
import com.example.androidjetpackcourse.handlers.Status
import com.example.androidjetpackcourse.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    lateinit var adapter: WeatherLocationAdapter
    private val weatherViewModel by viewModel<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeData()
    }

    private fun setupUI() {
        rv_locations.layoutManager = LinearLayoutManager(requireActivity())
        adapter = WeatherLocationAdapter()
        rv_locations.adapter = adapter

        et_location.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().isNotEmpty())
                weatherViewModel.getWeatherLocations("d6ebf978ddcf44d19bf131158212309", s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }


    private fun observeData() {
        weatherViewModel.locationsList.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        rv_locations.visibility = View.VISIBLE
                        pb_loading.visibility = View.GONE
                        resource.data?.let { locations -> retrieveList(locations) }
                    }
                    Status.ERROR -> {
                        rv_locations.visibility = View.VISIBLE
                        pb_loading.visibility = View.GONE
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        pb_loading.visibility = View.VISIBLE
                        rv_locations.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(locations: List<Location>) {
        adapter.apply {
            setLocations(locations)
        }
    }

}
