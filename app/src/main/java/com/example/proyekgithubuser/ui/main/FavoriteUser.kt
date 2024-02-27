package com.example.proyekgithubuser.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyekgithubuser.R
import com.example.proyekgithubuser.UsersItem
import com.example.proyekgithubuser.database.FavoriteUser
import com.example.proyekgithubuser.databinding.ActivityDetailUserBinding
import com.example.proyekgithubuser.databinding.ActivityFavoriteUserBinding
import com.example.proyekgithubuser.databinding.ActivityMainBinding

class FavoriteUser : AppCompatActivity() {
    private val favoriteUserViewModel by viewModels<FavoriteUserViewModel>(){
        ViewModelFactory.getInstance(application)
    }

    private var _activityFavoriteUserBinding: ActivityFavoriteUserBinding? = null
    private val activityFavoriteUserBinding get() = _activityFavoriteUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityFavoriteUserBinding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(activityFavoriteUserBinding?.root)

        val layoutManager = LinearLayoutManager(this)
        activityFavoriteUserBinding?.rvFavUsers?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        activityFavoriteUserBinding?.rvFavUsers?.addItemDecoration(itemDecoration)

        favoriteUserViewModel.getAllFavUser().observe(this) { users ->
            val items = arrayListOf<UsersItem>()
            users!!.map {
                val item = UsersItem(login = it.username, avatarUrl = it.avatarUrl)
                items.add(item)
            }
            activityFavoriteUserBinding?.rvFavUsers?.adapter = ListUserAdapter(items)
        }
    }
}