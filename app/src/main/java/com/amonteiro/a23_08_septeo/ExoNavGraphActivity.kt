package com.amonteiro.a23_08_septeo

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class ExoNavGraphActivity : AppCompatActivity(), MenuProvider {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_nav_graph)

        addMenuProvider(this)


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
    }

    //On redirige l'événement back ou flèche au NavController
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }

    /* -------------------------------- */
    // Menu
    /* -------------------------------- */
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.add(1, 1, 2, "Finish (A)")
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
       if(menuItem.itemId == 1) {
           finish()
           return true
       }
        return false
    }


    //Créer le menu

}