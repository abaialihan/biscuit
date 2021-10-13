package com.example.biscuit.ui.fragments

import androidx.fragment.app.Fragment
import com.example.biscuit.MainActivity
import com.example.biscuit.activities.RegisterActivity
import com.example.biscuit.utilits.AUTH
import com.example.biscuit.utilits.AppTextWatcher
import com.example.biscuit.utilits.replaceActivity
import com.example.biscuit.utilits.showToast
import com.example.biscuite.R
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val phoneNumber: String, val id: String) : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        //задаем в тайтл активити номер телефона
        (activity as RegisterActivity).title = phoneNumber

        // обработка введенного смс кода
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string: String = register_input_code.text.toString()
            if (string.length == 6)
                enterCode()
        })
    }

    // после введения смс кода, создаем нового user
    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener{
            //проверка пршла ли верификация
            if (it.isSuccessful) {
                //если прошла то приветствуем и запускаем MainActivity
                showToast("Добро пожаловать")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else showToast(it.exception?.message.toString())
        }
    }
}