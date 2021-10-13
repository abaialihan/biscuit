  package com.example.biscuit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.biscuit.ui.fragments.EnterPhoneNumberFragment
import com.example.biscuit.utilits.replaceFragment
import com.example.biscuite.R

import com.example.biscuite.databinding.ActivityRegisterBinding

  class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

      override fun onStart() {
          super.onStart()
          mToolbar = mBinding.registerToolbar
          setSupportActionBar(mToolbar)
          title = getString(R.string.register_title_your_phone)
          replaceFragment(EnterPhoneNumberFragment())
      }
  }