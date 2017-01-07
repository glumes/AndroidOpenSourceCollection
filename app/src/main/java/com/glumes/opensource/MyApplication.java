package com.glumes.opensource;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.glumes.opensource.di.components.AppComponent;
import com.glumes.opensource.di.components.DaggerAppComponent;
import com.glumes.opensource.di.modules.HttpModule;
import com.glumes.opensource.utils.FakeCrashLibrary;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by zhaoying on 16/10/20.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    public AppComponent mAppComponent ;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initRealmConfig();
        initTimber();
        initStetho();

    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }


    public void initRealmConfig() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }


    public void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }


    public AppComponent getAppComponent(){
        return mAppComponent ;
    }

}
