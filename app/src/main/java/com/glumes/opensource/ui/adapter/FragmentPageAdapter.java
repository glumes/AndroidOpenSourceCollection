package com.glumes.opensource.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.glumes.opensource.R;
import com.glumes.opensource.net.entity.BaseResult;
import com.glumes.opensource.ui.fragment.InfoFragment;
import com.glumes.opensource.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoying on 16/11/13.
 */

public class FragmentPageAdapter extends FragmentPagerAdapter{


    private Context mContext ;
    private String tabTitles[] = new String[]{"Android","iOS","前端","拓展资源"} ;
    private static final int NUM = 10 ;
    private static final int PAGE = 1 ;

    private ArrayList<Fragment> mFragments ;
    public FragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context ;
        mFragments = new ArrayList<>();
        mFragments.add(InfoFragment.newInstance("Android",NUM,PAGE));
        mFragments.add(InfoFragment.newInstance("iOS",NUM,PAGE));
        mFragments.add(InfoFragment.newInstance("前端",NUM,PAGE));
        mFragments.add(InfoFragment.newInstance("拓展资源",NUM,PAGE));

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
