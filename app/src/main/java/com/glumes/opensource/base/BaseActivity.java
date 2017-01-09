package com.glumes.opensource.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.glumes.opensource.MyApplication;
import com.glumes.opensource.di.components.AppComponent;

/**
 * Created by zhaoying on 2016/11/25.
 */

public abstract class BaseActivity extends AppCompatActivity {


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
        initComponentInject();
    }


    protected abstract void initComponentInject() ;
}
