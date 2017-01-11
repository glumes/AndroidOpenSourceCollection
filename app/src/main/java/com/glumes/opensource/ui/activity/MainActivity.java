package com.glumes.opensource.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    GankApiService mGankApiService ;

    @Inject
    Context mContext ;

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

        mAppbarLayout.addOnOffsetChangedListener(new AppbarLayoutOffsetChangeListener() {
            @Override
            public void doActionOnCollapsed() {
                mGankApiService.getRandomData("福利",1)
                                .map(new HttpResultFunc<>())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new LoadSubscriber<List<BaseResult>>() {
                                    @Override
                                    protected void onSuccess(List<BaseResult> baseResults) {
                                        Timber.d(baseResults.get(0).toString());
                                        Glide.with(mContext)
                                                .load(baseResults.get(0).getUrl())
                                                .into(mImage);
                                    }

                                    @Override
                                    protected void onFailed(Throwable e) {

                                    }
                                });
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        mPageAdapter = new FragmentPageAdapter(fragmentManager, this);

        mViewPager.setAdapter(mPageAdapter);
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


}
