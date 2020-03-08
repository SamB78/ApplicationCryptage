package com.example.applicationcrpytage.mainMenu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationcrpytage.R
import com.example.applicationcrpytage.databinding.FragmentMainMenuBinding

/**
 * A simple [Fragment] subclass.
 */
class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMainMenuBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_menu, container, false)
        binding.executePendingBindings()
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(MainMenuViewModel::class.java)
        binding.viewModel = viewModel

        val navController = findNavController()

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            when (it) {
                MainMenuViewModel.navigationMainMenu.PASSAGE_PAGE_A -> {
                    Toast.makeText(context, "A", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_mainMenuFragment_to_methodeCryptageAFragment)
                    viewModel.onBoutonClicked()
                }
                MainMenuViewModel.navigationMainMenu.PASSAGE_PAGE_B1 -> {
                    Toast.makeText(context, "B1", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_mainMenuFragment_to_methodeCryptageB1Fragment)
                    viewModel.onBoutonClicked()
                }
                MainMenuViewModel.navigationMainMenu.PASSAGE_PAGE_B2 -> {
                    Toast.makeText(context, "B2", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_mainMenuFragment_to_methodeCryptageB2Fragment)
                    viewModel.onBoutonClicked()
                }
            }

        })

        return binding.root
    }


}
