package com.example.loginwithfacebook.models

import java.io.Serializable

class InstagramData: Serializable {

    var username: String = ""
    var profile_picture_url: String = ""
    var followers_count: Int = 0
    var follows_count: Int = 0
    var name: String = ""
    var id: String = ""
    var biography: String = ""
    var media_count: Int = 0
    var website: String = ""


}