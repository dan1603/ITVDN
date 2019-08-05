package com.kalashnyk.denys.kotlinbindingsample.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kalashnyk.denys.kotlinbindingsample.databinding.UserItemBinding
import com.kalashnyk.denys.kotlinbindingsample.presentation.base.BaseAdapter
import com.kalashnyk.denys.kotlinbindingsample.presentation.item.IUserItemClickListener
import com.kalashnyk.denys.kotlinbindingsample.presentation.item.UserViewHolder
import com.kalashnyk.denys.kotlinbindingsample.repository.database.entity.UserEntity

class UserAdapter(private val context: Context, private val users: List<UserEntity>, private val listener: IUserItemClickListener<UserEntity>) :
    BaseAdapter<UserViewHolder, UserEntity, IUserItemClickListener<UserEntity>>(users as MutableList<UserEntity>, listener) {

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var inflater: LayoutInflater = LayoutInflater.from(context)
        var binding: UserItemBinding = UserItemBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(userViewHolder: UserViewHolder, i: Int) {
        super.onBindViewHolder(userViewHolder, i)
        userViewHolder.bind(users[i], listener)
    }
}
