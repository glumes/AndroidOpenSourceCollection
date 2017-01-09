package com.glumes.opensource.di.components;

import android.app.Application;

import com.glumes.opensource.di.modules.AppModule;
import com.glumes.opensource.di.modules.GankApiModule;
import com.glumes.opensource.di.modules.HttpModule;
import com.glumes.opensource.di.modules.RealmModule;
import com.glumes.opensource.net.api.GankApiService;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by zhaoying on 2017/1/3.
 */


/**
 * 全局 Component 组件。
 * 不能被继承 SubComponent ，只能被依赖 Dependence。
 * 选择性地向外暴露对象。
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                HttpModule.class,
        }
)
public interface AppComponent {


    Application application();

    OkHttpClient okHttpClient();

    Gson gson();

    Retrofit retrofit();



}
