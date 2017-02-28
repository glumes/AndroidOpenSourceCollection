package com.glumes.opensource.viewlistener;

import android.support.annotation.IntDef;
import android.support.design.widget.AppBarLayout;



/**
 * Created by zhaoying on 2017/1/11.
 */

public abstract class AppbarLayoutOffsetChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public static final int EXPANDED = 0 ;
    public static final int INTERNEDIATE = 1 ;
    public static final int COLLAPSED = 2 ;


    @IntDef({EXPANDED,INTERNEDIATE,COLLAPSED})
    public @interface State{
    }

    @State
    int state ;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0){
            if (state != AppbarLayoutOffsetChangeListener.EXPANDED){
                state = AppbarLayoutOffsetChangeListener.EXPANDED ;
            }
        }else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()){
            if (state != AppbarLayoutOffsetChangeListener.COLLAPSED){
                state = AppbarLayoutOffsetChangeListener.COLLAPSED ;

                doActionOnCollapsed();
            }
        }else {
            if (state != AppbarLayoutOffsetChangeListener.INTERNEDIATE){
                if (state == AppbarLayoutOffsetChangeListener.COLLAPSED){
                }
                state = AppbarLayoutOffsetChangeListener.INTERNEDIATE ;
            }
        }
    }

    public abstract void doActionOnCollapsed();
}
