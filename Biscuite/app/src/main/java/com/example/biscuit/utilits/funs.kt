package com.example.biscuit.utilits

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.biscuit.activities.RegisterActivity
import com.example.biscuit.ui.fragments.ChatsFragment
import com.example.biscuite.R

// для всех Fragment созадем функцию быстрого вызова Toast
fun Fragment.showToast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

// для переключения Activity
fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

// // для переключения Fragment в Activity
fun AppCompatActivity.replaceFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.data_container, fragment)
        .commit()
}

// // для переключения Fragment в Fragment
fun Fragment.replaceFragment(fragment: Fragment){
    parentFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.data_container, fragment)
        .commit()
}