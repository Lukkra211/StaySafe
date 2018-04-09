package com.example.pc.staysafe.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
//Adapter for switching betweem fragments into ArticleActivity
public class MyPagerAdapter extends FragmentPagerAdapter {

    private int NUM_FRAGMENT;
    private ArrayList<Fragment> fragments;

    public MyPagerAdapter(AppCompatActivity context, ArrayList<Fragment> fragments) {
        super(context.getSupportFragmentManager());
        this.fragments = fragments;
        NUM_FRAGMENT = fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return NUM_FRAGMENT;
    }

    public void updateFragments(ArrayList<Fragment> fragments){
        this.fragments = fragments;
    }
}