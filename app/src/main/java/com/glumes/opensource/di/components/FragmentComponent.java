package com.glumes.opensource.di.components;

import com.glumes.opensource.di.modules.FragmentModule;
import com.glumes.opensource.ui.fragment.android.AndroidFragment;
import com.glumes.opensource.ui.fragment.benefit.PictureFragment;
import com.glumes.opensource.ui.fragment.ios.iOSFragment;

import dagger.Component;

/**
 * Created by zhaoying on 2017/1/3.
 */

@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(PictureFragment fragment);
    void inject(AndroidFragment fragment);
    void inject(iOSFragment fragment);
}
