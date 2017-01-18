package com.glumes.opensource.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zhaoying on 2017/1/14.
 */

public class RotatePagerTramsformer implements ViewPager.PageTransformer {


    private static final float MAX_ROTATE = 20f;

    private float mRot ;

    @Override
    public void transformPage(View page, float position) {

        int pageWidth = page.getWidth();

        if (position < -1) {  // 不可见时

            page.setRotation(0);

        } else if (position <= 0) { // 旋转出去

            mRot = position * MAX_ROTATE ;
            page.setPivotX(pageWidth / 2);
            page.setPivotY(page.getMeasuredHeight());
            page.setRotation(mRot);

        } else if (position <= 1) { // 旋转进来
            mRot = position * MAX_ROTATE ;
            page.setPivotX(pageWidth / 2);
            page.setPivotY(page.getMeasuredHeight());
            page.setRotation(mRot);

        } else {
            page.setRotation(0);
        }

    }

}
