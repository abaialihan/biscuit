package com.example.biscuit.utilits

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference

const val NODE_USERS = "users"
const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"

// инициализация firebase
fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    // ссылка для biscuite-1499f-default-rtdb в консоли firebase, realtime database
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
}