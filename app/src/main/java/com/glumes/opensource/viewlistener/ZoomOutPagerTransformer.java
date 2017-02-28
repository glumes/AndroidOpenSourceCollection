package com.glumes.opensource.viewlistener;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zhaoying on 2017/1/13.
 */

public class ZoomOutPagerTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    /**
     * 来自谷歌官方示例的动画效果
     * https://developer.android.com/training/animation/screen-slide.html#pagetransformer
     * 想要在 3.0 以下使用属性动画，使用 nineoldandroid 开源库，
     * 并且，修改 ViewPager 中的 版本 IF 判断。
     * @param page
     * @param position
     */
//    @Override
//    public void transformPage(View page, float position) {
//        int pageWidth = page.getWidth() ;
//
//        if (position < -1 ){
//            page.setAlpha(0);
//        }else if (position <= 0){
//            page.setAlpha(1);
//            page.setTranslationX(0);
//            page.setScaleX(1);
//            page.setScaleY(1);
//        }else if (position <= 1){
//            page.setAlpha(1 - position);
//
//            page.setTranslationX(pageWidth * -position);
//
//            float scaleFactor = MIN_SCALE + (1- MIN_SCALE) * (1 - Math.abs(position)) ;
//            page.setScaleX(scaleFactor);
//            page.setScaleY(scaleFactor);
//        }else {
//            page.setAlpha(0);
//        }
//    }

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth() ;
        int pageHeight = page.getHeight() ;

        if (position < -1){
            page.setAlpha(0);
        }else if (position <= 1){
            float scaleFactor = Math.max(MIN_SCALE,1 - Math.abs(position)) ;
            float vertMargin = pageHeight * (1 - scaleFactor) / 2 ;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2 ;
            if (position < 0){
                page.setTranslationX(horzMargin - vertMargin / 2);
            }else {
                page.setTranslationX(-horzMargin + vertMargin / 2 );
            }

            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1- MIN_ALPHA));
        }else {
            page.setAlpha(0);
        }
    }
}
