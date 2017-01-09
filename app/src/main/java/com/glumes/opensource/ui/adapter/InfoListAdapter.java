package com.glumes.opensource.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.glumes.opensource.R;
import com.glumes.opensource.net.entity.BaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoying on 2017/1/9.
 */

public class InfoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<BaseResult> mResults ;
    private Context mContext ;



    public InfoListAdapter(Context context) {
        mContext = context;
        mResults = new ArrayList<>() ;
    }

    public InfoListAdapter(List<BaseResult> results, Context context) {
        mResults = results;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Glide.with(mContext).load(mResults.get(position).getUrl()).into(holder.mImageView);
//
//    }

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
