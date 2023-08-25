package com.amonteiro.a23_08_septeo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amonteiro.a23_08_septeo.WeatherAPI.count
import com.amonteiro.a23_08_septeo.databinding.FragmentScreen11Binding


class Screen11Fragment : Fragment(), MenuProvider {

    private var _binding: FragmentScreen11Binding? = null
    private val binding get() = _binding!!

    //Comme il y a requiereActivity on prend le viewModel hébérgé par l'activity
    //Si on avait mis this, on aura pris celui hébergé par le fragment et donc visible que par lui
    val model by lazy { ViewModelProvider(requireActivity())[ExoNavGraphViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScreen11Binding.inflate(inflater, container, false)
        binding.goto12.setOnClickListener {
            findNavController().navigate(R.id.action_screen11Fragment_to_screen12Fragment)
        }

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.STARTED)

        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Nouveau Titre"


        model.texte.observe(viewLifecycleOwner) {
            binding.editTextText.setText(it)
        }

        return binding.root
    }

    //Quand l'écran perd le focus je mets à jour la variable du viewModel
    override fun onPause() {
        super.onPause()
        model.changeTexte(binding.editTextText.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("Screen11Fragment.onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Screen11Fragment.onDestroy")
    }

    /* -------------------------------- */
    // Callback Menu
    /* -------------------------------- */
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.add(1, 2, 1, "Goto 1.2 (F1.1)").setIcon(R.drawable.baseline_delete_24).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.add(1, 5, 2, "ChangeVisibility")
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == 2) {
            findNavController().navigate(R.id.action_screen11Fragment_to_screen12Fragment)
            return true
        }
        else if (menuItem.itemId == 5) {
            model.changeVisibility()
            return true
        }
        return false
    }
}