package com.example.biscuit.ui.fragments

import androidx.fragment.app.Fragment
import com.example.biscuit.MainActivity
import com.example.biscuit.activities.RegisterActivity
import com.example.biscuit.utilits.*
import com.example.biscuite.R
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {

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

        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            //проверка прошла ли верификация
            if (task.isSuccessful) {
                // если прошла,
                // получаем id c FirebaseAuth текущего пользователя
                val uid = AUTH.currentUser?.uid.toString()

                // создаем Map что бы каждый CHILD не передавать по одной.
                // А сразу одним большим файлом
                val dataMap = mutableMapOf<String, Any>()

                // задаем значение ключу CHILD_ID, id текущего c FirebaseAuth.
                // итд со след полями
                dataMap[CHILD_ID] = uid
                dataMap[CHILD_PHONE] = phoneNumber //c конструктора
                dataMap[CHILD_USERNAME] = uid // по умолчанию username будет присвоен id, но потом может его переименовать

                // теперь все это записываем в БД
                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dataMap)
                    .addOnCompleteListener { task2 -> // когда все выполнилосль
                        // делаем проверку
                        if (task2.isSuccessful) {
                            //если успешно то приветствуем и запускаем MainActivity
                            showToast("Добро пожаловать")
                            (activity as RegisterActivity).replaceActivity(MainActivity())
                        } else showToast(task2.exception?.message.toString())
                    }
            } else showToast(task.exception?.message.toString())
        }
    }
}