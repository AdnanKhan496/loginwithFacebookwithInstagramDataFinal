package com.example.loginwithfacebook.networkLayer

import com.example.loginwithfacebook.models.*
import io.reactivex.Single
import retrofit2.http.*

interface ApiInterface {
    @GET("me")
    fun fetchProfile(@Query ("fields") fields: String, @Query ("access_token") access_token: String) : Single<Profile>

    @GET("me/media")
    fun fetchMedia(@Query ("fields") fields: String, @Query ("access_token") access_token: String) : Single<InstagramMedia>

    @GET("v9.0/me/accounts")
    fun getUserPages(@Query ("access_token") access_token: String) : Single<User>

    //Get insta B Acc Of That Page
  //  @GET("v9.0/471762816342012")
    @GET("v9.0/{fb_page_id}")
    fun getPageInstagramBusinessAccount(@Path("fb_page_id") fb_page_id :String ,@Query ("fields") fields: String, @Query ("access_token") access_token: String) : Single<InstagramBusinessAccountData>

    //Get Data Of Inst B Acc
    @GET("v9.0/{page_id}")
    fun getInstagramData(@Path("page_id") page_id :String, @Query ("fields") fields: String, @Query ("access_token") access_token: String) : Single<InstagramData>


}