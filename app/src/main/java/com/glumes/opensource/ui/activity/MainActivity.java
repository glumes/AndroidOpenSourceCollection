package com.glumes.opensource.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.glumes.opensource.MyApplication;
import com.glumes.opensource.R;
import com.glumes.opensource.base.BaseActivity;
import com.glumes.opensource.di.components.DaggerActivityComponent;
import com.glumes.opensource.di.modules.ActivityModule;
import com.glumes.opensource.evaluator.BezierEvaluator;
import com.glumes.opensource.net.api.GankApiService;
import com.glumes.opensource.ui.adapter.FragmentPageAdapter;
import com.glumes.opensource.viewlistener.AppbarLayoutOffsetChangeListener;
import com.glumes.opensource.viewlistener.RotatePagerTramsformer;

import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import timber.log.Timber;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    FragmentPageAdapter mPageAdapter;
    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.appbarLayout)
    AppBarLayout mAppbarLayout;

    @Inject
    GankApiService mGankApiService;

    @Inject
    Context mContext;

    Subscription mSubscription;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.activity_main)
    CoordinatorLayout mCoordinatorLayout;

    private Drawable[] drawables = new Drawable[6];

    private int mFlowerHeight;
    private int mFlowerWidth;

    private Random mRandom = new Random();

    private CoordinatorLayout.LayoutParams mLayoutParams;

    private static final int UP_POINT  = 0x01 ;
    private static final int DOWN_POINT = 0x02 ;

    private int mLayoutWidth ;
    private int mLayoutHeight ;

    private PointF mStartPoint ;

    private static final long DURATION = 2000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
//            @Override
//            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//
//                AppCompatDelegate delegate = getDelegate() ;
//
//                View view = delegate.createView(parent,name,context,attrs);
//
//                Timber.d("view name is %s",name);
//                return  view ;
//
//            }
//        });

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initData();

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(mContext);
                imageView.setImageDrawable(drawables[mRandom.nextInt(drawables.length)]);
                mCoordinatorLayout.addView(imageView,mLayoutParams);
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView,"alpha",0.2f,1.0f);
                alphaAnimator.setDuration(DURATION);
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView,"scaleX",0.5f,1.0f);
                scaleXAnimator.setDuration(DURATION);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView,"scaleY",0.5f,1.0f);
                scaleYAnimator.setDuration(DURATION);

                ValueAnimator bezierAnimator = getBezierAnimator();
                bezierAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointF pointF = (PointF) animation.getAnimatedValue();
                        imageView.setX(pointF.x);
                        imageView.setY(pointF.y);
                    }
                });
                bezierAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mCoordinatorLayout.removeView(imageView);
                    }
                });
                bezierAnimator.setDuration(DURATION);
                AnimatorSet animatorSet = new AnimatorSet() ;
                animatorSet.play(bezierAnimator).
                        with(alphaAnimator).
                        with(scaleXAnimator).
                        with(scaleYAnimator) ;
                animatorSet.start();
            }
        });

        mAppbarLayout.addOnOffsetChangedListener(new AppbarLayoutOffsetChangeListener() {
            @Override
            public void doActionOnCollapsed() {
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        mPageAdapter = new FragmentPageAdapter(fragmentManager, this);

        mViewPager.setAdapter(mPageAdapter);
        mViewPager.setPageTransformer(true, new RotatePagerTramsformer());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setPageMarginDrawable(R.drawable.ic_menu_gallery);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void initData() {

        DaggerActivityComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);

        mToolbar.inflateMenu(R.menu.base_toolbar_menu);
        mToolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {

                case R.id.setting:
                    startActivity(new Intent(MainActivity.this, SettingActivity.class));
                    break;
                case R.id.action_item2:

                    break;
            }
            return true;
        });


        drawables[0] = getResources().getDrawable(R.drawable.flower_1);
        drawables[1] = getResources().getDrawable(R.drawable.flower_2);
        drawables[2] = getResources().getDrawable(R.drawable.flower_3);
        drawables[3] = getResources().getDrawable(R.drawable.flower_4);
        drawables[4] = getResources().getDrawable(R.drawable.flower_5);
        drawables[5] = getResources().getDrawable(R.drawable.flower_6);

        mFlowerWidth = drawables[0].getIntrinsicWidth();
        mFlowerHeight = drawables[0].getIntrinsicHeight();
        mLayoutParams = new CoordinatorLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.gravity = Gravity.CENTER | Gravity.BOTTOM;
    }

    private ValueAnimator getBezierAnimator(){
        return ValueAnimator.ofObject(new BezierEvaluator(getPointF(DOWN_POINT),getPointF(UP_POINT)),
                mStartPoint,
                new PointF(mRandom.nextInt(mLayoutWidth), 0)) ;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mLayoutWidth = mCoordinatorLayout.getWidth() ;
        mLayoutHeight = mCoordinatorLayout.getHeight() ;
        mStartPoint = new PointF( (mLayoutWidth - mFlowerWidth) / 2 ,mLayoutHeight - mFlowerHeight) ;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (!mSubscription.isUnsubscribed()) {
//            mSubscription.unsubscribe();
//        }
    }

    /**
     * 返回三阶贝塞尔曲线的两个确定路径的点，分别为屏幕上方和下方的点
     * @param position
     * @return
     */
    private PointF getPointF(int position){
        PointF pointF = new PointF() ;
        pointF.x = mRandom.nextInt(mLayoutWidth) ;
        if (position == UP_POINT){
            pointF.y = mRandom.nextInt(mLayoutHeight/2);
        } else {
            pointF.y = mRandom.nextInt(mLayoutHeight/2) + mLayoutHeight / 2 ;
        }
        return pointF ;
    }


}
