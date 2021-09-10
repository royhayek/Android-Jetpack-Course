package com.example.navigation.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.navigation.R
import com.example.navigation.databinding.FragmentAboutBinding
import com.example.navigation.ui.cart.CartViewModel

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        arguments?.let { 
            val safeArgs = AboutFragmentArgs.fromBundle(it)
            binding.tvProductCount.text = "Total Products Available: ${safeArgs.productCount}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}