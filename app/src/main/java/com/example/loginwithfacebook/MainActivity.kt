package com.example.loginwithfacebook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.loginwithfacebook.networkLayer.ApiService
import com.facebook.*
import com.facebook.AccessToken
import com.facebook.FacebookSdk.setAutoLogAppEventsEnabled
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*
import java.util.Calendar.getInstance


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager
    private var EMAIL = "email"
    private var PAGES_SHOW_LIST = "pages_show_list"
    private var PAGES_READ_ENGAGEMENTS = "pages_read_engagement"
    private var INSTAGRAM_BASIC = "instagram_basic"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // instaClient = InstaClient(applicationContext, "username","password");

        login_button.setOnClickListener(View.OnClickListener {
            //  LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("read_custom_friendlists", "email"));
            // login_button.setReadPermissions(listOf(EMAIL))



            login_button.setReadPermissions(Arrays.asList(EMAIL,PAGES_SHOW_LIST , PAGES_READ_ENGAGEMENTS, INSTAGRAM_BASIC))
            callbackManager = CallbackManager.Factory.create()

            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    //Login with FB successful
                   // Toast.makeText(this@MainActivity,"Login SucessFul", Toast.LENGTH_SHORT).show()

                  //  setAutoLogAppEventsEnabled(true);
                    val intent = Intent(applicationContext, LandingActivity::class.java)
                    startActivity(intent)

                    //to get user data we will use Graph Api

                    /*  val graphRequest = GraphRequest.newMeRequest(result?.accessToken) { obj, response ->

                        try {
                            if (obj.has("id")) {
                                Log.d("FACEBOOK_DATA", obj.getString("id"))
                                Log.d("FACEBOOK_DATA", obj.getString("name"))
                                Log.d("FACEBOOK_DATA", obj.getString("email"))

                                Log.d("FACEBOOK_DATA", JSONObject(obj.getString("picture")).getJSONObject("data").getString("url"))

                                Log.d("FACEBOOK_DATA", obj.getString("birthday"))
                                Log.d("FACEBOOK_DATA", JSONObject(obj.getString("friends")).getJSONObject("summary").getString("total_count"))


                                tvUserName.text = obj.getString("name")
                                tvEmail.text = obj.getString("email")
                                tvBirthday.text = obj.getString("birthday")
                                tvFriendsCount.text = JSONObject(obj.getString("friends")).getJSONObject("summary").getString("total_count")
                                Glide.with(applicationContext).load(JSONObject(obj.getString("picture")).getJSONObject("data").getString("url")).into(imageView);


                            }

                        } catch (e: Exception) {
                        }

                    }

                    val param = Bundle()
                    param.putString("fields", "name,email,id,picture.type(large),birthday,friends")
                    graphRequest.parameters = param
                    graphRequest.executeAsync()
*/

                }

                override fun onCancel() {
                    // TODO("Not yet implemented")
                    Toast.makeText(this@MainActivity,"onCancel", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: FacebookException?) {
                    //   TODO("Not yet implemented")
                    Toast.makeText(this@MainActivity,"onError :: $error",Toast.LENGTH_SHORT).show()
                }

            })

        })





      /*
        *//* make the API call *//*

        *//* make the API call *//*GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "id",
                null,
                HttpMethod.GET
        ) {
            Log.d("FACEBOOK_DATA", "id")
        }.executeAsync()

*/
    }



   /*

    override fun onResume() {
        super.onResume()
      //  callApi()
      //  callMediaApi()
      //  getUsersApi()
      //  getPageInstagramBusinessAccount()
    }

    fun callApi(){

        ApiService.buildService().fetchProfile("id,username,account_type,media_count", "IGQVJWcVduMklwQTNKM1RLWlVDQ25nX0ZA3VEU4OE5Cdi1ZANHlLdEFPd05fLU95clNQSDExLVZApOEZAxd2lSeW1nV3A4ZAUhPbTVuLXBabFd3TDYyNG51WUpVSm9LSWZAoM3hnTkFoSDJ4eVktX2EwRDg0dQZDZD")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { response ->
                    var data = response

                    Log.e("TestInstagramProfile", data.toString())
                }
    }

    fun callMediaApi(){

        ApiService.buildService().fetchMedia("id,caption,media_type,media_url,permalink,thumbnail_url,timestamp,username,children", "IGQVJWcVduMklwQTNKM1RLWlVDQ25nX0ZA3VEU4OE5Cdi1ZANHlLdEFPd05fLU95clNQSDExLVZApOEZAxd2lSeW1nV3A4ZAUhPbTVuLXBabFd3TDYyNG51WUpVSm9LSWZAoM3hnTkFoSDJ4eVktX2EwRDg0dQZDZD")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { response ->
                    var data = response

                    Log.e("TestMedia", data.toString())
                }
    }

    fun getUsersApi(){

        ApiService.buildService().getUserPages("EAAJk6XhjBt8BANS8FzjSSzZBYQcwf5Nc6a8N9kxE0pFs9y5t542geDGj77f95ZAovMe1k3fEWNbUlvX90EUqTLQrl6ZBxgWtOUiQf9yZA7BFlulcaMH8CAIGCRfo1i50RtYNRgDV48YZCX7xnVxOGAKIipAeCnAKNYny0KlTFL3XTznR1BizbP0OZAAgqRc5U9R8KsDokWA89RXBALNGYK6yaTfT8lkPAZD")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { response ->
                    var data = response

                    Log.e("TestUser", data.toString())
                }
    }

    fun getPageInstagramBusinessAccount(){

        ApiService.buildService().getPageInstagramBusinessAccount("instagram_business_account","EAAJk6XhjBt8BANS8FzjSSzZBYQcwf5Nc6a8N9kxE0pFs9y5t542geDGj77f95ZAovMe1k3fEWNbUlvX90EUqTLQrl6ZBxgWtOUiQf9yZA7BFlulcaMH8CAIGCRfo1i50RtYNRgDV48YZCX7xnVxOGAKIipAeCnAKNYny0KlTFL3XTznR1BizbP0OZAAgqRc5U9R8KsDokWA89RXBALNGYK6yaTfT8lkPAZD")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { response ->
                    var data = response

                    Log.e("fasd", data.toString())
                }
    }


    */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode, resultCode, data)
    }




}