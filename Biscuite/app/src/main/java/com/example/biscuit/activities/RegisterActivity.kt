  package com.example.biscuit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biscuite.databinding.ActivityRegisterBinding

  class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

}