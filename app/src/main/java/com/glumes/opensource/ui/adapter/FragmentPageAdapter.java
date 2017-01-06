package com.glumes.opensource.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.glumes.opensource.ui.fragment.benefit.PictureFragment;
import com.glumes.opensource.utils.Constant;

import java.util.ArrayList;

/**
 * Created by zhaoying on 16/11/13.
 */

public class FragmentPageAdapter extends FragmentPagerAdapter{


    private Context mContext ;
    private String tabTitles[] = new String[]{"Tab1","Tab2","Tab3"} ;
    private final int PAGE_COUT = 3 ;

    private ArrayList<Fragment> mFragments ;
    public FragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context ;
        mFragments = new ArrayList<>();
        mFragments.add(PictureFragment.newInstance(Constant.PICTURE_TYPE,1,4));
        mFragments.add(PictureFragment.newInstance(Constant.PICTURE_TYPE,2,4));
        mFragments.add(PictureFragment.newInstance(Constant.PICTURE_TYPE,3,4));

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
