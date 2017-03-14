package com.glumes.opensource.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.glumes.opensource.MyApplication;
import com.glumes.opensource.di.components.AppComponent;
import com.glumes.opensource.utils.ThemeFactory;

import timber.log.Timber;

/**
 * Created by zhaoying on 2016/11/25.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {


        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

                AppCompatDelegate delegate = getDelegate() ;

                View view = delegate.createView(parent,name,context,attrs);

                Timber.d("view name is %s",name);
                return  view ;

            }
        });

        super.onCreate(savedInstanceState, persistentState);
        Timber.d("start");
    }

}
