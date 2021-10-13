package com.example.biscuit.ui.fragments

import androidx.fragment.app.Fragment
import com.example.biscuit.utilits.replaceFragment
import com.example.biscuit.utilits.showToast
import com.example.biscuite.R
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*

class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {
    override fun onStart() {
        super.onStart()
        register_btn_next.setOnClickListener{
            sendCodeForRegistration()
        }
    }

    private fun sendCodeForRegistration() {
        // проверка пустого поле EditText
        if (register_input_phone_number.text.toString().isEmpty())
            showToast(R.string.register_toast_enter_phone.toString())
        else {
            replaceFragment(EnterCodeFragment())
        }
    }
}