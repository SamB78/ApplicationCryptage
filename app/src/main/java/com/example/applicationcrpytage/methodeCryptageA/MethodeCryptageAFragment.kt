package com.example.applicationcrpytage.methodeCryptageA


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationcrpytage.R
import com.example.applicationcrpytage.databinding.FragmentMethodeCryptageABinding
import com.example.applicationcrpytage.databinding.FragmentMethodeCryptageB2Binding
import com.example.applicationcrpytage.methodeCryptageB2.MethodeCryptageB2ViewModel

/**
 * A simple [Fragment] subclass.
 */
class MethodeCryptageAFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMethodeCryptageABinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_methode_cryptage_a, container, false)
        binding.executePendingBindings()
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(MethodeCryptageAViewModel::class.java)
        binding.viewModel = viewModel

        val navController = findNavController()


        // Inflate the layout for this fragment
        return binding.root
    }


}
