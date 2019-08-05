package com.kalashnyk.denys.simplemvpapp.presentation.activity.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.kalashnyk.denys.simplemvpapp.R;
import com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity;
import com.kalashnyk.denys.simplemvpapp.di.component.PresenterComponent;
import com.kalashnyk.denys.simplemvpapp.presentation.base.BaseActivity;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity implements DetailContract.View {

    private int mUserId;

    @Inject
    protected DetailContract.Presenter mPresenter;

    public static Intent newInstance(Context context, int id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(context.getString(R.string.EXTRAS_ID), id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mPresenter.attachWithView(this);
        mUserId = getIntent().getIntExtra(getString(R.string.EXTRAS_ID), 0);
        mPresenter.getUser(mUserId);
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

    @Override
    protected void injectDependency(PresenterComponent presentersComponent) {
        presentersComponent.inject(this);
    }

    @Override
    public void showUser(UserEntity user) {
        initializeToolbar(user.getName(), R.drawable.ic_close, false);

        TextView tvId = findViewById(R.id.txt_detail_id);
        TextView tvName = findViewById(R.id.txt_detail_name);
        TextView tvSurName = findViewById(R.id.txt_detail_surname);
        TextView tvFatherName = findViewById(R.id.txt_detail_fathername);

        tvId.setText(String.valueOf(user.getId()));
        tvName.setText(user.getName());
        tvSurName.setText(user.getSurname());
        tvFatherName.setText(user.getFathername());
    }
}
