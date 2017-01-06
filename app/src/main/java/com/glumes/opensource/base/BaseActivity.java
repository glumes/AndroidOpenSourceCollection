package com.glumes.opensource.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.glumes.opensource.MyApplication;
import com.glumes.opensource.di.components.AppComponent;

/**
 * Created by zhaoying on 2016/11/25.
 */

public class BaseActivity extends AppCompatActivity {

    Context contenxt = null ;

    public AppComponent getAppComponent(){
        return ((MyApplication)getApplication()).getAppComponent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
