package com.glumes.opensource.net;

import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by zhaoying on 2016/12/7.
 */

public abstract class LoadSubscriber<T> extends Subscriber<T> {

    protected abstract void onSuccess(T t);

    protected abstract void onFailed(Throwable e);

    @Override
    public void onCompleted() {
        Timber.d("completed");
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }
}
