package com.example.proyekgithubuser

import com.example.proyekgithubuser.ui.main.DetailUserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token //insert TOKEN HERE")
    fun findUserByUsername(
        @Query("q") username: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token //insert TOKEN HERE")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token //insert TOKEN HERE")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<UsersItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token //insert TOKEN HERE")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<UsersItem>>
}