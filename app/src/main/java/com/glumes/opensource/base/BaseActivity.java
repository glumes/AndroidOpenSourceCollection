package com.glumes.opensource.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.glumes.opensource.MyApplication;
import com.glumes.opensource.di.components.ActivityComponent;
import com.glumes.opensource.di.components.AppComponent;
import com.glumes.opensource.di.components.DaggerActivityComponent;
import com.glumes.opensource.di.modules.ActivityModule;
import com.glumes.opensource.di.modules.GankApiModule;

/**
 * Created by zhaoying on 2016/11/25.
 */

public  class BaseActivity extends AppCompatActivity {


    private AppComponent mAppComponent ;

    public AppComponent getAppComponent(){
        if (mAppComponent == null){
            mAppComponent = MyApplication.getInstance().getAppComponent();
        }
        return mAppComponent ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


}
