package com.example.proyekgithubuser.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyekgithubuser.R
import com.example.proyekgithubuser.UsersItem

class ListUserAdapter(private val listUsers: List<UsersItem>) : RecyclerView.Adapter<ListUserAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_users, viewGroup, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvName.text = listUsers[position].login
        Glide.with(viewHolder.itemView.context)
            .load(listUsers[position].avatarUrl)
            .into(viewHolder.imgPhoto)
        viewHolder.itemView.setOnClickListener{
            val intentDetail = Intent(viewHolder.itemView.context, DetailUser::class.java)
            intentDetail.putExtra(DetailUser.username, listUsers[viewHolder.adapterPosition].login)
            intentDetail.putExtra(DetailUser.avatarURL, listUsers[viewHolder.adapterPosition].avatarUrl)
            viewHolder.itemView.context.startActivity(intentDetail)
        }
    }
    override fun getItemCount() = listUsers.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
    }
}