package com.glumes.opensource.net.api;

import com.glumes.opensource.net.entity.BaseResult;
import com.glumes.opensource.net.entity.HttpResult;

import java.util.List;

import javax.annotation.Generated;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zhaoying on 16/10/21.
 */

public interface GankApiService {


    @GET("data/{type}/{num}/{page}")
    Observable<HttpResult<List<BaseResult>>> getDataByType(
            @Path("type") String type,
            @Path("num") int num,
            @Path("page") int page
    );

    @GET("random/data/{type}/{num}")
    Observable<HttpResult<List<BaseResult>>> getRandomData(
            @Path("type") String type,
            @Path("num") int num
    );


}
