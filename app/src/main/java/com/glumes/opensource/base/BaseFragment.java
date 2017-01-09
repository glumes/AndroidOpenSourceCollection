package com.glumes.opensource.base;

import android.support.v4.app.Fragment;

import com.glumes.opensource.di.components.HasComponent;

/**
 * Created by zhaoying on 2017/1/7.
 */

public  class BaseFragment extends Fragment {


    /**
     * Gets a component for dependency injection by its type.
     *
     * Fragment 依托的 Activity 实现 HasComponent 接口，接口方法为 getComponent ，返回 Component 。
     * HasComponent 带有泛型，指定 Component 类型
     * 然后，Fragment 实现 getComponent 方法。
     * getComponent 方法中 getActivity 将 Activiyt 转换为实现的接口类型。
     * 然后就可以调用接口的 getComponent 方法，得到 Activity 的 Component 。
     *
     */
//    @SuppressWarnings("unchecked")
//    protected <C> C getComponent(Class<C> componentType) {
//        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
//    }


}
