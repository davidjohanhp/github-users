package com.example.proyekgithubuser.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.proyekgithubuser.database.FavoriteUser
import com.example.proyekgithubuser.repository.FavoriteUserRepository

class FavoriteUserViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)

    fun getAllFavUser(): LiveData<List<FavoriteUser>> = mFavoriteUserRepository.getAll()
}