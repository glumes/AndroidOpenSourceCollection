package com.glumes.opensource;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by zhaoying on 16/10/26.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public List<String> mDatas ;
    public Context mContext ;

    public RecyclerAdapter(List<String> datas,Context context) {
        this.mContext = context ;
        this.mDatas = datas ;
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0 ;i < 10 ; i ++){
            mDatas.add(String.valueOf(i));
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Timber.d(mDatas.get(position));

        Glide.with(mContext).load(mDatas.get(position)).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView ;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.picture);
        }
    }
}
