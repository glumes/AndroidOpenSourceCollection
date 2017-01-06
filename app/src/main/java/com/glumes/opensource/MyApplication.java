package com.glumes.opensource;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.glumes.opensource.di.components.AppComponent;
import com.glumes.opensource.di.components.DaggerAppComponent;
import com.glumes.opensource.di.modules.NetworkModule;
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
        mAppComponent = DaggerAppComponent.builder().networkModule(new NetworkModule()).build();

    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }


    public void initRealmConfig() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }


    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
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
