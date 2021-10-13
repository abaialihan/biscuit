package com.example.biscuit.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.biscuit.MainActivity
import com.example.biscuit.activities.RegisterActivity
import com.example.biscuit.utilits.AUTH
import com.example.biscuit.utilits.replaceActivity
import com.example.biscuite.R


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // когда выбрана раздел выйти в optionsMenu, выходим из аккаунта
        when(item.itemId){
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                // после выхода из аккаунта, запускаем RegisterActivity()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
        }
        return true
    }
}