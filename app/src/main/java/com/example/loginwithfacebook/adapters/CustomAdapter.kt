package com.example.loginwithfacebook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.loginwithfacebook.R
import com.example.loginwithfacebook.models.User
import com.example.loginwithfacebook.models.UserPagesData

class CustomAdapter (val userList: ArrayList<UserPagesData>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // holder?.userId?.text = userList[position].id
       // holder?.userName?.text = userList[position].name
        holder?.bind(userList.get(position))

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.users_item, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val userId = itemView.findViewById<TextView>(R.id.user_id)
        val userName = itemView.findViewById<TextView>(R.id.user_name)

        fun bind(users: UserPagesData){
            userId.text = "ID: " + users.id
            userName.text = "User NAme: " + users.name

        }



    }


}