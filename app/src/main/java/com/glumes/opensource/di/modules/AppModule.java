package com.glumes.opensource.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhaoying on 2017/1/6.
 */

@Module
public class AppModule {

    Application mApplication ;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application proviceApplication(){
        return this.mApplication ;
    }


}
