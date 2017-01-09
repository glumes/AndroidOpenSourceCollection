package com.glumes.opensource.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by zhaoying on 2017/1/3.
 */

@Module
public class RealmModule {


    @Provides
    @Singleton
    Realm provideRealm(){
        return  Realm.getDefaultInstance() ;
    }

}
