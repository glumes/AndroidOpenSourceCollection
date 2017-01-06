package com.glumes.opensource.di.modules;

import com.glumes.opensource.ui.fragment.benefit.PicturePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhaoying on 2017/1/3.
 */

@Module
public class FragmentModule {

    private int mType ;
    public FragmentModule(int type) {
        mType = type ;
    }

    @Provides
    PicturePresenter providePicturePresenter(){
        return new PicturePresenter();
    }
}
