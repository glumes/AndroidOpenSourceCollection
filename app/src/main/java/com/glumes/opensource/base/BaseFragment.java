package com.glumes.opensource.base;

import android.support.v4.app.Fragment;

import com.glumes.opensource.di.components.HasComponent;

/**
 * Created by zhaoying on 2017/1/7.
 */

public class BaseFragment extends Fragment {



    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
