package com.glumes.opensource.base;

import com.glumes.opensource.mvp.IModel;
import com.glumes.opensource.mvp.IPresenter;
import com.glumes.opensource.mvp.IView;
import com.glumes.opensource.net.entity.BaseResult;

import java.util.List;

import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by zhaoying on 2016/11/25.
 */


public abstract class BasePresenter<T extends IView, M extends IModel>
        implements IPresenter<T> {

    public CompositeSubscription mSubscription;
    private boolean isLoading;

    public T mView;

    protected M mModel;

    public BasePresenter(T view, M model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void attachView(T t) {
        mView = t;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        if (mSubscription != null && mSubscription.hasSubscriptions()) {
            mSubscription.unsubscribe();
        }
        mSubscription = null;
        mView = null;
        mModel = null;
        Timber.d("super method");
    }

//
//    public void LoadData(int type, int page, int num) {
//        mSubscription.add(mModel.getData(type, page, num)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(() -> {
//                    isLoading = true;
//                    mView.showLoading();
//                })
//                .doOnTerminate(() -> {
//                    isLoading = false;
//                    mView.dismissLoading();
//                })
//                .subscribe(new LoadSubscriber<List<BaseResult>>() {
//                    @Override
//                    protected void onSuccess(List<BaseResult> baseResults) {
//                        mView.showContent(baseResults);
//                    }
//
//                    @Override
//                    protected void onFailed(Throwable e) {
//                        mView.showFailed(e);
//                    }
//                }));
//    }


    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
