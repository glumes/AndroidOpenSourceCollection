package com.glumes.opensource.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.glumes.opensource.MyApplication;
import com.glumes.opensource.R;
import com.glumes.opensource.di.components.DaggerActivityComponent;
import com.glumes.opensource.di.modules.ActivityModule;
import com.glumes.opensource.net.LoadSubscriber;
import com.glumes.opensource.net.api.GankApiService;
import com.glumes.opensource.net.entity.BaseResult;
import com.glumes.opensource.rx.HttpResultFunc;
import com.glumes.opensource.ui.adapter.FragmentPageAdapter;
import com.glumes.opensource.view.AppbarLayoutOffsetChangeListener;
import com.glumes.opensource.view.RotatePagerTramsformer;
import com.glumes.opensource.view.ZoomOutPagerTransformer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

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

        mFloatingActionButton.setOnClickListener(v -> mSubscription = mGankApiService.getRandomData("福利", 1)
                .map(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new LoadSubscriber<List<BaseResult>>() {
                    @Override
                    protected void onSuccess(List<BaseResult> baseResults) {
                        Timber.d(baseResults.get(0).toString());
                        Glide.with(mContext)
                                .load(baseResults.get(0).getUrl())
                                        .listener(GlidePalette.with(baseResults.get(0).getUrl())
                                                .intoCallBack(palette -> {
                                                    if (palette != null) {
//                                                            mCollapsingToolbarLayout.setContentScrimColor(palette
//                                                                    .getDarkVibrantColor(ContextCompat.getColor(mContext,
//                                                                            R.color.colorPrimary)));

//                                                            mFloatingActionButton.setBackgroundColor(palette
//                                                                    .getDarkVibrantColor(ContextCompat.getColor(mContext,
//                                                                            R.color.colorPrimary)));
                                                    }
                                                })
                                        )

                                .into(mImage);
                    }

                    @Override
                    protected void onFailed(Throwable e) {
                        Timber.e("load image failed");
                    }
                }));

        mAppbarLayout.addOnOffsetChangedListener(new AppbarLayoutOffsetChangeListener() {
            @Override
            public void doActionOnCollapsed() {
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        mPageAdapter = new FragmentPageAdapter(fragmentManager, this);

        mViewPager.setAdapter(mPageAdapter);
        mViewPager.setPageTransformer(true,new RotatePagerTramsformer());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setPageMarginDrawable(R.drawable.ic_menu_gallery);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
