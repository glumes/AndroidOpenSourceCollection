package com.glumes.opensource.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.glumes.opensource.R;
import com.glumes.opensource.base.BaseActivity;
import com.glumes.opensource.di.components.ActivityComponent;
import com.glumes.opensource.di.components.DaggerActivityComponent;
import com.glumes.opensource.di.components.HasComponent;
import com.glumes.opensource.di.modules.ActivityModule;
import com.glumes.opensource.di.modules.GankApiModule;
import com.glumes.opensource.ui.adapter.FragmentPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent>{

    private static final int LIMIT = 10;
    private static final int PAGE = 2;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    FragmentPageAdapter mPageAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getComponent().inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mToolbar.inflateMenu(R.menu.base_toolbar_menu);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){

                    case R.id.action_item1:
                        Timber.d("item1");
                        break;
                    case R.id.action_item2:
                        Timber.d("item2");
                        break;
                }
                return true;
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        mPageAdapter = new FragmentPageAdapter(fragmentManager,this);

        mViewPager.setAdapter(mPageAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public ActivityComponent getComponent() {
        return DaggerActivityComponent.builder()
                .gankApiModule(new GankApiModule())
                .activityModule(new ActivityModule(this))
                .build();
    }
}
