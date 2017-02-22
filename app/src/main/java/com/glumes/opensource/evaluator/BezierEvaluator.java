package com.glumes.opensource.evaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

import timber.log.Timber;

/**
 * Created by zhaoying on 2017/2/12.
 */

/**
 * 贝塞尔曲线插值器，根据起始点和结束点来计算
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF point1;
    private PointF point2 ;


    public BezierEvaluator(PointF point1, PointF point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    /**
     * 三阶贝塞尔曲线计算公式
     * @param t
     * @param point0
     * @param point3
     * @return
     */
    @Override
    public PointF evaluate(float t, PointF point0, PointF point3) {
        PointF point = new PointF();
        float temp = 1 - t;
        Timber.d("fraction t is %f",t);
        Timber.d("start point width is %f,end point width is %f",point0.x,point3.x);

        point.x = point0.x * temp * temp * temp
                + 3 * point1.x * t * temp * temp
                + 3 * point2.x * t * t * temp + point3.x * t * t * t;
        Timber.d("result point width is %f",point.x);

        point.y = point0.y * temp * temp * temp
                + 3 * point1.y * t * temp * temp
                + 3 * point2.y * t * t * temp + point3.y * t * t * t;
        return point;
    }


}
