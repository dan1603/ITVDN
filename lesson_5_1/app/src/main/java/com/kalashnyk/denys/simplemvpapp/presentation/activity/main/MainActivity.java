package com.kalashnyk.denys.simplemvpapp.presentation.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kalashnyk.denys.simplemvpapp.R;
import com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity;
import com.kalashnyk.denys.simplemvpapp.di.component.PresenterComponent;
import com.kalashnyk.denys.simplemvpapp.presentation.activity.detail.DetailActivity;
import com.kalashnyk.denys.simplemvpapp.presentation.adapter.UserAdapter;
import com.kalashnyk.denys.simplemvpapp.presentation.base.BaseActivity;
import com.kalashnyk.denys.simplemvpapp.presentation.item.IUserItemClickListener;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    protected MainContract.Presenter mPresenter;

    private UserAdapter mAdapter;

    private IUserItemClickListener<UserEntity> mItemClickListener = new IUserItemClickListener<UserEntity>() {
        @Override
        public void openDetail(UserEntity productEntity) {
            openItemDetail(productEntity.getId());
        }
    };

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbar("Users", false);
        mPresenter.attachWithView(this);
        mPresenter.getUsers();
    }

    @Override
    protected void injectDependency(PresenterComponent presentersComponent) {
        presentersComponent.inject(this);
    }

    private void openItemDetail(long id) {
        getContext().startActivity(DetailActivity.newInstance(this, (int) id));
    }

    @Override
    public void showUsers(List<UserEntity> users) {
        RecyclerView rvUsers = findViewById(R.id.rv_users);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mAdapter = new UserAdapter(this, users);
        mAdapter.setItemClickListener(mItemClickListener);
        rvUsers.setLayoutManager(manager);
        rvUsers.setAdapter(mAdapter);

    }

}
