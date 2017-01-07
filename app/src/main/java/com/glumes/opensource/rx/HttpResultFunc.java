package com.glumes.opensource.rx;

import com.glumes.opensource.net.entity.HttpResult;
import com.glumes.opensource.utils.DataErrorException;

import rx.functions.Func1;

/**
 * Created by zhaoying on 2017/1/3.
 */

public class HttpResultFunc <T> implements Func1<HttpResult<T>,T> {
    @Override
    public T call(HttpResult<T> HttpResult) {
        if (HttpResult.getError().equals("true")){
            throw new DataErrorException("data is false");
        }
        return HttpResult.getResults();
    }
}