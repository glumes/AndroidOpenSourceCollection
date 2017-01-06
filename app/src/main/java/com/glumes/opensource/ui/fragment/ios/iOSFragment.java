package com.glumes.opensource.ui.fragment.ios;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glumes.opensource.R;
import com.glumes.opensource.net.entity.BaseResult;

import java.util.List;

import rx.Subscriber;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link iOSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class iOSFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private Subscriber<List<BaseResult>> mSubscriber = new Subscriber<List<BaseResult>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Timber.e("error message is %s",e.getMessage());
        }

        @Override
        public void onNext(List<BaseResult> baseResults) {
            Timber.d("list size is %d ", baseResults.size());
            for (BaseResult result : baseResults){
                Timber.d(result.getUrl());
            }
        }
    };

    public iOSFragment() {
        // Required empty public constructor
    }


    public static iOSFragment newInstance(String param1, String param2) {
        iOSFragment fragment = new iOSFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ios, container, false);
    }

}
