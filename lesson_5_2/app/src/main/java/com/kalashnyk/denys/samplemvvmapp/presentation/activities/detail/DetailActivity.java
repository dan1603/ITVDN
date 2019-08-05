package com.kalashnyk.denys.samplemvvmapp.presentation.activities.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.kalashnyk.denys.samplemvvmapp.R;
import com.kalashnyk.denys.samplemvvmapp.di.component.ViewModelComponent;
import com.kalashnyk.denys.samplemvvmapp.domain.SingleUserViewModel;
import com.kalashnyk.denys.samplemvvmapp.presentation.base.BaseActivity;
import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;

import java.util.Objects;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity {

    @Inject
    SingleUserViewModel mViewModel;

    private int mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initViewModel();
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    public static Intent newInstance(Context context, int id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(context.getString(R.string.EXTRAS_ID), id);
        return intent;
    }

    private void initViewModel() {
        mUserId = getIntent().getIntExtra(getString(R.string.EXTRAS_ID), 0);
        mViewModel.getItem(mUserId);
        mViewModel.getLiveDataItem().observe(this, userEntity -> initTextViews(userEntity));
    }

    private void initTextViews(UserEntity user) {
        TextView tvId = findViewById(R.id.txt_detail_id);
        TextView tvName = findViewById(R.id.txt_detail_name);
        TextView tvSurName = findViewById(R.id.txt_detail_surname);
        TextView tvFatherName = findViewById(R.id.txt_detail_fathername);

        tvId.setText(String.valueOf(user.getId()));
        tvName.setText(user.getName());
        tvSurName.setText(user.getSurname());
        tvFatherName.setText(user.getFathername());

        initActionBar(user.getName());
    }

    private void initActionBar(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

