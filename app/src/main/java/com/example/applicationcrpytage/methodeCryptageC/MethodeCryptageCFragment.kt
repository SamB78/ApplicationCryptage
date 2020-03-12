package com.example.applicationcrpytage.methodeCryptageC


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationcrpytage.R
import com.example.applicationcrpytage.databinding.FragmentMethodeCryptageCBinding

/**
 * A simple [Fragment] subclass.
 */
class MethodeCryptageCFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMethodeCryptageCBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_methode_cryptage_c, container, false)
        binding.executePendingBindings()
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(MethodeCryptageCViewModel::class.java)
        binding.viewModel = viewModel

        val navController = findNavController()


        // Inflate the layout for this fragment
        return binding.root
    }


}
