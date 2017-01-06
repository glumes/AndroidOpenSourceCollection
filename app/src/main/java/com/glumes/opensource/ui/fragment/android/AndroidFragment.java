package com.glumes.opensource.ui.fragment.android;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glumes.opensource.R;
import com.glumes.opensource.net.entity.BaseResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import timber.log.Timber;


public class AndroidFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.repo_list)
    RecyclerView mRepoList;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private String mParam1;
    private String mParam2;


    public AndroidFragment() {
        // Required empty public constructor
    }

    private Subscriber<List<BaseResult>> mSubscriber = new Subscriber<List<BaseResult>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Timber.e("error message is %s", e.getMessage());
        }

        @Override
        public void onNext(List<BaseResult> baseResults) {
            Timber.d("list size is %d ", baseResults.size());
            for (BaseResult result : baseResults) {
                Timber.d(result.getUrl());
            }
        }
    };

    public static AndroidFragment newInstance(String param1, String param2) {
        AndroidFragment fragment = new AndroidFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
