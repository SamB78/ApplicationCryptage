package com.example.applicationcrpytage.methodeCryptageB2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationcrpytage.R
import com.example.applicationcrpytage.databinding.FragmentMainMenuBinding
import com.example.applicationcrpytage.databinding.FragmentMethodeCryptageB2Binding
import com.example.applicationcrpytage.mainMenu.MainMenuViewModel

/**
 * A simple [Fragment] subclass.
 */
class MethodeCryptageB2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMethodeCryptageB2Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_methode_cryptage_b2, container, false)
        binding.executePendingBindings()
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(MethodeCryptageB2ViewModel::class.java)
        binding.viewModel = viewModel

        val navController = findNavController()


        // Inflate the layout for this fragment
        return binding.root
    }


}
