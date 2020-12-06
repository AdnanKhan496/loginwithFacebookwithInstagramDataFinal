package com.example.loginwithfacebook.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.loginwithfacebook.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*


class MainFragment : Fragment() {

    lateinit var callbackManager: CallbackManager
    private var EMAIL = "email"
    private var FRIEND_L = "read_custom_friendlists"
    private var userNAme = "username"

    var login_facebook: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_main, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* textView2.setOnClickListener(View.OnClickListener {
            Toast.makeText(activity,"TEXT",Toast.LENGTH_SHORT).show()
        })*/
    }


}