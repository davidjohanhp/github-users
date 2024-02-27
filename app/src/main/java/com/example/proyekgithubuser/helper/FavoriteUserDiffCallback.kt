package com.example.proyekgithubuser.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.proyekgithubuser.database.FavoriteUser

class FavoriteUserDiffCallback (private val mOldFavoriteUserList: List<FavoriteUser>, private val mNewFavoriteUserList: List<FavoriteUser>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavoriteUserList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavoriteUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavoriteUserList[oldItemPosition].username == mNewFavoriteUserList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = mOldFavoriteUserList[oldItemPosition]
        val newUser = mNewFavoriteUserList[newItemPosition]
        return oldUser.username == newUser.username && oldUser.avatarUrl == newUser.avatarUrl
    }
}