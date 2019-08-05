package com.kalashnyk.denys.kotlinbindingsample.presentation.activities.detail

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.kalashnyk.denys.kotlinbindingsample.R
import com.kalashnyk.denys.kotlinbindingsample.databinding.UserDetailBinding
import com.kalashnyk.denys.kotlinbindingsample.di.component.ViewModelComponent
import com.kalashnyk.denys.kotlinbindingsample.domain.SingleUserViewModel
import com.kalashnyk.denys.kotlinbindingsample.presentation.base.BaseActivity
import com.kalashnyk.denys.kotlinbindingsample.repository.database.entity.UserEntity
import java.util.*
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    var viewModel: SingleUserViewModel? = null
        @Inject set

    private lateinit var binding: UserDetailBinding
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        initViewModel()
    }

    private fun initViewModel() {
        userId = intent.getIntExtra(getString(R.string.EXTRAS_ID), 0)
        viewModel?.getItem(userId)
        viewModel?.getLiveDataItem()?.observe(this, Observer { it?.let { initDataBinding(it) } })
    }

    private fun initDataBinding(user: UserEntity) {
        binding.user = user
        initActionBar("${user.surname} ${user.name} ${user.fathername}")
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context, id: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(context.getString(R.string.EXTRAS_ID), id)
            return intent
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun initActionBar(title: String) {
        Objects.requireNonNull(supportActionBar)?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
