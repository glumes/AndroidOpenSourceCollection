package com.glumes.opensource.di.modules;

import com.glumes.opensource.di.scope.ActivityScope;
import com.glumes.opensource.net.api.GankApiService;


import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zhaoying on 2017/1/6.
 */

@Module
public class GankApiModule {

    public GankApiModule() {

    }

    @Provides // 方法以 Provide 开头，增加代码的可读性
    @ActivityScope
    GankApiService proviceGankApiService(Retrofit retrofit) {
        return  retrofit.create(GankApiService.class);
    }

}
