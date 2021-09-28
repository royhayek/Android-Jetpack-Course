package com.example.androidjetpackcourse.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.androidjetpackcourse.data.model.weather.WeatherDetail
import androidx.databinding.DataBindingUtil
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.databinding.GridviewDetailItemBinding

class WeatherDetailsAdapter(private val detailsList: ArrayList<WeatherDetail>) : BaseAdapter() {
    private lateinit var binding: GridviewDetailItemBinding

    override fun getCount(): Int {
        return detailsList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.context).inflate(R.layout.gridview_detail_item, null)
            binding = DataBindingUtil.bind(convertView)!!
            convertView.tag = binding
        } else {
            binding = convertView.tag as GridviewDetailItemBinding
        }

        val detail = detailsList[position]
        binding.ivDetailIcon.setImageResource(detail.icon)
        binding.tvDetailTitle.text = detail.title
        binding.tvDetailValue.text = detail.value

        return binding.root
    }
}
