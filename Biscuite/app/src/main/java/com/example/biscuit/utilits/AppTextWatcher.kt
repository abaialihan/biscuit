package com.example.biscuit.utilits

import android.text.Editable
import android.text.TextWatcher

/** Улушение класса TextWatcher под себя **/
class AppTextWatcher(val onSuccess:(Editable?) -> Unit) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        onSuccess(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}