package com.example.proyekgithubuser.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyekgithubuser.ApiConfig
import com.example.proyekgithubuser.UsersItem
import com.example.proyekgithubuser.database.FavoriteUser
import com.example.proyekgithubuser.repository.FavoriteUserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : ViewModel() {
    private val _userDetail = MutableLiveData<DetailUserResponse>()
    val userDetail: LiveData<DetailUserResponse> = _userDetail

    private val _userFollower = MutableLiveData<List<UsersItem>>()
    val userFollower: LiveData<List<UsersItem>> = _userFollower

    private val _userFollowing = MutableLiveData<List<UsersItem>>()
    val userFollowing: LiveData<List<UsersItem>> = _userFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoadingFollow = MutableLiveData<Boolean>()
    val isLoadingFollow: LiveData<Boolean> = _isLoadingFollow

    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)

    companion object{
        private const val TAG = "MainViewModel"
    }

    init {
        findUser(DetailUser.username)
        findUserFollower(DetailUser.username)
        findUserFollowing(DetailUser.username)
    }

    fun insertFavUser(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.insert(favoriteUser)
    }

    fun getFavUser(username: String): LiveData<FavoriteUser> = mFavoriteUserRepository.getFavoriteByUsername(username)

    fun deleteFavUser(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.delete(favoriteUser)
    }

    fun findUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _userDetail.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun findUserFollower(username: String) {
        _isLoadingFollow.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                _isLoadingFollow.value = false
                if (response.isSuccessful) {
                    _userFollower.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                _isLoadingFollow.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun findUserFollowing(username: String) {
        _isLoadingFollow.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                _isLoadingFollow.value = false
                if (response.isSuccessful) {
                    _userFollowing.value = response.body()
                    Log.d("response", _userFollowing.value.toString())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                _isLoadingFollow.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}