package com.kalashnyk.denys.samplemvvmapp.presentation.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kalashnyk.denys.samplemvvmapp.R;
import com.kalashnyk.denys.samplemvvmapp.repository.database.entity.UserEntity;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private View mItemView;
    private UserEntity mUser;
    private IUserItemClickListener<UserEntity> mListener;

    private View.OnClickListener mItemDetail = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mListener.openDetail(mUser);
        }
    };

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
    }

    public void bind(UserEntity user, IUserItemClickListener listener) {
        mUser = user;
        mListener = listener;
        setupItem();
    }

    private void setupItem() {
        TextView tvId = mItemView.findViewById(R.id.txt_rv_id);
        TextView tvName = mItemView.findViewById(R.id.txt_name);
        TextView tvSurName = mItemView.findViewById(R.id.txt_surname);
        TextView tvFatherName = mItemView.findViewById(R.id.txt_fathername);

        tvId.setText(String.valueOf(mUser.getId()));
        tvName.setText(mUser.getName());
        tvSurName.setText(mUser.getSurname());
        tvFatherName.setText(mUser.getFathername());

        mItemView.setOnClickListener(mItemDetail);
    }
}
