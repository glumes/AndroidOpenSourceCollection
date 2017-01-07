package com.glumes.opensource.di.modules;

import android.app.Activity;
import android.content.Context;

import com.glumes.opensource.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhaoying on 2017/1/6.
 */


/**
 * 公共的 Activity Module 。
 */
@Module
public class ActivityModule {

    private Activity mActivity ;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    Context provideContext(){
        return this.mActivity ;
    }

    @Provides
    @ActivityScope
    Activity proviceActivity(){
        return this.mActivity ;
    }


}
