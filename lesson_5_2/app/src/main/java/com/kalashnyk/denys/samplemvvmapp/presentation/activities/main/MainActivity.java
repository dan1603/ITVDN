package com.kalashnyk.denys.samplemvvmapp.presentation.activities.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kalashnyk.denys.samplemvvmapp.R;
import com.kalashnyk.denys.samplemvvmapp.di.component.ViewModelComponent;
import com.kalashnyk.denys.samplemvvmapp.domain.AllUsersViewModel;
import com.kalashnyk.denys.samplemvvmapp.presentation.activities.detail.DetailActivity;
import com.kalashnyk.denys.samplemvvmapp.presentation.adapter.UserAdapter;
import com.kalashnyk.denys.samplemvvmapp.presentation.base.BaseActivity;
import com.kalashnyk.denys.samplemvvmapp.presentation.item.IUserItemClickListener;
import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    protected AllUsersViewModel mViewModel;

    private UserAdapter mAdapter;

    private IUserItemClickListener<UserEntity> mItemClickListener = new IUserItemClickListener<UserEntity>() {
        @Override
        public void openDetail(UserEntity productEntity) {
            openItemDetail(productEntity.getId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel.getAllItems();
        mViewModel.getLiveDataItems().observe(this, this::initRecyclerView);
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    private void initRecyclerView(List<UserEntity> users) {
        RecyclerView rvUsers = findViewById(R.id.rv_users);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mAdapter = new UserAdapter(this, users);
        mAdapter.setItemClickListener(mItemClickListener);
        rvUsers.setLayoutManager(manager);
        rvUsers.setAdapter(mAdapter);
    }

    private void openItemDetail(long id) {
        this.startActivity(DetailActivity.newInstance(this, (int) id));
    }
}
