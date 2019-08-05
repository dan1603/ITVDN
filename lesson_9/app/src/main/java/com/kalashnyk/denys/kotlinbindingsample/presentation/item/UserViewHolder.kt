package com.kalashnyk.denys.kotlinbindingsample.presentation.item

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kalashnyk.denys.kotlinbindingsample.databinding.UserItemBinding
import com.kalashnyk.denys.kotlinbindingsample.repository.database.entity.UserEntity

class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private var userEntity: UserEntity? = null
    private var listener: IUserItemClickListener<UserEntity>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this!!.userEntity!!) }

    fun bind(user: UserEntity, listener: IUserItemClickListener<UserEntity>) {
        userEntity = user
        this.listener = listener
        setupItem()
    }

    private fun setupItem() {
        binding.user = userEntity
        binding.root.setOnClickListener(itemDetail)
    }
}
