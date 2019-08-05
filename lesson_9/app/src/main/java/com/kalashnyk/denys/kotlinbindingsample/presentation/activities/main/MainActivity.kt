package com.kalashnyk.denys.kotlinbindingsample.presentation.activities.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kalashnyk.denys.kotlinbindingsample.R
import com.kalashnyk.denys.kotlinbindingsample.databinding.ActivityMainBinding
import com.kalashnyk.denys.kotlinbindingsample.di.component.ViewModelComponent
import com.kalashnyk.denys.kotlinbindingsample.domain.AllUsersViewModel
import com.kalashnyk.denys.kotlinbindingsample.presentation.activities.detail.DetailActivity
import com.kalashnyk.denys.kotlinbindingsample.presentation.adapter.UserAdapter
import com.kalashnyk.denys.kotlinbindingsample.presentation.base.BaseActivity
import com.kalashnyk.denys.kotlinbindingsample.presentation.item.IUserItemClickListener
import com.kalashnyk.denys.kotlinbindingsample.repository.database.entity.UserEntity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    var viewModel: AllUsersViewModel? = null
        @Inject set

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel?.getAllItems()
        viewModel?.getLiveDataItems()?.observe(this, Observer { it?.let { initRecyclerView(it) } } )
    }

    private fun initRecyclerView(users: List<UserEntity>) {
        val manager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(this, users, itemClickListener)
        userAdapter.setItemClickListener(itemClickListener)
        rvUsers.layoutManager = manager
        rvUsers.adapter = userAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    private val itemClickListener = object : IUserItemClickListener<UserEntity> {
        override fun openDetail(entity: UserEntity) {
            openItemDetail(entity.id)
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun openItemDetail(id: Int) {
        this.startActivity(DetailActivity.newInstance(this, id))
    }
}
