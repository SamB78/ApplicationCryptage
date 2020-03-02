package com.example.gestionnairerapportdechantier.personnel.gestionPersonnel


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.gestionnairerapportdechantier.Database.GestionnaireDatabase
import com.example.gestionnairerapportdechantier.R
import com.example.gestionnairerapportdechantier.databinding.FragmentGestionPersonnelBinding
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class GestionPersonnelFragment : Fragment() {

//    val viewModel: GestionPersonnelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentGestionPersonnelBinding.inflate(inflater, container, false)
        binding.executePendingBindings()


        Timber.i("idPersonnel fromBundle = ${GestionPersonnelFragmentArgs.fromBundle(arguments!!).idPersonnel}")

        //ViewModelFactory
        val application = requireNotNull(this.activity).application
        val dataSource = GestionnaireDatabase.getInstance(application).PersonnelDao
        val viewModelFactory = GestionPersonnelViewModelFactory(
            dataSource, GestionPersonnelFragmentArgs.fromBundle(arguments!!).idPersonnel
        )

        //ViewModel
        val viewModel: GestionPersonnelViewModel by viewModels() { viewModelFactory }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.navigation.observe(viewLifecycleOwner, Observer { navigation ->
            when (navigation) {
                GestionPersonnelViewModel.gestionNavigation.ENREGISTREMENT_PERSONNEL -> {
                    Toast.makeText(activity, "Nouvelle entrée dans la BDD", Toast.LENGTH_SHORT)
                    viewModel.onBoutonClicked()
                    findNavController().navigate(R.id.action_gestionPersonnelFragment_to_listePersonnelFragment)
                }
                GestionPersonnelViewModel.gestionNavigation.ANNULATION -> {
                    Toast.makeText(activity, "Saisie annulée", Toast.LENGTH_SHORT)
                    findNavController().navigate(R.id.action_gestionPersonnelFragment_to_listePersonnelFragment)
                    viewModel.onBoutonClicked()
                }

            }
        })


        // Inflate the layout for this fragment
        return binding.root
    }

}
