package com.example.proyekgithubuser.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyekgithubuser.R
import com.example.proyekgithubuser.UsersItem

class ListFollowAdapter(private val listUsers: List<UsersItem>) : RecyclerView.Adapter<ListFollowAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_users, viewGroup, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvName.text = listUsers[position].login
        Glide.with(viewHolder.itemView.context)
            .load(listUsers[position].avatarUrl)
            .into(viewHolder.imgPhoto)
    }
    override fun getItemCount() = listUsers.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPhoto: ImageView = view.findViewById(R.id.img_item_photo)
        val tvName: TextView = view.findViewById(R.id.tv_item_name)
    }
}