package com.example.androidjetpackcourse.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidjetpackcourse.data.model.weather.Location
import com.example.androidjetpackcourse.databinding.WeatherLocationItemBinding


class WeatherLocationAdapter() : RecyclerView.Adapter<WeatherLocationAdapter.LocationViewHolder>() {

    private var locations: List<Location> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = WeatherLocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locations[position]
        holder.apply {
            bind(location)
        }
    }

    override fun getItemCount(): Int {
        return  locations.size
    }

    fun setLocations(locations: List<Location>) {
        this.locations = locations
        notifyDataSetChanged()
    }

    class LocationViewHolder(val binding: WeatherLocationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loc: Location) {
            binding.apply {
                location = loc
            }
        }
    }
}
