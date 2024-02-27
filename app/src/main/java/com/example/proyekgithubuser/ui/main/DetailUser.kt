package com.example.proyekgithubuser.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.proyekgithubuser.R
import com.example.proyekgithubuser.database.FavoriteUser
import com.example.proyekgithubuser.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUser : AppCompatActivity() {
    private val detailViewModel by viewModels<DetailViewModel>(){
        ViewModelFactory.getInstance(application)
    }
    private var _detailViewBinding: ActivityDetailUserBinding? = null
    private val detailBinding get() = _detailViewBinding

    private var isFavorite: Boolean = false

    companion object {
        const val username = "username"
        const val avatarURL = "url"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _detailViewBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detailBinding?.root)

        val usernameData = intent.getStringExtra(username)
        val avatarUrlData = intent.getStringExtra(avatarURL)

        if (usernameData != null) {
            detailViewModel.findUser(usernameData)
        }

        detailViewModel.userDetail.observe(this){
            userDetail -> setUserData(userDetail)
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        if (usernameData != null) {
            sectionsPagerAdapter.username = usernameData
        }
        val viewPager: ViewPager2 = detailBinding!!.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = detailBinding!!.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        val ivFavorite = detailBinding!!.fabAdd
        var checkUser: FavoriteUser? = null

        detailViewModel.getFavUser(usernameData!!).observe(this) {
            checkUser = it
            if (checkUser == null) {
                ivFavorite.setImageDrawable(ContextCompat.getDrawable(ivFavorite.context, R.drawable.ic_favorite_border_24))
                ivFavorite.setOnClickListener {
                    detailViewModel.insertFavUser(FavoriteUser(usernameData!!, avatarUrlData) as FavoriteUser)
                    Toast.makeText(this, usernameData + " added to favorites", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                ivFavorite.setImageDrawable(ContextCompat.getDrawable(ivFavorite.context, R.drawable.ic_favorite_24))
                isFavorite = true
                ivFavorite.setOnClickListener {
                    detailViewModel.deleteFavUser(checkUser as FavoriteUser)
                    Toast.makeText(this, usernameData + " removed from favorites", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUserData(detailUser: DetailUserResponse) {
        detailBinding!!.tvUserUsername.text = detailUser.login
        detailBinding!!.tvUserName.text = detailUser.name
        detailBinding!!.tvUserFollowing.text = "${detailUser.following.toString()} Following"
        detailBinding!!.tvUserFollower.text = "${detailUser.followers.toString()} Followers"
        Glide.with(detailBinding!!.root)
            .load(detailUser.avatarUrl)
            .into(detailBinding!!.itemImage)
    }

    private fun showLoading(isLoading: Boolean) {
        detailBinding!!.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        detailBinding!!.tvUserName.visibility = if (isLoading) View.GONE else View.VISIBLE
        detailBinding!!.tvUserUsername.visibility = if (isLoading) View.GONE else View.VISIBLE
        detailBinding!!.tvUserFollowing.visibility = if (isLoading) View.GONE else View.VISIBLE
        detailBinding!!.tvUserFollower.visibility = if (isLoading) View.GONE else View.VISIBLE
        detailBinding!!.itemImage.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _detailViewBinding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailViewModel::class.java)
    }
}