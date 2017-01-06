package com.glumes.opensource;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.RelativeLayout;

import com.glumes.opensource.net.entity.BaseResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import timber.log.Timber;

public class PictureActivity extends AppCompatActivity {


    @BindView(R.id.activity_picture)
    RelativeLayout mActivityPicture;

    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private static final int LIMIT = 20;

    private static final int PAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

//        mLayoutManager = new GridLayoutManager(this,2);

        mLayoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(mLayoutManager);




//        NetworkApi.getInstance().getAndroidResult(10, 1,
        Subscriber<List<BaseResult>> subscriber = new Subscriber<List<BaseResult>>() {
            @Override
            public void onCompleted() {
                Timber.d("request finish");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("error is %s" , e.getMessage());
            }

            @Override
            public void onNext(List<BaseResult> baseResults) {
                Timber.d("list size is %d ", baseResults.size());
                for (BaseResult result : baseResults){
                    Timber.d(result.getUrl());
                }
            }
        };

//        NetworkApi.getInstance().getAndroidResult(10,1,subscriber);
//        NetworkApi.getInstance().getiOSResult(10,1,subscriber);
//        NetworkApi.getInstance().getPictureResult(10,1,subscriber);


    }

}
