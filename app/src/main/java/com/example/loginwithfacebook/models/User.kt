package com.example.loginwithfacebook.models

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class User: Serializable {
    var data: ArrayList<UserPagesData> = ArrayList()
}