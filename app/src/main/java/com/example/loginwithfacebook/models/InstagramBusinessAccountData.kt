package com.example.loginwithfacebook.models

import android.provider.ContactsContract
import java.io.Serializable
import java.util.*

class InstagramBusinessAccountData :Serializable {
    lateinit var instagram_business_account : i_b_a
    var id:String = ""

}

class i_b_a :Serializable{
    var id : String = ""
}