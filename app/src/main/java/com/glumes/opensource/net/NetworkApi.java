package com.glumes.opensource.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.glumes.opensource.net.api.GankApiService;
import com.glumes.opensource.net.entity.BaseResult;
import com.glumes.opensource.rx.HttpResultFunc;
import com.glumes.opensource.utils.Constant;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import timber.log.Timber;

/**
 * Created by zhaoying on 16/10/21.
 */

public class NetworkApi {

    private static final long TIME_OUT = 10;
    private static final String BASE_URL = "https://gank.io/api/";
    private Retrofit mRetrofit;
    private GankApiService mGankApiService;

    private static final class NetworkApiHolder {
        private static final NetworkApi INSTANCE = new NetworkApi();
    }

    public static NetworkApi getInstance() {
        return NetworkApiHolder.INSTANCE;
    }

    private NetworkApi() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.d(message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
                .readTimeout(TIME_OUT,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(new StethoInterceptor());  // Stetho 必须使用 NetWorkInterceptor

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mGankApiService = mRetrofit.create(GankApiService.class);
    }

    public Observable<List<BaseResult>> getData(int type,int page,int limit){
        switch (type){
            case Constant.ANDROID_TYPE:
               return mGankApiService.getAndroidResult(limit,page).map(new HttpResultFunc<>()) ;
            case Constant.IOS_TYPE:
                return mGankApiService.getiOSResult(limit,page).map(new HttpResultFunc<>());

            case Constant.PICTURE_TYPE:
                return mGankApiService.getPictureResult(limit,page).map(new HttpResultFunc<>());

        }
        return  null ;
    }


//    /**
//     *
//     * @param <T>
//     */
//    private class HttpResultFunc<T> implements Func1<HttpResult<T>,T>{
//        @Override
//        public T call(HttpResult<T> HttpResult) {
//            if (HttpResult.getError().equals("true")){
//                throw new DataErrorException("data is false");
//            }
//            return HttpResult.getResults();
//        }
//    }




}
