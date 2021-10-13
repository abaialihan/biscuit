package com.example.biscuit.ui.fragments

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.biscuit.utilits.AppTextWatcher
import com.example.biscuit.utilits.showToast
import com.example.biscuite.R
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {
    override fun onStart() {
        super.onStart()
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string: String = register_input_code.text.toString()
            if (string.length == 6)
                verificationCode()

        })
    }

    fun verificationCode() {
        showToast("ok")
    }
}