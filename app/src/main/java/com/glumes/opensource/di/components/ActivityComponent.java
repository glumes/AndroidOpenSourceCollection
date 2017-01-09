package com.glumes.opensource.di.components;

import com.glumes.opensource.di.modules.ActivityModule;
import com.glumes.opensource.di.modules.GankApiModule;
import com.glumes.opensource.di.scope.ActivityScope;
import com.glumes.opensource.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by zhaoying on 2017/1/6.
 */

/**
 * 公共的 Activity Component 组件
 * 如果根据业务还得细分出具体的 Activity 类型，则：
 * 1、选择继承或依赖该 Component ，完成注入，同时新建具体业务的 Scope 范围。
 * 2、新建具体业务 Component 完成注入
 */

@ActivityScope
@Component(
        modules = {
                GankApiModule.class,
                ActivityModule.class
        }
)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
