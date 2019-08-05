package com.kalashnyk.denys.retrofitexample.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kalashnyk.denys.retrofitexample.presentation.fragments.AllUsersFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.AddUserFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.DeleteUserFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.EditUserFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.FileFragment;
import com.kalashnyk.denys.retrofitexample.presentation.fragments.SingleUserFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AllUsersFragment.newInstance();
            case 1:
                return SingleUserFragment.newInstance();
            case 2:
                return AddUserFragment.newInstance();
            case 3:
                return EditUserFragment.newInstance();
            case 4:
                return DeleteUserFragment.newInstance();
            case 5:
                return FileFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ALL";
            case 1:
                return "BY ID";
            case 2:
                return "ADD";
            case 3:
                return "EDIT";
            case 4:
                return "DELETE";
            case 5:
                return "FILE";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

}
