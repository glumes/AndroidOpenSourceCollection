package com.glumes.opensource.di.components;

import com.glumes.opensource.di.modules.FragmentModule;
import com.glumes.opensource.di.modules.GankApiModule;
import com.glumes.opensource.di.scope.FragmentScope;
import com.glumes.opensource.ui.fragment.InfoFragment;

import dagger.Component;

/**
 * Created by zhaoying on 2017/1/3.
 */



@FragmentScope
@Component(
        dependencies = AppComponent.class ,

        modules = {
                FragmentModule.class,

        }
)
public interface FragmentComponent {

    void inject(InfoFragment fragment);


}
