package com.example.biscuit.ui.fragments

import androidx.fragment.app.Fragment
import com.example.biscuit.activities.MainActivity
import com.example.biscuit.activities.RegisterActivity
import com.example.biscuit.utilits.AUTH
import com.example.biscuit.utilits.replaceActivity
import com.example.biscuit.utilits.replaceFragment
import com.example.biscuit.utilits.showToast
import com.example.biscuite.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit

class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var mPhoneNumber: String
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()

        //верификация по номеру телефона
        mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener{
                    //проверка пршла ли верификация
                    if (it.isSuccessful) {
                        //если прошла то приветствуем и запускаем MainActivity
                        showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(it.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(firebaseException: FirebaseException) {
                showToast(firebaseException.message.toString())
            }

            // этот метод запускается когда отправлен смс код
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                // после отправки кода переходим в EnterCodeFragment()
                replaceFragment(EnterCodeFragment(mPhoneNumber, id))

            }
        }
        register_btn_next.setOnClickListener{
            sendCodeForRegistration()
        }
    }

    private fun sendCodeForRegistration() {
        // проверка пустого поле EditText
        if (register_input_phone_number.text.toString().isEmpty())
            showToast(R.string.register_toast_enter_phone.toString())
        else {
            authUser()
        }
    }

    //авторизация и верификация
    private fun authUser() {
        mPhoneNumber = register_input_phone_number.text.toString()
        val options = PhoneAuthOptions.newBuilder(AUTH)
            .setPhoneNumber(mPhoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity as RegisterActivity)
            .setCallbacks(mCallBack)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)

//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//            mPhoneNumber,
//            60,
//            TimeUnit.SECONDS,
//            activity as RegisterActivity,
//            mCallBack
//        )
    }
}