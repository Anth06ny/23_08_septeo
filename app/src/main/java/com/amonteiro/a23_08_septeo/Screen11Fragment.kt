package com.amonteiro.a23_08_septeo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.amonteiro.a23_08_septeo.databinding.FragmentScreen11Binding


class Screen11Fragment : Fragment(), MenuProvider {

    private var _binding: FragmentScreen11Binding? = null
    private val binding get() = _binding!!

    var count = 0

    //val model by lazy { ViewModelProvider(this)[11ViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        count++
        println("onCreateView.count=$count")

            _binding = FragmentScreen11Binding.inflate(inflater, container, false)
        binding.goto12.setOnClickListener {
            findNavController().navigate(R.id.action_screen11Fragment_to_screen12Fragment)
        }

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.STARTED )

        return binding.root
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
        menu.add(1,2, 1, "Goto 1.2 (F1.1)" )
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == 2) {
            findNavController().navigate(R.id.action_screen11Fragment_to_screen12Fragment)
            return true
        }
        return false
    }
}