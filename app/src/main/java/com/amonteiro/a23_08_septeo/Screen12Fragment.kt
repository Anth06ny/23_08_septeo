package com.amonteiro.a23_08_septeo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amonteiro.a23_08_septeo.databinding.FragmentScreen12Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Screen12Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Screen12Fragment : Fragment(), MenuProvider {

    private var _binding: FragmentScreen12Binding? = null
    private val binding get() = _binding!!

    val model by lazy { ViewModelProvider(requireActivity())[ExoNavGraphViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScreen12Binding.inflate(inflater, container, false)


        //Menu
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        model.texte.observe(viewLifecycleOwner) {
            binding.editTextText2.setText(it)
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        model.changeTexte(binding.editTextText2.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("Screen12Fragment.onDestroyView")
        // _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Screen12Fragment.onDestroy")
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.add(0,3, 3, "Up (F1.2)" )
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == 3) {
            findNavController().navigateUp()
            return true
        }
        return false
    }

}