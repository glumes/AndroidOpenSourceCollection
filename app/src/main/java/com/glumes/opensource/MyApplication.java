package com.glumes.opensource;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.glumes.opensource.di.components.AppComponent;
import com.glumes.opensource.di.components.DaggerAppComponent;
import com.glumes.opensource.di.modules.AppModule;
import com.glumes.opensource.di.modules.HttpModule;
import com.glumes.opensource.di.modules.RealmModule;

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
        initAppComponent();
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

    public void initAppComponent(){
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build() ;
    }

    public AppComponent getAppComponent(){
        return mAppComponent ;
    }

}
