package com.glumes.opensource.di.components;

import com.glumes.opensource.di.modules.FragmentModule;
import com.glumes.opensource.di.scope.FragmentScope;
import com.glumes.opensource.ui.fragment.InfoFragment;

import dagger.Component;

/**
 * Created by zhaoying on 2017/1/3.
 */

/**
 * 公共的 Fragment Component 组件，是 Activity 的 SubComponent 。
 * Fragment 享有 Activity 的内容。
 * 如果根据业务还得细分出具体的 Activity 类型，则：
 * 1、选择继承或依赖该 Component ，完成注入，同时新建具体业务的 Scope 范围。
 * 2、新建具体业务 Component 完成注入
 */

@FragmentScope
@Component(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(InfoFragment fragment);


}
