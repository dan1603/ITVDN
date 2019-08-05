package com.kalashnyk.denys.gmapsapp.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalashnyk.denys.gmapsapp.R;
import com.kalashnyk.denys.gmapsapp.repository.database.entity.PinEntity;

import java.util.List;

public class PinPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<PinEntity> mPins;

    public PinPagerAdapter(Context context, List<PinEntity> pins) {
        mContext = context;
        mPins = pins;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View layout = mLayoutInflater.inflate(R.layout.item_pin, container, false);
        TextView title = layout.findViewById(R.id.tv_pin_title);
        TextView lat = layout.findViewById(R.id.tv_pin_lat);
        TextView lng = layout.findViewById(R.id.tv_pin_lng);
        title.setText(mPins.get(position).getTitle());
        lat.setText(mPins.get(position).getLat());
        lng.setText(mPins.get(position).getLng());
        container.addView(layout, 0);
        return layout;
    }

    @Override
    public int getCount() {
        return mPins.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
