package com.glumes.opensource.base;

import com.glumes.opensource.mvp.IPresenter;
import com.glumes.opensource.net.LoadSubscriber;
import com.glumes.opensource.net.entity.BaseResult;

import java.util.List;

import dagger.Module;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by zhaoying on 2016/11/25.
 */

@Module
public abstract class BasePresenter<T extends LoadView<List<BaseResult>>, M extends BaseModel>
        implements IPresenter<T> {

    private CompositeSubscription mSubscription;
    private boolean isLoading;

    private T mView;

    protected M mModel;


//    @Provides
//    BasePresenter<T,M> provideBasePresenter(){
//        return new BasePresenter<T, M>() {
//            @Override
//            public void initModel() {
//
//            }
//        }
//    }



    @Override
    public void attachView(T t) {
        mView = t;
        initModel();
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


    public void LoadData(int type, int page, int num) {
        mSubscription.add(mModel.getData(type, page, num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> {
                    isLoading = true;
                    mView.showLoading();
                })
                .doOnTerminate(() -> {
                    isLoading = false;
                    mView.dismissLoading();
                })
                .subscribe(new LoadSubscriber<List<BaseResult>>() {
                    @Override
                    protected void onSuccess(List<BaseResult> baseResults) {
                        mView.showContent(baseResults);
                    }

                    @Override
                    protected void onFailed(Throwable e) {
                        mView.showFailed(e);
                    }
                }));
    }

    public abstract void initModel();

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
