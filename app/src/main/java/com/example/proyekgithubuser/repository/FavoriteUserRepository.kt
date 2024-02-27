package com.example.proyekgithubuser.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.proyekgithubuser.database.FavoriteUser
import com.example.proyekgithubuser.database.FavoriteUserDao
import com.example.proyekgithubuser.database.FavoriteUserRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteUserRepository(application: Application) {
    private val mFavoriteUserDao: FavoriteUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteUserRoomDatabase.getDatabase(application)
        mFavoriteUserDao = db.favoriteUserDao()
    }

    fun insert(favoriteUser: FavoriteUser) {
        executorService.execute {
            mFavoriteUserDao.insert(favoriteUser)
        }
    }

    fun getFavoriteByUsername(username : String): LiveData<FavoriteUser> = mFavoriteUserDao.getFavoriteUserByUsername(username)

    fun delete(favoriteUser: FavoriteUser) {
        executorService.execute {
            mFavoriteUserDao.delete(favoriteUser)
        }
    }

    fun getAll() : LiveData<List<FavoriteUser>> = mFavoriteUserDao.getAllFavoriteUser()
}