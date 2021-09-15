package com.example.androidjetpackcourse.view.fragments.shop
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.androidjetpackcourse.databinding.FragmentShopBinding
import java.util.*

class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btAboutUs.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.about_destination)

            val nextAction = ShopFragmentDirections.nextAction()
            nextAction.productCount = Random().nextInt(200)
            Navigation.findNavController(it).navigate(nextAction)

//            Navigation.findNavController(it).navigate(R.id.next_action)

        }
    }

}