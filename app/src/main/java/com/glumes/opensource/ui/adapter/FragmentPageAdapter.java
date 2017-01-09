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
    private String tabTitles[] = new String[]{"Tab1","Tab2","Tab3"} ;
    private final int PAGE_COUT = 3 ;

    private ArrayList<Fragment> mFragments ;
    public FragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context ;
        mFragments = new ArrayList<>();
        mFragments.add(InfoFragment.newInstance(Constant.PICTURE_TYPE,1,4));
        mFragments.add(InfoFragment.newInstance(Constant.PICTURE_TYPE,2,4));
        mFragments.add(InfoFragment.newInstance(Constant.PICTURE_TYPE,3,4));

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


    /**
     * Created by zhaoying on 2016/12/7.
     */

    public static class PictureListAdapter extends RecyclerView.Adapter<PictureListAdapter.ViewHolder> {


        private List<BaseResult> mResults ;
        private Context mContext ;


        public PictureListAdapter(Context context) {
            mContext = context;
            mResults = new ArrayList<>() ;
        }

        public PictureListAdapter(List<BaseResult> results, Context context) {
            mResults = results;
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Glide.with(mContext).load(mResults.get(position).getUrl()).into(holder.mImageView);

        }

        @Override
        public int getItemCount() {
            return mResults.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder{

            public ImageView mImageView ;

            public ViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.picture);
            }

        }


        public List<BaseResult> getResults() {
            return mResults;
        }

        public void setResults(List<BaseResult> results) {
            mResults.addAll(results);
        }


    }
}
