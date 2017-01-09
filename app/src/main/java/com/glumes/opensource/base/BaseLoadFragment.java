package com.glumes.opensource.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glumes.opensource.MyApplication;
import com.glumes.opensource.di.components.AppComponent;
import com.glumes.opensource.mvp.IView;

/**
 * Created by zhaoying on 2016/11/25.
 */

public abstract class BaseLoadFragment/*<P extends BaseInfoPresenter>*/ extends BaseFragment implements IView {

//    @Inject
//    protected P mPresenter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
//        initPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


//    protected abstract void initPresenter();


}
