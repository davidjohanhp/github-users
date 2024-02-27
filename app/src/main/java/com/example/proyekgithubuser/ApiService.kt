package com.example.proyekgithubuser

import com.example.proyekgithubuser.ui.main.DetailUserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun findUserByUsername(
        @Query("q") username: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<UsersItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<UsersItem>>
}