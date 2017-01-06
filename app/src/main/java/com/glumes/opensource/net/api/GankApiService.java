package com.glumes.opensource.net.api;

import com.glumes.opensource.net.entity.BaseResult;
import com.glumes.opensource.net.entity.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zhaoying on 16/10/21.
 */

public interface GankApiService {


    @GET("data/Android/{limit}/{page}")
    Observable<HttpResult<List<BaseResult>>> getAndroidResult(
        @Path("limit") int limit,
        @Path("page") int page
    );

    @GET("data/iOS/{limit}/{page}")
    Observable<HttpResult<List<BaseResult>>> getiOSResult(
            @Path("limit") int limit,
            @Path("page") int page
    );

    @GET("data/福利/{limit}/{page}")
    Observable<HttpResult<List<BaseResult>>> getPictureResult(
            @Path("limit") int limit,
            @Path("page") int page
    );


}
