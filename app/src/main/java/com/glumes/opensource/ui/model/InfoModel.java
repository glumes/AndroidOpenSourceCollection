package com.glumes.opensource.ui.model;

import com.glumes.opensource.net.api.GankApiService;
import com.glumes.opensource.net.entity.BaseResult;
import com.glumes.opensource.rx.HttpResultFunc;
import com.glumes.opensource.ui.contract.InfoContract;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by zhaoying on 2017/1/9.
 */

public class InfoModel  implements InfoContract.Model {


    private GankApiService mGankApiService ;

    @Inject
    public InfoModel(GankApiService gankApiService) {
        this.mGankApiService = gankApiService ;
    }


    @Override
    public Observable<List<BaseResult>> getData(String type, int page, int num) {
        return mGankApiService.getDataByType(type, page, num).map(new HttpResultFunc<>()) ;
    }

}
