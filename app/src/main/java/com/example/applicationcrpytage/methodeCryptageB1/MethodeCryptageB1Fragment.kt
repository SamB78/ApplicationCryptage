package com.example.applicationcrpytage.methodeCryptageB1


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationcrpytage.R
import com.example.applicationcrpytage.databinding.FragmentMethodeCryptageB1Binding


/**
 * A simple [Fragment] subclass.
 */
class MethodeCryptageB1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMethodeCryptageB1Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_methode_cryptage_b1, container, false)
        binding.executePendingBindings()
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(MethodeCryptageB1ViewModel::class.java)
        binding.viewModel = viewModel

        val navController = findNavController()


        // Inflate the layout for this fragment
        return binding.root
    }


}
