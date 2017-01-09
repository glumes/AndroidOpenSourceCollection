package com.glumes.opensource.ui.presenter;

import com.glumes.opensource.base.BaseModel;
import com.glumes.opensource.base.BasePresenter;
import com.glumes.opensource.ui.contract.InfoContract;

/**
 * Created by zhaoying on 16/11/10.
 */

public class InfoPresenter extends BasePresenter<InfoContract.PictureView,BaseModel>{



    public InfoPresenter() {

    }

    @Override
    public void attachView(InfoContract.PictureView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void initModel() {
        mModel = new BaseModel();
    }


}
