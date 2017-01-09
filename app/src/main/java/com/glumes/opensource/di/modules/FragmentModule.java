package com.glumes.opensource.di.modules;

import com.glumes.opensource.di.scope.FragmentScope;
import com.glumes.opensource.net.api.GankApiService;
import com.glumes.opensource.ui.contract.InfoContract;
import com.glumes.opensource.ui.model.InfoModel;
import com.glumes.opensource.ui.presenter.InfoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhaoying on 2017/1/3.
 */

/**
 * 公共的 Fragment  Module 。
 */
@Module
public class FragmentModule {

    private InfoContract.InfoView mInfoView ;
    public FragmentModule(InfoContract.InfoView infoView) {
        this.mInfoView = infoView ;
    }

    @Provides
    @FragmentScope
    InfoContract.Presenter providePresenter(InfoPresenter infoPresenter){
        return infoPresenter ;
    }

    @Provides
    @FragmentScope
    InfoContract.InfoView provideInfoView(){
        return this.mInfoView ;
    }

    @Provides
    @FragmentScope
    InfoContract.Model provideModel(GankApiService gankApiService){
        return new InfoModel(gankApiService) ;
    }
}
