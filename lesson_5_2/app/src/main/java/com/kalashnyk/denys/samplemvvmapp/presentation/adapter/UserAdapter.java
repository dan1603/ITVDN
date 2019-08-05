package com.kalashnyk.denys.samplemvvmapp.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kalashnyk.denys.samplemvvmapp.R;
import com.kalashnyk.denys.samplemvvmapp.presentation.base.BaseAdapter;
import com.kalashnyk.denys.samplemvvmapp.presentation.item.IUserItemClickListener;
import com.kalashnyk.denys.samplemvvmapp.presentation.item.UserViewHolder;
import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;

import java.util.List;

public class UserAdapter extends BaseAdapter<UserViewHolder, UserEntity, IUserItemClickListener> {

    private List<UserEntity> mUsers;
    private Context mContext;

    public UserAdapter(Context context, List<UserEntity> list) {
        super(context, list);
        mUsers = list;
        mContext = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        super.onBindViewHolder(userViewHolder, i);
        userViewHolder.bind(mUsers.get(i), mItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
