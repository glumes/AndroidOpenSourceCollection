package com.glumes.opensource.base;

import com.glumes.opensource.mvp.IView;

/**
 * Created by zhaoying on 2016/12/7.
 */

/**
 * 泛型 T 为数据类型
 * @param <T>
 */
public interface LoadView<T> extends IView{

    void showContent(T t);

    void showFailed(Throwable e);
}
