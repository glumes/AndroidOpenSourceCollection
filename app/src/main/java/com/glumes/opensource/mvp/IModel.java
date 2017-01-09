package com.glumes.opensource.mvp;

/**
 * Created by zhaoying on 16/11/10.
 */

public interface IModel<T> {

    T getData(String type,int page,int num );
}
