package com.glumes.opensource.di.components;

import com.glumes.opensource.di.modules.NetworkModule;
import com.glumes.opensource.net.api.GankApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhaoying on 2017/1/3.
 */

@Singleton
@Component( modules = { NetworkModule.class } )
public interface AppComponent {

    /**
     * 父组件需要显示的暴露对象给子组件
     * @return
     */
    GankApiService gankApiService();
}
