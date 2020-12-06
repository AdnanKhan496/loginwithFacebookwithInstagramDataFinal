package com.example.loginwithfacebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loginwithfacebook.adapters.CustomAdapter
import com.example.loginwithfacebook.models.UserPagesData
import com.example.loginwithfacebook.models.id
import com.example.loginwithfacebook.networkLayer.ApiService
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.GraphRequest
import org.json.JSONObject
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.users_item.*

class LandingActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager
    lateinit var loginManager: LoginManager
    lateinit var loginResult: LoginResult
    private var EMAIL = "email"
    private var FRIEND_L = "read_custom_friendlists"
    private var userNAme = "username"



    var token1 = AccessToken.getCurrentAccessToken()

    var users = ArrayList<UserPagesData>()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        Toast.makeText(applicationContext,"Login Success",Toast.LENGTH_SHORT).show()

        getUsersApi()
       // getPageInstagramBusinessAccount()

       /* val rv = findViewById<RecyclerView>(R.id.rvUsers)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        // val users = ArrayList<UserPagesData>()

        var customAdapter = CustomAdapter(users)
        rv.adapter = customAdapter*/





        val graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken()) { obj, response ->

            try {
                if (obj.has("id")) {
                    Toast.makeText(applicationContext,"DataFetched",Toast.LENGTH_SHORT).show()
                    Log.d("FACEBOOK_DATA", obj.getString("id"))
                    Log.d("FACEBOOK_DATA", obj.getString("name"))
                    Log.d("FACEBOOK_DATA", obj.getString("email"))

                    Log.d(
                        "FACEBOOK_DATA",
                        JSONObject(obj.getString("picture")).getJSONObject("data").getString("url")
                    )

                    Log.d("FACEBOOK_DATA", obj.getString("birthday"))
                    Log.d(
                        "FACEBOOK_DATA",
                        JSONObject(obj.getString("friends")).getJSONObject("summary")
                            .getString("total_count")
                    )


                    tvUserName.text = obj.getString("name")
                    tvEmail.text = obj.getString("email")
                    tvBirthday.text = obj.getString("birthday")
                    tvFriendsCount.text =
                        JSONObject(obj.getString("friends")).getJSONObject("summary")
                            .getString("total_count")
                    Glide.with(applicationContext).load(
                        JSONObject(obj.getString("picture")).getJSONObject("data").getString("url")
                    ).into(imageView);


                  //  getUsersApi()
                  //  getPageInstagramBusinessAccount()


                }

            } catch (e: Exception) {
                Toast.makeText(applicationContext,"Exception: $e",Toast.LENGTH_SHORT).show()

            }

        }

        val param = Bundle()
        param.putString("fields", "name,email,id,picture.type(large),birthday,friends")
        graphRequest.parameters = param
        graphRequest.executeAsync()


        btn_Instagram_data.setOnClickListener(View.OnClickListener {
           // getUsersApi()
           // getPageInstagramBusinessAccount()
        })



    }

    override fun onResume() {
        super.onResume()
        //  callApi()
        //  callMediaApi()
        //  getUsersApi()
        //  getPageInstagramBusinessAccount()
    }


    fun callApi(){

        ApiService.buildService().fetchProfile("id,username,account_type,media_count", token1.token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { response ->
                var data = response

                Log.e("TestInstagramProfile", data.toString())
            }
    }

    fun callMediaApi(){

        ApiService.buildService().fetchMedia("id,caption,media_type,media_url,permalink,thumbnail_url,timestamp,username,children", "EAAJk6XhjBt8BADousWKWSthZAZBUoZB3OZCuOFZC1QMHkJxvhUhokE6LDpEkJazKJmWMzbdfUvsISoLpzo1V9tlcufzJ7J3FcAUhI5tKGeylK6JFR0PfKjJkgfWEBvHanWzBEhZCSjMv3hadOFhyuZCJGkTSlPXSPzFYJ6xEp3i5VNbE6QdLyyF7kMAnEbx1VY0QqJgMaNK50pZAqJvQkVacL6x5MS6YeP8ZD")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { response ->
                var data = response

                Log.e("TestMedia", data.toString())
            }
    }

    fun getUsersApi(){

        ApiService.buildService().getUserPages(token1.token
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { response ->
                var data = response
                Toast.makeText(applicationContext,"getUsersApi Called: ${response.data}",Toast.LENGTH_SHORT).show()

                users = data.data


                getPageInstagramBusinessAccount(users.get(0).id)
                val rv = findViewById<RecyclerView>(R.id.rvUsers)
                rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                // val users = ArrayList<UserPagesData>()

                var customAdapter = CustomAdapter(users)
                rv.adapter = customAdapter
               // if(user_id.text.toString().equals(null)){

              //  }



                Log.e("TestUser", data.toString())
            }
    }

    fun getPageInstagramBusinessAccount(id: String){

        ApiService.buildService().getPageInstagramBusinessAccount(id,"instagram_business_account",token1.token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { response ->
                var data = response
              //  Toast.makeText(applicationContext,"getPageInstagramBusinessAccount Called: ${data.toString()}",Toast.LENGTH_SHORT).show()

                getInstagramData(data.instagram_business_account.id)
                Log.e("fasd", data.toString())
            }
    }


    fun getInstagramData(id: String) {

        ApiService.buildService().getInstagramData(id,"username,profile_picture_url,followers_count,follows_count,biography,media_count,website", token1.token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { response ->
                var data = response
                Toast.makeText(applicationContext,"getPageInstagramBusinessAccount Called: ${data.toString()}",Toast.LENGTH_SHORT).show()
                Log.e("fasd", data.toString())

                tvInstaUserNAme.text = data.username
                tvInstaFollowers.text = data.followers_count.toString()
                tvInstaFollowings.text = data.follows_count.toString()
                tvInstaId.text = data.id
                Glide.with(applicationContext).load(data.profile_picture_url).into(ivInstaDp);
                tvMedia_count.text = data.media_count.toString()
                tvInstaWebsite.text = data.website
                tvInstaBiography.text = data.biography
            }
    }






}