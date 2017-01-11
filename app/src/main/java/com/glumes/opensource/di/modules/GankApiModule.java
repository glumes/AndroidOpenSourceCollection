package com.glumes.opensource.di.modules;

import com.glumes.opensource.di.scope.ActivityScope;
import com.glumes.opensource.di.scope.FragmentScope;
import com.glumes.opensource.net.api.GankApiService;
import com.glumes.opensource.net.entity.BaseResult;
import com.glumes.opensource.rx.HttpResultFunc;


import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by zhaoying on 2017/1/6.
 */

@Module
public class GankApiModule {

    public GankApiModule() {

    }

    @Provides // 方法以 Provide 开头，增加代码的可读性
    @Singleton
    GankApiService proviceGankApiService(Retrofit retrofit) {
        return  retrofit.create(GankApiService.class);
    }


//    @Provides
//    @ActivityScope
//    Observable<List<BaseResult>> provideDataByType(GankApiService gankApiService,String type,int limit,int page){
//        return gankApiService.getDataByType(type, limit, page).map(new HttpResultFunc<>()) ;
//    }
//
//    @Provides
//    @ActivityScope
//    Observable<List<BaseResult>> provideRandomData(GankApiService gankApiService,String type,int num){
//        return gankApiService.getRandomData(type, num).map(new HttpResultFunc<>());
//    }


}
