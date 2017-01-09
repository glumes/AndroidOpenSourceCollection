package com.glumes.opensource.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


/**
 * Created by zhaoying on 2017/1/9.
 */

public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {


    RecyclerView.LayoutManager mLayoutManager ;
    private int mNum ;

    public RecyclerViewScrollListener(RecyclerView.LayoutManager layoutManager,int num) {
        mLayoutManager = layoutManager;
        mNum = num ;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                        int totalItemCount = mLayoutManager.getItemCount();
                if (lastVisibleItem == totalItemCount - mNum ) {
//                    if (!mPresenter.isLoading()) {
//                        Timber.e("refresh");
//                        mPresenter.LoadData(mType, mPage++, mNum);
//                    }
                    loadMore();
                }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }


    protected abstract void loadMore() ;
}
