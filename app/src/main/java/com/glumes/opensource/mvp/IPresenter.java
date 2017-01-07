package com.glumes.opensource.mvp;

/**
 * Created by zhaoying on 16/11/2.
 */

public interface IPresenter<T> {

    void attachView(T t);

    void detachView();

}
