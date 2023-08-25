package com.amonteiro.a23_08_septeo

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.amonteiro.a23_08_septeo.databinding.ActivityExoNavGraphBinding

class ExoNavGraphActivity : AppCompatActivity(), MenuProvider {

    val binding by lazy { ActivityExoNavGraphBinding.inflate(layoutInflater) }

    //viewModel hébergé par l'activity donc commun à tous les fragments
    val model by lazy { ViewModelProvider(this)[ExoNavGraphViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        addMenuProvider(this)

        //J'observe le view model qui contient l'info si on doit changer la visibilité ou non
        model.bottomVisibility.observe(this) {
            binding.bottomView.isVisible = it
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val navController = binding.navHostFragment.findNavController()
        binding.bottomView.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.screen11Fragment, R.id.screen21Fragment))

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    //On redirige l'événement back ou flèche au NavController
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }

    /* -------------------------------- */
    // Menu
    /* -------------------------------- */
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_progress, menu)

        menu.add(1, 1, 2, "Finish (A)").setIcon(R.drawable.baseline_flag_24).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == 1) {
            finish()
            return true
        }
        return false
    }

    //Créer le menu
}

class ExoNavGraphViewModel : ViewModel() {

    val bottomVisibility = MutableLiveData(true)
    val texte = MutableLiveData("")

    fun changeVisibility() {
        bottomVisibility.postValue(!bottomVisibility.value!!)
    }

    fun changeTexte(newTexte : String) {
        texte.postValue(newTexte)
    }

}