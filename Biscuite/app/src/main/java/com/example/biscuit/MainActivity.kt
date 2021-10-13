package com.example.biscuit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.biscuit.activities.RegisterActivity
import com.example.biscuit.ui.fragments.ChatsFragment
import com.example.biscuit.ui.objects.AppDrawer
import com.example.biscuit.utilits.replaceActivity
import com.example.biscuit.utilits.replaceFragment
import com.example.biscuite.R
import com.example.biscuite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar
    private lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunctions()
    }

    // для выполнения всех функциональеостей активити
    private fun initFunctions() {
        // проверка на авторизацию
        if(false){
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment())
        }else{
            // если не авторизован, открываем RegisterActivity
            replaceActivity(RegisterActivity())
        }
    }

    // для инициализации всех обьектов
    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
    }
}