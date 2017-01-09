package com.glumes.opensource.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.glumes.opensource.MyApplication;
import com.glumes.opensource.R;
import com.glumes.opensource.base.BaseFragment;
import com.glumes.opensource.di.components.DaggerFragmentComponent;
import com.glumes.opensource.di.modules.FragmentModule;
import com.glumes.opensource.di.modules.GankApiModule;
import com.glumes.opensource.net.entity.BaseResult;
import com.glumes.opensource.ui.adapter.InfoListAdapter;
import com.glumes.opensource.ui.contract.InfoContract;
import com.glumes.opensource.view.RecyclerViewScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class InfoFragment extends BaseFragment implements InfoContract.InfoView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    @BindView(R.id.info_list)
    RecyclerView mInfoList;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;


    private String mType;

    private int mPage;
    private int mNum;
    RecyclerView.LayoutManager mLayoutManager;

    private InfoListAdapter mInfoListAdapter ;

    @Inject
    public InfoContract.Presenter mPresenter ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFragmentComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .gankApiModule(new GankApiModule())
                .build().inject(this);

        if (getArguments() != null) {
            mType = getArguments().getString(ARG_PARAM1);
            mPage = getArguments().getInt(ARG_PARAM2);
            mNum = getArguments().getInt(ARG_PARAM3);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_benefit, container, false);
        ButterKnife.bind(this, view);
        initView();
        mPresenter.attachView(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.LoadData(mType, mPage, mNum);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(String type, int limit, int page) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, type);
        args.putInt(ARG_PARAM2, limit);
        args.putInt(ARG_PARAM3, page);
        fragment.setArguments(args);
        return fragment;
    }


    void initView() {
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mInfoList.setHasFixedSize(true);
        mInfoList.setLayoutManager(mLayoutManager);
        mRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.LoadData(mType, mPage++, mNum);
        });

        mInfoListAdapter = new InfoListAdapter(this.getActivity());

        mInfoList.setAdapter(mInfoListAdapter);
//        mInfoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
////                Timber.d("state is %d", newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
////                Timber.d("scrolled dx is %d,dy is %d", dx, dy);
//                Timber.d("totalItemCount is %d", mLayoutManager.getItemCount());
//                Timber.d("child Count is %d", mInfoList.getChildCount());
//                Timber.d("FirstVisibleItem is %d", ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition
//                        ());
//                Timber.d("LastVisibleItem is %d", ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition());
//                Timber.d("firstCompleteVisibleImte is %d", ((LinearLayoutManager) mLayoutManager)
//                        .findFirstCompletelyVisibleItemPosition());
//
//                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
//                int totalItemCount = mLayoutManager.getItemCount();
//                if (lastVisibleItem == totalItemCount -1  ) {
//                    if (!mPresenter.isLoading()) {
//                        Timber.e("refresh");
//                        mPresenter.LoadData(mType, mPage++, mNum);
//                    }
//                }
//            }
//        });

        mInfoList.addOnScrollListener(new RecyclerViewScrollListener(mLayoutManager,1) {
            @Override
            protected void loadMore() {
                if (!mPresenter.isLoading()){
                    Timber.d("load data");
                    if (mRefreshLayout.isRefreshing()){
                        mInfoListAdapter.notifyItemRemoved(mInfoListAdapter.getItemCount());
                    }

                    mPresenter.LoadData(mType,mPage++,mNum);
                }
            }
        });
    }


    @Override
    public void showLoading() {
        mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(true));
    }

    @Override
    public void dismissLoading() {
        mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(false));
    }


    @Override
    public void showContent(List<BaseResult> baseResults) {
        mInfoListAdapter.setResults(baseResults);
        mInfoListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFailed(Throwable e) {
        Toast.makeText(getActivity(),"load data failed",Toast.LENGTH_SHORT).show();
        Timber.e("error msg is %s", e.getMessage());
    }

}
